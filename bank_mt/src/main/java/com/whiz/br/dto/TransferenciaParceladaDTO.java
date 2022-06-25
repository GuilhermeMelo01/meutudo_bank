package com.whiz.br.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class TransferenciaParceladaDTO implements Serializable {

    private Long idEnviadorTransferencia;
    private Double valor;
    private Integer numeroParcelas;

    public TransferenciaParceladaDTO(){
    }

    public TransferenciaParceladaDTO(Long idEnviadorTransferencia, Double valor,
                                     Integer numeroParcelas) {
        this.idEnviadorTransferencia = idEnviadorTransferencia;
        this.valor = valor;
        this.numeroParcelas = numeroParcelas;
    }

    public Long getIdEnviadorTransferencia() {
        return idEnviadorTransferencia;
    }

    public void setIdEnviadorTransferencia(Long idEnviadorTransferencia) {
        this.idEnviadorTransferencia = idEnviadorTransferencia;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
}
