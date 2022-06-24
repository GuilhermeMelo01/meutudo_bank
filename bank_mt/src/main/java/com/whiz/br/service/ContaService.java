package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.repository.ContaRepository;
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

    public Double findBalance(Long id){
        Conta conta = contaRepository.findById(id).orElseThrow();
        return conta.getSaldo();
    }
}
