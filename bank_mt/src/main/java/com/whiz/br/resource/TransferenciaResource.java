package com.whiz.br.resource;

import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.dto.ReverterTransferenciaDTO;
import com.whiz.br.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/reverter", method = RequestMethod.POST)
    public ResponseEntity<Void> reverter(@RequestBody ReverterTransferenciaDTO reverterTransferenciaDTO){
        transferenciaService.reverterTransferencia(reverterTransferenciaDTO);
        return ResponseEntity.noContent().build();
    }
}
