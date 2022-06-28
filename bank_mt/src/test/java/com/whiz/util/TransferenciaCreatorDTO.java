package com.whiz.util;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.enums.EstadoTransferencia;

import java.time.LocalDate;

public class TransferenciaCreatorDTO {

    public static NewTransferenciaDTO creatorNewTransferenciaDTOToBeSaved(){
        return new NewTransferenciaDTO(TransferenciaCreator.creatorTransferenciaToBeSaved(),
                TransferenciaCreator.creatorTransferenciaToBeSaved().getValue());
    }
}
