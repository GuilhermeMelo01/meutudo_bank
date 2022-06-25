package com.whiz.br.dto;

import com.whiz.br.domain.Transferencia;

import java.io.Serializable;

public class ReverterTransferenciaDTO implements Serializable {

    private Long idTransferencia;
    private Long idRecptorReembolso;
    private Long idAllowReembolso;

    public ReverterTransferenciaDTO() {
    }

    public ReverterTransferenciaDTO(Transferencia transferencia) {
        idTransferencia = transferencia.getId();
        idRecptorReembolso = transferencia.getConta().getId();
        idAllowReembolso = transferencia.getConta().getId();
    }

    public Long getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Long idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Long getIdRecptorReembolso() {
        return idRecptorReembolso;
    }

    public void setIdRecptorReembolso(Long idRecptorReembolso) {
        this.idRecptorReembolso = idRecptorReembolso;
    }

    public Long getIdAllowReembolso() {
        return idAllowReembolso;
    }

    public void setIdAllowReembolso(Long idAllowReembolso) {
        this.idAllowReembolso = idAllowReembolso;
    }
}
