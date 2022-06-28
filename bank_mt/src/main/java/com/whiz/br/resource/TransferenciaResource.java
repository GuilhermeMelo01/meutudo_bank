package com.whiz.br.resource;

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
    public ResponseEntity<Transferencia> findById(@PathVariable Long id){
        Transferencia transferencia = transferenciaService.findById(id);
        return ResponseEntity.ok().body(transferencia);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Transferencia>> findAll(){
        List<Transferencia> transferencias = transferenciaService.listAll();
        return ResponseEntity.ok().body(transferencias);
    }


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
    public ResponseEntity<Void> tranferenciaParcelada(@RequestBody TransferenciaParceladaDTO transferenciaParceladaDTO){
        transferenciaService.transferenciaParcelada(transferenciaParceladaDTO);
        return ResponseEntity.noContent().build();
    }
}
