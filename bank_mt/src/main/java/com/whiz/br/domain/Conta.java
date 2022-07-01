package com.whiz.br.domain;

import com.whiz.br.service.exception.IllegalArgumentException;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Conta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroConta;
    private Double saldo;

    @OneToMany(mappedBy = "conta")
    private List<Transferencia> transferencias = new ArrayList<>();

    public Conta(Long id, String numeroConta, Double saldo) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Double saquar(Double valorTransferencia, Conta contaRecebedora) {
        if (valorTransferencia <= 0) {
            throw new IllegalArgumentException("Valor da transferencia não pode ser 0");
        }
        if(contaRecebedora == null){
            throw new IllegalArgumentException("Conta não pode ser null");
        }
        if (this.saldo <= 0) {
            throw new IllegalArgumentException("Não existe saldo para fazer esse procedimento");
        }
        if ((this.saldo - valorTransferencia) < 0) {
            throw new IllegalArgumentException("Não existe saldo para fazer esse procedimento");
        }
        this.saldo -= valorTransferencia;
        contaRecebedora.saldo += valorTransferencia;
        return valorTransferencia;
    }

    public  boolean verificarIdEquals(Long idRecebedor) {
        if (this.id.equals(idRecebedor)) {
            throw new IllegalArgumentException(
                    "values cannot be equals! value(1): " + this+ " value(2): " + idRecebedor);
        }
        return true;
    }
}
