package com.whiz.util.transferencia;

import com.whiz.br.dto.ReverterTransferenciaDTO;

public class TransferenciaDTOReverter {

    public static ReverterTransferenciaDTO creatorValidUpdateTransferencia() {
        return new ReverterTransferenciaDTO(TransferenciaCreator.creatorValidReverterTransferencia());
    }

    public static ReverterTransferenciaDTO creatorAnotherValidRevertTransferencia(){
        return new ReverterTransferenciaDTO(TransferenciaCreator.creatorValidTransferencia());
    }

}
