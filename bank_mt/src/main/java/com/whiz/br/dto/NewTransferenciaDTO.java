package com.whiz.br.dto;

import com.whiz.br.domain.Transferencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewTransferenciaDTO implements Serializable {

    private Long idEnviadorTransferencia;
    private Long idRecebedorTransferencia;
    private Double valorTransferencia;

    public NewTransferenciaDTO(Transferencia transferencia, Double valorTransferencia) {
        this.idEnviadorTransferencia = transferencia.getId();
        this.valorTransferencia = valorTransferencia;
    }
}
