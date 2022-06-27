package com.whiz.br.service.utils;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.enums.EstadoTransferencia;

import java.time.LocalDate;

public interface HelpTransferencia {

    Transferencia newTransferencia
            (Double value, EstadoTransferencia estado, LocalDate localDate, Conta conta);

    Transferencia newTransferenciaParcelada
            (Double value, EstadoTransferencia estado, LocalDate localDate, Conta conta);

    void transferenciaCancelada(Transferencia transferencia, EstadoTransferencia estado);

}
