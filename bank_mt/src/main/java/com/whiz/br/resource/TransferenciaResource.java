package com.whiz.br.resource;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.dto.ReverterTransferenciaDTO;
import com.whiz.br.dto.TransferenciaParceladaDTO;
import com.whiz.br.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transferencias")
public class TransferenciaResource {

    private final TransferenciaService transferenciaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transferencia> findById(@PathVariable Long id) {
        Transferencia transferencia = transferenciaService.findById(id);
        return ResponseEntity.ok().body(transferencia);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Transferencia>> findAll() {
        List<Transferencia> transferencias = transferenciaService.findAll();
        return ResponseEntity.ok().body(transferencias);
    }

    @RequestMapping(value = "/transferir", method = RequestMethod.POST)
    public ResponseEntity<Void> transferencia(@RequestBody NewTransferenciaDTO newTransferenciaDTO) {
        Long idEnviadorTransferencia = newTransferenciaDTO.getIdEnviadorTransferencia();
        Long idRecebedorTransferencia = newTransferenciaDTO.getIdRecebedorTransferencia();
        Double valorTransferencia = newTransferenciaDTO.getValorTransferencia();
        transferenciaService.transferencia(idEnviadorTransferencia, idRecebedorTransferencia, valorTransferencia);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{idEnviadorReembolso}/reverter", method = RequestMethod.PUT)
    public ResponseEntity<Void> reverterTransferencia(@PathVariable Long idEnviadorReembolso,
                                                      @RequestBody ReverterTransferenciaDTO reverterTransferenciaDTO) {
        Long idRecebedorReembolso = reverterTransferenciaDTO.getIdRecebedorReembolso();
        Long idTransferencia = reverterTransferenciaDTO.getIdTransferencia();
        transferenciaService.reverterTransferencia(idEnviadorReembolso, idRecebedorReembolso, idTransferencia);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/parcelada", method = RequestMethod.POST)
    public ResponseEntity<Void> transferenciaParcelada(
            @RequestBody TransferenciaParceladaDTO transferenciaParceladaDTO) {
        Long idEnviadorTransferencia = transferenciaParceladaDTO.getIdEnviadorTransferencia();
        Long idRecebedorTransferencia = transferenciaParceladaDTO.getIdRecebedorTransferencia();
        Double valorTransferencia = transferenciaParceladaDTO.getValor();
        Integer numeroParcelas = transferenciaParceladaDTO.getNumeroParcelas();
        transferenciaService.transferenciaParcelada(idEnviadorTransferencia, idRecebedorTransferencia,
                valorTransferencia, numeroParcelas);
        return ResponseEntity.noContent().build();
    }
}
