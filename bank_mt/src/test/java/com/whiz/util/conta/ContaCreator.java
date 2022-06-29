package com.whiz.util.conta;

import com.whiz.br.domain.Conta;

public class ContaCreator {

    public static Conta creatorContaToBeSaved(){
        return new Conta(null, "8989", 2000.0);
    }

    public static Conta creatorValidConta(){
        return new Conta(1L, "8989", 2000.0);
    }

    public static Conta creatorUpdateValidConta(){
        return new Conta(3L, "8989", 2000.0);
    }

    public static String creatorSaldoValidConta(){
        return "Numero da conta: "+ creatorValidConta().getNumeroConta() +
                " - Saldo da conta: " + creatorValidConta().getSaldo();
    }
}
