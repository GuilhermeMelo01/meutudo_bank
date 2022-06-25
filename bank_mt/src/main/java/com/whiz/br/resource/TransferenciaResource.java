package com.whiz.br.resource;

import com.whiz.br.domain.Conta;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.dto.ReverterTransferenciaDTO;
import com.whiz.br.dto.TransferenciaParceladaDTO;
import com.whiz.br.service.ContaService;
import com.whiz.br.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaResource {

    @Autowired
    private TransferenciaService transferenciaService;

    @RequestMapping(value = "/transferir", method = RequestMethod.POST)
    public ResponseEntity<Void> tranferencia(@RequestBody NewTransferenciaDTO newTransferenciaDTO){
        transferenciaService.transferencia(newTransferenciaDTO);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{idEnviadorReembolso}/reverter", method = RequestMethod.PUT)
    public ResponseEntity<Void> reverter(@PathVariable Long idEnviadorReembolso,
                                         @RequestBody ReverterTransferenciaDTO reverterTransferenciaDTO){
        transferenciaService.reverterTransferencia(idEnviadorReembolso, reverterTransferenciaDTO);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/parcelada", method = RequestMethod.POST)
    public ResponseEntity<Void> tranferencia(@RequestBody TransferenciaParceladaDTO transferenciaParceladaDTO){
        transferenciaService.transferenciaParcelada(transferenciaParceladaDTO);
        return ResponseEntity.noContent().build();
    }
}
