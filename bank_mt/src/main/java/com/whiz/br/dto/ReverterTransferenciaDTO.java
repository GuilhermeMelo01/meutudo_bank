package com.whiz.br.dto;

import com.whiz.br.domain.Transferencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReverterTransferenciaDTO implements Serializable {

    private Long idRecebedorReembolso;
    private Long idTransferencia;

    public ReverterTransferenciaDTO(Transferencia transferencia){}
}
