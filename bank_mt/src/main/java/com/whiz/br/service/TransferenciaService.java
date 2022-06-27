package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Parcela;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.dto.ReverterTransferenciaDTO;
import com.whiz.br.dto.TransferenciaParceladaDTO;
import com.whiz.br.enums.EstadoTransferencia;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.repository.ParcelaRepository;
import com.whiz.br.repository.TransferenciaRepository;
import com.whiz.br.service.exception.IllegalArgumentException;
import com.whiz.br.service.utils.HelpTransferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferenciaService implements HelpTransferencia {

    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ContaService contaService;
    @Autowired
    private ParcelaRepository parcelaRepository;

    public Transferencia findById(Long id) {
        return transferenciaRepository.findById(id).orElseThrow();
    }

    public void transferencia(NewTransferenciaDTO newTransferenciaDTO) {
        Conta contaEnviador = contaService.findById(newTransferenciaDTO.getIdEnviadorTransferencia());
        Conta contaRecebedor = contaService.findById(newTransferenciaDTO.getIdRecebedorTransferencia());
        Double valorTransferencia = newTransferenciaDTO.getValorTransferencia();
        Double saldoContaEnviador = contaEnviador.getSaldo();
        verificarSaldo(saldoContaEnviador, valorTransferencia);
        contaEnviador.setSaldo(contaEnviador.getSaldo() - valorTransferencia);
        contaRecebedor.setSaldo(contaRecebedor.getSaldo() + valorTransferencia);
        Transferencia transferencia = newTransferencia(valorTransferencia, EstadoTransferencia.CONCLUIDA, LocalDate.now(), contaEnviador);
        transferenciaRepository.saveAll(List.of(transferencia));
        contaRepository.saveAll(List.of(contaEnviador, contaRecebedor));
    }

    public void reverterTransferencia(Long idEnviadorReembolso, ReverterTransferenciaDTO reverterTransferenciaDTO) {
        verificarId(idEnviadorReembolso, reverterTransferenciaDTO.getIdRecebedorReembolso());
        Conta contaEnviadorReembolso = contaService.findById(idEnviadorReembolso);
        Conta contaRecebedorReembolso = contaService.findById(reverterTransferenciaDTO.getIdRecebedorReembolso());
        Transferencia transferencia = findById(reverterTransferenciaDTO.getIdTransferencia());
        Double valorTransferencia = transferencia.getValue();
        contaEnviadorReembolso.setSaldo(contaEnviadorReembolso.getSaldo() - valorTransferencia);
        contaRecebedorReembolso.setSaldo(contaRecebedorReembolso.getSaldo() + valorTransferencia);
        transferenciaCancelada(transferencia, EstadoTransferencia.CANCELADA);
        transferenciaRepository.saveAll(List.of(transferencia));
        contaRepository.saveAll(List.of(contaEnviadorReembolso, contaRecebedorReembolso));
    }

    public void transferenciaParcelada(TransferenciaParceladaDTO transferenciaParceladaDTO) {
        Conta contaEnviador = contaService.findById(transferenciaParceladaDTO.getIdEnviadorTransferencia());
        Double valorTransferencia = transferenciaParceladaDTO.getValor();
        Integer numeroParcelas = transferenciaParceladaDTO.getNumeroParcelas();
        LocalDate dataPagamentoPlus = LocalDate.now().plusMonths(1);
        verificarSaldo(contaEnviador.getSaldo(), valorTransferencia);
        double valorParcelas = valorTransferencia / numeroParcelas;
        Transferencia newTransferencia = newTransferenciaParcelada(valorTransferencia, EstadoTransferencia.PROGRAMADA, LocalDate.now(), contaEnviador);
        List<Parcela> parcelas = newParcelas(numeroParcelas, valorParcelas, dataPagamentoPlus, newTransferencia);
        transferenciaRepository.saveAll(List.of(newTransferencia));
        parcelaRepository.saveAll(parcelas);
    }

    private void verificarSaldo(Double saldoContaEnviador, Double valorTransferencia) {
        List<Integer> lista = new ArrayList<>();
        if (saldoContaEnviador <= 0) {
            lista.add(1);
        }
        if ((saldoContaEnviador - valorTransferencia) < 0) {
            lista.add(1);
        }
        if (lista.size() != 0) {
            throw new IllegalArgumentException("Não existe saldo para fazer esse procedimento");
        }
    }

    private void verificarId(Long idEnviador, Long idRecebedor){ 
        if(idEnviador.equals(idRecebedor)){
            throw new IllegalArgumentException(
                    "values cannot be equals! value(1): "+ idEnviador +" value(2): "+ idRecebedor);
        }
    }

    private List<Parcela> newParcelas(Integer numeroParcelas, Double valorParcelas,
                                            LocalDate dataPagamentoPlus, Transferencia newTransferencia){
        List<Parcela> listaDeParcelas = new ArrayList<>();
        for (int i = 0; i < numeroParcelas; i++) {
            listaDeParcelas.add(new Parcela(null, valorParcelas, dataPagamentoPlus, newTransferencia));
            dataPagamentoPlus = dataPagamentoPlus.plusMonths(1);
        }
        newTransferencia.getParcelas().addAll(listaDeParcelas);
        return listaDeParcelas;
    }

    @Override
    public Transferencia newTransferencia(Double value, EstadoTransferencia estado, LocalDate localDate, Conta conta) {
        return new Transferencia(null, value, estado, localDate, conta);
    }

    @Override
    public Transferencia newTransferenciaParcelada(Double value, EstadoTransferencia estado, LocalDate localDate, Conta conta) {
        return new Transferencia(null, value, estado, localDate, conta);
    }

    @Override
    public void transferenciaCancelada(Transferencia transferencia, EstadoTransferencia estado) {
        transferencia.setEstado(estado);
    }
}
