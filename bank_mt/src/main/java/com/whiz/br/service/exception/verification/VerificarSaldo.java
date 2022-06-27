package com.whiz.br.service.exception.verification;

import com.whiz.br.service.exception.IllegalArgumentException;

import java.util.ArrayList;
import java.util.List;

public class VerificarSaldo {

    public static void verificarSaldo(Double saldoContaEnviador, Double valorTransferencia) {
        List<Integer> lista = new ArrayList<>();
        if (saldoContaEnviador <= 0) {
            lista.add(1);
        }
        if ((saldoContaEnviador - valorTransferencia) < 0) {
            lista.add(1);
        }
        if (lista.size() != 0) {
            throw new IllegalArgumentException("Não existe saldo para fazer esse procedimento");
        }
    }

}
