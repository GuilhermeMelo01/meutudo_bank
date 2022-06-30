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
import com.whiz.br.service.utils.HelpTransferencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaRepository contaRepository;
    private final ContaService contaService;
    private final ParcelaRepository parcelaRepository;

    public Transferencia findById(Long id) {
        return transferenciaRepository.findById(id).orElseThrow();
    }

    public List<Transferencia> findAll() {
        return transferenciaRepository.findAll();
    }

    public void transferencia(NewTransferenciaDTO newTransferenciaDTO) {
        Conta contaEnviador = contaService.findById(newTransferenciaDTO.getIdEnviadorTransferencia());
        Conta contaRecebedor = contaService.findById(newTransferenciaDTO.getIdRecebedorTransferencia());
        Double valorTransferencia = newTransferenciaDTO.getValorTransferencia();
        Double saldoContaEnviador = contaEnviador.getSaldo();
        HelpTransferencia.verificarSaldo(saldoContaEnviador, valorTransferencia);
        contaEnviador.setSaldo(contaEnviador.getSaldo() - valorTransferencia);
        contaRecebedor.setSaldo(contaRecebedor.getSaldo() + valorTransferencia);
        Transferencia transferencia = HelpTransferencia.newTransferencia(valorTransferencia,
                EstadoTransferencia.CONCLUIDA, LocalDate.now(), contaEnviador, contaRecebedor);
        transferenciaRepository.saveAll(List.of(transferencia));
        contaRepository.saveAll(List.of(contaEnviador, contaRecebedor));
    }

    public void reverterTransferencia(Long idEnviadorReembolso, ReverterTransferenciaDTO reverterTransferenciaDTO) {
        HelpTransferencia.verificarIdEquals(idEnviadorReembolso, reverterTransferenciaDTO.getIdRecebedorReembolso());
        Conta contaEnviadorReembolso = contaService.findById(idEnviadorReembolso);
        Conta contaRecebedorReembolso = contaService.findById(reverterTransferenciaDTO.getIdRecebedorReembolso());
        Transferencia transferencia = findById(reverterTransferenciaDTO.getIdTransferencia());
        Double valorTransferencia = transferencia.getValor();
        contaEnviadorReembolso.setSaldo(contaEnviadorReembolso.getSaldo() - valorTransferencia);
        contaRecebedorReembolso.setSaldo(contaRecebedorReembolso.getSaldo() + valorTransferencia);
        HelpTransferencia.transferenciaCancelada(transferencia, EstadoTransferencia.CANCELADA);
        transferenciaRepository.saveAll(List.of(transferencia));
        contaRepository.saveAll(List.of(contaEnviadorReembolso, contaRecebedorReembolso));
    }

    public void transferenciaParcelada(TransferenciaParceladaDTO transferenciaParceladaDTO) {
        Conta contaEnviador = contaService.findById(transferenciaParceladaDTO.getIdEnviadorTransferencia());
        Conta contaRecebedor = contaService.findById(transferenciaParceladaDTO.getIdRecebedorTransferencia());
        Double valorTransferencia = transferenciaParceladaDTO.getValor();
        Integer numeroParcelas = transferenciaParceladaDTO.getNumeroParcelas();
        LocalDate dataPagamentoPlus = LocalDate.now().plusMonths(1);
        HelpTransferencia.verificarSaldo(contaEnviador.getSaldo(), valorTransferencia);
        double valorParcelas = valorTransferencia / numeroParcelas;
        Transferencia newTransferencia = HelpTransferencia.newTransferenciaParcelada(
                valorTransferencia, EstadoTransferencia.PROGRAMADA, LocalDate.now(), contaEnviador, contaRecebedor);
        List<Parcela> parcelas = HelpTransferencia.newParcelas(
                numeroParcelas, valorParcelas, dataPagamentoPlus, newTransferencia);
        transferenciaRepository.saveAll(List.of(newTransferencia));
        parcelaRepository.saveAll(parcelas);
    }
}
