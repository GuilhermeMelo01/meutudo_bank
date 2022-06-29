package com.whiz.util.transferencia;

import com.whiz.br.dto.NewTransferenciaDTO;

public class TransferenciaDTOCreator {

    public static NewTransferenciaDTO creatorNewTransferenciaDTOToBeSaved(){
        return new NewTransferenciaDTO(TransferenciaCreator.creatorTransferenciaToBeSaved(),
                TransferenciaCreator.creatorTransferenciaToBeSaved().getValor());
    }
}
