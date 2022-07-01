package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Parcela;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.repository.ParcelaRepository;
import com.whiz.br.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public void transferencia(Long idContaEnviador, Long idContaRecebedor, Double valorTransferencia) {
        Conta contaEnviadorDB = contaService.findById(idContaEnviador);
        Conta contaRecebedorDB = contaService.findById(idContaRecebedor);
        contaEnviadorDB.saquar(valorTransferencia, contaRecebedorDB);
        Transferencia transferencia = Transferencia.builder().valor(valorTransferencia).estado(1)
                .data(LocalDate.now()).conta(contaEnviadorDB).idContaRecebedora(contaRecebedorDB.getId()).build();
        transferenciaRepository.saveAll(List.of(transferencia));
        contaRepository.saveAll(List.of(contaEnviadorDB, contaRecebedorDB));
    }

    public void reverterTransferencia(Long idEnviadorReembolso, Long idRecebedorReembolso, Long idTransferencia) {
        Conta contaEnviadorReembolso = contaService.findById(idEnviadorReembolso);
        Conta contaRecebedorReembolso = contaService.findById(idRecebedorReembolso);
        contaEnviadorReembolso.verificarIdEquals(contaRecebedorReembolso.getId());
        Transferencia transferencia = findById(idTransferencia);
        Double valorTransferencia = contaEnviadorReembolso.saquar(transferencia.getValor(), contaRecebedorReembolso);
        Transferencia transferenciaCancelada = Transferencia.builder().id(transferencia.getId()).valor(valorTransferencia).estado(3)
                .data(transferencia.getData()).conta(contaRecebedorReembolso)
                .idContaRecebedora(contaEnviadorReembolso.getId()).build();
        transferenciaRepository.saveAll(List.of(transferenciaCancelada));
        contaRepository.saveAll(List.of(contaEnviadorReembolso, contaRecebedorReembolso));
    }

    public void transferenciaParcelada(Long idEnviadorTransferencia, Long idRecebedorTransferencia,
                                       Double valorTransferencia, Integer numeroParcelas) {
        Conta contaEnviador = contaService.findById(idEnviadorTransferencia);
        Conta contaRecebedor = contaService.findById(idRecebedorTransferencia);
        LocalDate dataPagamentoPlus = LocalDate.now().plusMonths(1);
        contaEnviador.saquar(valorTransferencia, contaRecebedor);
        double valorParcelas = valorTransferencia / numeroParcelas;
        Transferencia novaTransferencia = Transferencia.builder().valor(valorTransferencia).estado(2).data(LocalDate.now())
                .conta(contaEnviador).idContaRecebedora(contaRecebedor.getId()).build();
        List<Parcela> listaDeParcelas = new ArrayList<>();
        for (int i = 0; i < numeroParcelas; i++) {
            listaDeParcelas.add(new Parcela(null, valorParcelas, dataPagamentoPlus, novaTransferencia));
            dataPagamentoPlus = dataPagamentoPlus.plusMonths(1);
        }
        transferenciaRepository.saveAll(List.of(novaTransferencia));
        parcelaRepository.saveAll(listaDeParcelas);
    }
}
