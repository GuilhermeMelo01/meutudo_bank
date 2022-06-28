package com.whiz.util;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.enums.EstadoTransferencia;

import java.time.LocalDate;

public class TransferenciaCreator {

    public static Transferencia creatorTransferenciaToBeSaved(){
        return new Transferencia(null, 300.0, EstadoTransferencia.CONCLUIDA, LocalDate.now(),
                new Conta());
    }

    public static Transferencia creatorValidTransferencia(){
        return new Transferencia(1L, 300.0, EstadoTransferencia.CONCLUIDA, LocalDate.now(),
                new Conta());
    }

    public static Transferencia creatorValidUpdateTransferencia(){
        return new Transferencia(1L, 500.0, EstadoTransferencia.CANCELADA, LocalDate.now(),
                new Conta());
    }
}
