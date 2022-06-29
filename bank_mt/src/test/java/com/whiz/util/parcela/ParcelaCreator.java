package com.whiz.util.parcela;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Parcela;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.enums.EstadoTransferencia;

import java.time.LocalDate;

public class ParcelaCreator {

    public static Parcela creatorValidParcelaWithTransferencia() {
        Transferencia transferencia = new Transferencia(1L, 300.0, EstadoTransferencia.PROGRAMADA, LocalDate.now(),
                new Conta());
        Parcela parcela = new Parcela(1L, 100.0, LocalDate.now().plusMonths(1), transferencia);
        transferencia.getParcelas().add(parcela);
        return parcela;
    }
}
