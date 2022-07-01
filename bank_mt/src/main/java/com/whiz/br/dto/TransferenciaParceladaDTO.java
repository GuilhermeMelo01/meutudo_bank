package com.whiz.br.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferenciaParceladaDTO implements Serializable {

    private Long idEnviadorTransferencia;
    private Long idRecebedorTransferencia;
    private Double valor;
    private Integer numeroParcelas;
}
