package com.whiz.util.conta;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.enums.EstadoTransferencia;

import java.time.LocalDate;

public class ContaCreator {

    public static Conta creatorContaToBeSaved(){
        return new Conta(null, "8989", 2000.0);
    }

    public static Conta creatorValidConta(){
        return new Conta(1L, "8989", 2000.0);
    }

    public static Conta creatorValidContaWithTransferencia(){
        Conta contaEnv = new Conta(1L, "8989", 2000.0);
        Conta contaRec = new Conta(2L, "8549", 1000.0);
        Transferencia transferencia = new Transferencia(1L, 300.0, EstadoTransferencia.CONCLUIDA,
                LocalDate.now(), contaEnv, contaRec);
        contaEnv.getTransferencias().add(transferencia);
        return contaEnv;
    }

    public static Conta creatorValidContaWithTransferenciaCancelada(){
        Conta contaEnv = new Conta(1L, "8989", 2000.0);
        Conta contaRec = new Conta(2L, "8549", 1000.0);
        Transferencia transferencia = new Transferencia(1L, 300.0, EstadoTransferencia.CANCELADA,
                LocalDate.now(), contaEnv, contaRec);
        contaEnv.getTransferencias().add(transferencia);
        return contaEnv;
    }

    public static Conta creatorUpdateValidConta(){
        return new Conta(3L, "8989", 2000.0);
    }

    public static String creatorSaldoValidConta(){
        return "Numero da conta: "+ creatorValidConta().getNumeroConta() +
                " - Saldo da conta: " + creatorValidConta().getSaldo();
    }
}
