package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public void instatiateTestDataBase() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Conta cont1 = new Conta(null, "8054", 1220.0);
        Conta cont2 = new Conta(null, "8183", 4330.0);

        Transferencia t1 = new Transferencia(null, sdf.parse("11/05/2021"), cont1);

        cont1.getTransferencias().addAll(List.of(t1));

        contaRepository.saveAll(List.of(cont1, cont2));
        transferenciaRepository.saveAll(List.of(t1));
    }
}
