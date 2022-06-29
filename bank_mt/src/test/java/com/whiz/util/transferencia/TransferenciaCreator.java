package com.whiz.util.transferencia;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Parcela;
import com.whiz.br.domain.Transferencia;
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

    public static Transferencia creatorValidTransferenciaWithParcela(){
        Transferencia transferencia = new Transferencia(1L, 300.0, EstadoTransferencia.PROGRAMADA, LocalDate.now(),
                new Conta());
        Parcela parcela = new Parcela(1L, 100.0, LocalDate.now().plusMonths(1), transferencia);
        transferencia.getParcelas().add(parcela);
        return transferencia;
    }

    public static Transferencia creatorValidReverterTransferencia(){
        return new Transferencia(2L, 500.0, EstadoTransferencia.CANCELADA, LocalDate.now(),
                new Conta());
    }

}
