package com.whiz.br.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whiz.br.domain.Transferencia;

import java.io.Serializable;

public class NewTransferenciaDTO implements Serializable {

    private Long idEnv;
    private Long idRec;
    private Double value;

    public NewTransferenciaDTO() {
    }

    public NewTransferenciaDTO(Transferencia transferencia, Double value) {
        idEnv = transferencia.getConta().getId();
        idRec = transferencia.getConta().getId();
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
