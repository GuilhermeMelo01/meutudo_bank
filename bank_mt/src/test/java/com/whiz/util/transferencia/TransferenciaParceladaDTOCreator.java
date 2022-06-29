package com.whiz.util.transferencia;

import com.whiz.br.dto.TransferenciaParceladaDTO;

public class TransferenciaParceladaDTOCreator {

    public static TransferenciaParceladaDTO creatorVadidTransferenciaParcelada(){
        return new TransferenciaParceladaDTO(1L, 300.0, 3);
    }
}
