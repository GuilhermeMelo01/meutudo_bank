package com.whiz.br.resource;


import com.whiz.br.domain.Cliente;
import com.whiz.br.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteResource {

    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok().body(clientes);
    }

}
