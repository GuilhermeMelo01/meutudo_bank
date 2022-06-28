package com.whiz.br.dto;

import com.whiz.br.domain.Transferencia;

import java.io.Serializable;

public class NewTransferenciaDTO implements Serializable {

    private Long idEnviadorTransferencia;
    private Long idRecebedorTransferencia;
    private Double valorTransferencia;

    public NewTransferenciaDTO() {
    }

    public NewTransferenciaDTO(Transferencia transferencia, Double valorTransferencia) {
        idEnviadorTransferencia = transferencia.getConta().getId();
        idRecebedorTransferencia = transferencia.getConta().getId();
        this.valorTransferencia = valorTransferencia;
    }

    public Long getIdEnviadorTransferencia() {
        return idEnviadorTransferencia;
    }

    public void setIdEnviadorTransferencia(Long idEnviadorTransferencia) {
        this.idEnviadorTransferencia = idEnviadorTransferencia;
    }

    public Long getIdRecebedorTransferencia() {
        return idRecebedorTransferencia;
    }

    public void setIdRecebedorTransferencia(Long idRecebedorTransferencia) {
        this.idRecebedorTransferencia = idRecebedorTransferencia;
    }

    public Double getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(Double valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }
}
