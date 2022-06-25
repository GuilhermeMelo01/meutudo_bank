package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.repository.TransferenciaRepository;
import com.whiz.br.service.exception.IllegalArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Conta contaEnvia = contaService.findById(newTransferenciaDTO.getIdEnv());
        Conta contaRecebe = contaService.findById(newTransferenciaDTO.getIdRec());
        Double valor = newTransferenciaDTO.getValue();
        Double saldoContaEnviar = contaEnvia.getSaldo();
        if (saldoContaEnviar <= 0) {
            throw new IllegalArgumentException("Não existe saldo para fazer essa transferencia");
        }
        if ((saldoContaEnviar - valor) < 0) {
            throw new IllegalArgumentException("Não existe saldo para efetuar a transferencia");
        }
        contaEnvia.setSaldo(contaEnvia.getSaldo() - valor);
        contaRecebe.setSaldo(contaRecebe.getSaldo() + valor);
        contaRepository.saveAll(List.of(contaEnvia, contaRecebe));
    }
}
