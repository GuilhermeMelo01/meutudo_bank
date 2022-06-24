package com.whiz.br.dto;

import com.whiz.br.domain.Conta;

import java.io.Serializable;

public class NewTransferenciaDTO implements Serializable {

    private Long idEnv;
    private Long idRec;
    private Double value;

    public NewTransferenciaDTO() {
    }

    public NewTransferenciaDTO(Conta conta, Double value) {
        idEnv = conta.getId();
        idRec = conta.getId();
        this.value = value;
    }

    public Long getIdEnv() {
        return idEnv;
    }

    public void setIdEnv(Long idEnv) {
        this.idEnv = idEnv;
    }

    public Long getIdRec() {
        return idRec;
    }

    public void setIdRec(Long idRec) {
        this.idRec = idRec;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
