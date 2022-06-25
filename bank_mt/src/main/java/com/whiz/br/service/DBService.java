package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Parcela;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.enums.EstadoTransferencia;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.repository.ParcelaRepository;
import com.whiz.br.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private ParcelaRepository parcelaRepository;

    public void instatiateTestDataBase() {

        Conta cont1 = new Conta(null, "8054", 1220.0);
        Conta cont2 = new Conta(null, "8183", 4330.0);

        Transferencia t1 = new Transferencia(null, 300.0, EstadoTransferencia.CONCLUIDA, LocalDate.of(2021, 3, 11), cont1);
        Transferencia t2 = new Transferencia(null, 150.0, EstadoTransferencia.CONCLUIDA, LocalDate.of(2022, 2, 28), cont2);

        cont1.getTransferencias().addAll(List.of(t1));
        cont2.getTransferencias().addAll(List.of(t2));

        Parcela p1 = new Parcela(null, t1.getValue() / 3, LocalDate.now(), t1);
        Parcela p2 = new Parcela(null, t1.getValue() / 3, LocalDate.now(), t1);
        Parcela p3 = new Parcela(null, t1.getValue() / 3, LocalDate.now(), t1);
        t1.getParcelas().addAll(List.of(p1, p2, p3));

        contaRepository.saveAll(List.of(cont1, cont2));
        transferenciaRepository.saveAll(List.of(t1, t2));
        parcelaRepository.saveAll(List.of(p1, p2, p3));
    }
}
