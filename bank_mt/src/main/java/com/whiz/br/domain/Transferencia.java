package com.whiz.br.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whiz.br.enums.EstadoTransferencia;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Entity
@Builder
public class Transferencia implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private Integer estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    private Long idContaRecebedora;

    @OneToMany(mappedBy = "transferencia")
    private List<Parcela> parcelas = new ArrayList<>();


    public Transferencia() {
    }

    public Transferencia(Long id, Double valor, EstadoTransferencia estado, LocalDate data,
                         Conta conta, Conta contaRecebedora) {
        this.id = id;
        this.valor = valor;
        this.estado = (estado == null) ? null : estado.getCod();
        this.data = data;
        this.conta = conta;
        this.idContaRecebedora = contaRecebedora.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public EstadoTransferencia getEstado() {
        return EstadoTransferencia.toEnum(estado);
    }

    public void setEstado(EstadoTransferencia estado) {
        this.estado = estado.getCod();
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Long getIdContaRecebedora() {
        return idContaRecebedora;
    }

    public void setIdContaRecebedora(Long idContaRecebedora) {
        this.idContaRecebedora = idContaRecebedora;
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
