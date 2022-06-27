package com.whiz.br.service.utils;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Parcela;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.enums.EstadoTransferencia;
import com.whiz.br.service.exception.IllegalArgumentException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HelpTransferencia {

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

    public static void verificarIdEquals(Long idEnviador, Long idRecebedor) {
        if (idEnviador.equals(idRecebedor)) {
            throw new IllegalArgumentException(
                    "values cannot be equals! value(1): " + idEnviador + " value(2): " + idRecebedor);
        }
    }

    public static List<Parcela> newParcelas(Integer numeroParcelas, Double valorParcelas,
                                      LocalDate dataPagamentoPlus, Transferencia newTransferencia) {
        List<Parcela> listaDeParcelas = new ArrayList<>();
        for (int i = 0; i < numeroParcelas; i++) {
            listaDeParcelas.add(new Parcela(null, valorParcelas, dataPagamentoPlus, newTransferencia));
            dataPagamentoPlus = dataPagamentoPlus.plusMonths(1);
        }
        newTransferencia.getParcelas().addAll(listaDeParcelas);
        return listaDeParcelas;
    }

    public static Transferencia newTransferencia(Double value, EstadoTransferencia estado, LocalDate localDate, Conta conta) {
        return new Transferencia(null, value, estado, localDate, conta);
    }

    public static Transferencia newTransferenciaParcelada(Double value, EstadoTransferencia estado,
                                                   LocalDate localDate, Conta conta) {
        return new Transferencia(null, value, estado, localDate, conta);
    }

    public static void transferenciaCancelada(Transferencia transferencia, EstadoTransferencia estado) {
        transferencia.setEstado(estado);
    }

}
