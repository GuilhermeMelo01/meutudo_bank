package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.service.exception.IllegalArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> findAll(){
        return contaRepository.findAll();
    }

    public Conta findById(Long id){
        return contaRepository.findById(id).orElseThrow();
    }

    public Double findBalance(Long id){
        Conta conta = contaRepository.findById(id).orElseThrow();
        return conta.getSaldo();
    }

    public void transferencia(NewTransferenciaDTO newTransferenciaDTO){
        Conta contaEnvia = findById(newTransferenciaDTO.getIdEnv());
        Conta contaRecebe = findById(newTransferenciaDTO.getIdRec());
        Double valor = newTransferenciaDTO.getValue();
        Double saldoContaEnviar = contaEnvia.getSaldo();
        if(saldoContaEnviar <= 0){
            throw new IllegalArgumentException("Não existe saldo para fazer essa transferencia");
        }
        if ((saldoContaEnviar - valor) < 0){
            throw new IllegalArgumentException("Não existe saldo para efetuar a transferencia");
        }
        contaEnvia.setSaldo(contaEnvia.getSaldo() - valor);
        contaRecebe.setSaldo(contaRecebe.getSaldo() + valor);
        contaRepository.saveAll(List.of(contaEnvia, contaRecebe));
    }
}
