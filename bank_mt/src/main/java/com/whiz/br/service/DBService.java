package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Parcela;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.enums.EstadoTransferencia;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.repository.ParcelaRepository;
import com.whiz.br.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DBService {

    private final ContaRepository contaRepository;
    private final TransferenciaRepository transferenciaRepository;
    private final ParcelaRepository parcelaRepository;

    public void instatiateTestDataBase() {

        Conta cont1 = new Conta(null, "8054", 1220.0);
        Conta cont2 = new Conta(null, "8183", 4330.0);
        Conta cont3 = new Conta(null, "5475", 10.000);
        Conta cont4 = new Conta(null, "5235", 5540.10);
        Conta cont5 = new Conta(null, "8877", 3432.30);
        Conta cont6 = new Conta(null, "9324", 4435.55);
        Conta cont7 = new Conta(null, "8433", 6070.40);
        Conta cont8 = new Conta(null, "6543", 11020.10);
        Conta cont9 = new Conta(null, "1325", 5000.0);

        Transferencia t1 = new Transferencia(null, 300.0, EstadoTransferencia.CONCLUIDA,
                LocalDate.of(2021, 3, 11), cont1);
        Transferencia t2 = new Transferencia(null, 150.0, EstadoTransferencia.CONCLUIDA,
                LocalDate.of(2022, 2, 28), cont2);
        Transferencia t3 = new Transferencia(null, 400.35, EstadoTransferencia.CONCLUIDA,
                LocalDate.of(2019, 5, 23), cont3);
        Transferencia t4 = new Transferencia(null, 200.50, EstadoTransferencia.CONCLUIDA,
                LocalDate.of(2016, 1, 12), cont5);
        Transferencia t5 = new Transferencia(null, 500.0, EstadoTransferencia.CONCLUIDA,
                LocalDate.of(2021, 6, 15), cont8);
        Transferencia t6 = new Transferencia(null, 250.40, EstadoTransferencia.CONCLUIDA,
                LocalDate.of(2020, 7, 16), cont9);
        Transferencia t7 = new Transferencia(null, 800.50, EstadoTransferencia.CONCLUIDA,
                LocalDate.of(2017, 9, 1), cont8);
        Transferencia t8 = new Transferencia(null, 1000.00, EstadoTransferencia.PROGRAMADA,
                LocalDate.of(2022, 6, 5), cont7);
        Transferencia t9 = new Transferencia(null, 220.20, EstadoTransferencia.CANCELADA,
                LocalDate.of(2022, 1, 4), cont4);
        Transferencia t10 = new Transferencia(null, 22.00, EstadoTransferencia.CONCLUIDA,
                LocalDate.of(2021, 5, 8), cont6);
        Transferencia t11 = new Transferencia(null, 100.00, EstadoTransferencia.CONCLUIDA,
                LocalDate.of(2018, 2, 21), cont7);

        cont1.getTransferencias().addAll(List.of(t1));
        cont2.getTransferencias().addAll(List.of(t2));
        cont3.getTransferencias().addAll(List.of(t3));
        cont4.getTransferencias().addAll(List.of(t9));
        cont5.getTransferencias().addAll(List.of(t4));
        cont6.getTransferencias().addAll(List.of(t10));
        cont7.getTransferencias().addAll(List.of(t8, t11));
        cont8.getTransferencias().addAll(List.of(t5, t7));
        cont9.getTransferencias().addAll(List.of(t6));

        //T1
        Parcela p1 = new Parcela(null, t1.getValor() / 3, t1.getData().plusMonths(1), t1);
        Parcela p2 = new Parcela(null, t1.getValor() / 3, t1.getData().plusMonths(2), t1);
        Parcela p3 = new Parcela(null, t1.getValor() / 3, t1.getData().plusMonths(3), t1);
        //T3
        Parcela p5 = new Parcela(null, t3.getValor() / 2, t3.getData().plusMonths(1), t3);
        Parcela p6 = new Parcela(null, t3.getValor() / 2, t3.getData().plusMonths(2), t3);
        //T8
        Parcela p11 = new Parcela(null, t8.getValor() / 4, t8.getData().plusMonths(1), t8);
        Parcela p12 = new Parcela(null, t8.getValor() / 4, t8.getData().plusMonths(2), t8);
        Parcela p13 = new Parcela(null, t8.getValor() / 4, t8.getData().plusMonths(3), t8);
        Parcela p14 = new Parcela(null, t8.getValor() / 4, t8.getData().plusMonths(4), t8);
        //T9
        Parcela p15 = new Parcela(null, t9.getValor() / 2, t9.getData(), t9);
        Parcela p16 = new Parcela(null, t9.getValor() / 2, t9.getData(), t9);


        t1.getParcelas().addAll(List.of(p1, p2, p3));
        t3.getParcelas().addAll(List.of(p5, p6));
        t8.getParcelas().addAll(List.of(p11, p12, p13, p14));
        t9.getParcelas().addAll(List.of(p15, p16));

        contaRepository.saveAll(List.of(cont1, cont2, cont3, cont4, cont5, cont6, cont7, cont8, cont9));
        transferenciaRepository.saveAll(List.of(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11));
        parcelaRepository.saveAll(List.of(p1, p2, p3, p5, p6, p11, p12, p13, p14, p15, p16));
    }
}
