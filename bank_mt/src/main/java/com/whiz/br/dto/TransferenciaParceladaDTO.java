package com.whiz.br.dto;

import java.io.Serializable;

public class TransferenciaParceladaDTO implements Serializable {

    private Long idEnviadorTransferencia;
    private Long idRecebedorTransferencia;
    private Double valor;
    private Integer numeroParcelas;

    public TransferenciaParceladaDTO(){
    }

    public TransferenciaParceladaDTO(Long idEnviadorTransferencia, Long idRecebedorTransferencia, Double valor, Integer numeroParcelas) {
        this.idEnviadorTransferencia = idEnviadorTransferencia;
        this.idRecebedorTransferencia = idRecebedorTransferencia;
        this.valor = valor;
        this.numeroParcelas = numeroParcelas;
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
