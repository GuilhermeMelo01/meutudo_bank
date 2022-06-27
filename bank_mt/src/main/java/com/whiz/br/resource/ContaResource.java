package com.whiz.br.resource;


import com.whiz.br.domain.Conta;
import com.whiz.br.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("contas")
public class ContaResource {

    @Autowired
    private ContaService contaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Conta>> findAll(){
        List<Conta> contas = contaService.findAll();
        return ResponseEntity.ok().body(contas);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Conta> findById(@PathVariable Long id){
        Conta conta = contaService.findById(id);
        return ResponseEntity.ok().body(conta);
    }

    @RequestMapping(value = "/balance/{id}",method = RequestMethod.GET)
    public ResponseEntity<String> findBalance(@PathVariable Long id){
        String saldo = contaService.findBalance(id);
        return ResponseEntity.ok().body(saldo);
    }
}
