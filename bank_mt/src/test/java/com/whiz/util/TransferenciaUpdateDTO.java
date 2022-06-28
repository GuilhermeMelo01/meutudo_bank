package com.whiz.util;

import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.dto.ReverterTransferenciaDTO;

public class TransferenciaUpdateDTO {

    public static ReverterTransferenciaDTO creatorNewTransferenciaDTOToBeSaved(){
        return new ReverterTransferenciaDTO(TransferenciaCreator.creatorValidUpdateTransferencia());
    }
}
