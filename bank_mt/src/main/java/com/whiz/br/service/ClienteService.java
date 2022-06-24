package com.whiz.br.service;

import com.whiz.br.domain.Cliente;
import com.whiz.br.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }
}
