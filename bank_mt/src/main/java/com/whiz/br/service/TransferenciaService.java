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
import com.whiz.br.service.exception.verification.VerificarSaldo;
import com.whiz.br.service.utils.HelpTransferencia;
import com.whiz.br.service.utils.Parcelas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        VerificarSaldo.verificarSaldo(saldoContaEnviador, valorTransferencia);
        contaEnviador.setSaldo(contaEnviador.getSaldo() - valorTransferencia);
        contaRecebedor.setSaldo(contaRecebedor.getSaldo() + valorTransferencia);
        Transferencia transferencia = newTransferencia(valorTransferencia, EstadoTransferencia.CONCLUIDA, LocalDate.now(), contaEnviador);
        transferenciaRepository.saveAll(List.of(transferencia));
        contaRepository.saveAll(List.of(contaEnviador, contaRecebedor));
    }

    public void reverterTransferencia(Long idEnviadorReembolso, ReverterTransferenciaDTO reverterTransferenciaDTO) {
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
        Double valor = transferenciaParceladaDTO.getValor();
        Integer numeroParcelas = transferenciaParceladaDTO.getNumeroParcelas();
        LocalDate dataPagamentoPlus = LocalDate.now().plusMonths(1);
        VerificarSaldo.verificarSaldo(contaEnviador.getSaldo(), valor);
        double valorParcelas = valor / numeroParcelas;
        Transferencia newTransferencia = newTransferenciaParcelada(valor, EstadoTransferencia.PROGRAMADA, LocalDate.now(), contaEnviador);
        List<Parcela> parcelas = Parcelas.newParcelas(numeroParcelas, valorParcelas, dataPagamentoPlus, newTransferencia);
        transferenciaRepository.saveAll(List.of(newTransferencia));
        parcelaRepository.saveAll(parcelas);
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
