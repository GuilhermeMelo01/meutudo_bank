package com.whiz.br.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Parcela implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorDaParcela;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "transferencia_id")
    private Transferencia transferencia;

    public Parcela() {
    }

    public Parcela(Long id, Double valorDaParcela, LocalDate dataPagamento) {
        this.id = id;
        this.valorDaParcela = valorDaParcela;
        this.dataPagamento = dataPagamento;
    }

    public Parcela(Long id, Double valorDaParcela, LocalDate dataPagamento, Transferencia transferencia) {
        this.id = id;
        this.valorDaParcela = valorDaParcela;
        this.dataPagamento = dataPagamento;
        this.transferencia = transferencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorDaParcela() {
        return valorDaParcela;
    }

    public void setValorDaParcela(Double valorDaParcela) {
        this.valorDaParcela = valorDaParcela;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Transferencia getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Transferencia transferencia) {
        this.transferencia = transferencia;
    }
}
