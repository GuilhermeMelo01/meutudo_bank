package com.whiz.br.dto;

import com.whiz.br.domain.Transferencia;

import java.io.Serializable;

public class ReverterTransferenciaDTO implements Serializable {

    private Long idTransferencia;
    private Long idRecebedorReembolso;

    public ReverterTransferenciaDTO() {
    }

    public ReverterTransferenciaDTO(Transferencia transferencia) {
        idTransferencia = transferencia.getId();
        idRecebedorReembolso = transferencia.getConta().getId();
    }

    public Long getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Long idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Long getIdRecebedorReembolso() {
        return idRecebedorReembolso;
    }

    public void setIdRecebedorReembolso(Long idRecebedorReembolso) {
        this.idRecebedorReembolso = idRecebedorReembolso;
    }

}
