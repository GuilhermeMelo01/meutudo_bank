package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.dto.ReverterTransferenciaDTO;
import com.whiz.br.enums.EstadoTransferencia;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.repository.TransferenciaRepository;
import com.whiz.br.service.exception.IllegalArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ContaService contaService;

    public Transferencia findById(Long id) {
        return transferenciaRepository.findById(id).orElseThrow();
    }

    public void transferencia(NewTransferenciaDTO newTransferenciaDTO) {
        Conta contaEnviador = contaService.findById(newTransferenciaDTO.getIdEnviadorTransferencia());
        Conta contaRecebedor = contaService.findById(newTransferenciaDTO.getIdRecebedorTransferencia());
        Double valorTransferencia = newTransferenciaDTO.getValorTransferencia();
        Double saldoContaEnviador = contaEnviador.getSaldo();
        if (saldoContaEnviador <= 0) {
            throw new IllegalArgumentException("Não existe saldo para fazer essa transferencia");
        }
        if ((saldoContaEnviador - valorTransferencia) < 0) {
            throw new IllegalArgumentException("Não existe saldo para efetuar a transferencia");
        }
        contaEnviador.setSaldo(contaEnviador.getSaldo() - valorTransferencia);
        contaRecebedor.setSaldo(contaRecebedor.getSaldo() + valorTransferencia);
        newTransferencia(valorTransferencia, LocalDate.now(), contaEnviador);
        contaRepository.saveAll(List.of(contaEnviador, contaRecebedor));
    }

    public void reverterTransferencia(Long idEnviadorReembolso, ReverterTransferenciaDTO reverterTransferenciaDTO){
        Conta contaEnviadorReembolso = contaService.findById(idEnviadorReembolso);
        Conta contaRecebedorReembolso = contaService.findById(reverterTransferenciaDTO.getIdRecebedorReembolso());
        Transferencia transferencia = findById(reverterTransferenciaDTO.getIdTransferencia());
        Double valorTransferencia = transferencia.getValue();
        contaEnviadorReembolso.setSaldo(contaEnviadorReembolso.getSaldo() - valorTransferencia);
        contaRecebedorReembolso.setSaldo(contaRecebedorReembolso.getSaldo() + valorTransferencia);
        contaRepository.saveAll(List.of(contaEnviadorReembolso, contaRecebedorReembolso));
    }

    private void newTransferencia(Double value, LocalDate localDate, Conta conta) {
        Transferencia newTransferencia = new Transferencia(null, value, EstadoTransferencia.CONCLUIDA, localDate, conta);
        transferenciaRepository.saveAll(List.of(newTransferencia));
    }
}
