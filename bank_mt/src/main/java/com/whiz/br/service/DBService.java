package com.whiz.br.service;

import com.whiz.br.domain.Cliente;
import com.whiz.br.domain.Conta;
import com.whiz.br.repository.ClienteRepository;
import com.whiz.br.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;

    public void instatiateTestDataBase(){

        Cliente cli1 = new Cliente(null, "Guilherme");
        Cliente cli2 = new Cliente(null, "Marcela");

        Conta cont1 = new Conta(null, "8054", 1220.0, cli1);
        Conta cont2 = new Conta(null, "8183", 4330.0, cli2);

        cli1.getConta().addAll(List.of(cont1));
        cli2.getConta().addAll(List.of(cont2));

        clienteRepository.saveAll(List.of(cli1, cli2));
        contaRepository.saveAll(List.of(cont1, cont2));
    }
}
