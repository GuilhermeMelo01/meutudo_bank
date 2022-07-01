package com.whiz.br.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Parcela implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setTransferencia(Transferencia transferencia) {
        this.transferencia = transferencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcela parcela = (Parcela) o;
        return Objects.equals(id, parcela.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
