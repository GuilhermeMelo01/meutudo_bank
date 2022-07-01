package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    public Conta findById(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Value not found! value: " + id));
    }

    public List<Conta> findAll() {
        return contaRepository.findAll();
    }

    public String findBalance(Long id) {
        Conta conta = contaRepository.findById(id).orElseThrow();
        return "Numero da conta: "+ conta.getNumeroConta() + " - Saldo da conta: " + conta.getSaldo();
    }

}
