package com.whiz.br.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whiz.br.enums.EstadoTransferencia;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Transferencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;
    private Integer estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @OneToMany(mappedBy = "transferencia")
    private List<Parcela> parcelas = new ArrayList<>();


    public Transferencia() {
    }

    public Transferencia(Long id, Double value, EstadoTransferencia estado, LocalDate date, Conta conta) {
        this.id = id;
        this.value = value;
        this.estado = (estado == null) ? null : estado.getCod();
        this.date = date;
        this.conta = conta;
    }

    public Transferencia(Long id, EstadoTransferencia estado) {
        this.id = id;
        this.estado = (estado == null) ? null : estado.getCod();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public EstadoTransferencia getEstado() {
        return EstadoTransferencia.toEnum(estado);
    }

    public void setEstado(EstadoTransferencia estado) {
        this.estado = estado.getCod();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transferencia that = (Transferencia) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
