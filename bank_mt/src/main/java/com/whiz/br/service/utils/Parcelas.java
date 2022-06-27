package com.whiz.br.service.utils;

import com.whiz.br.domain.Parcela;
import com.whiz.br.domain.Transferencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Parcelas {

    public static List<Parcela> newParcelas(Integer numeroParcelas, Double valorParcelas,
                                            LocalDate dataPagamentoPlus, Transferencia newTransferencia){
        List<Parcela> listaDeParcelas = new ArrayList<>();
        for (int i = 0; i < numeroParcelas; i++) {
            listaDeParcelas.add(new Parcela(null, valorParcelas, dataPagamentoPlus, newTransferencia));
            dataPagamentoPlus = dataPagamentoPlus.plusMonths(1);
        }
        newTransferencia.getParcelas().addAll(listaDeParcelas);
        return listaDeParcelas;
    }

}
