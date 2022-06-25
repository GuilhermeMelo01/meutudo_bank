package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.repository.TransferenciaRepository;
import com.whiz.br.service.exception.IllegalArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public List<Conta> findAll() {
        return contaRepository.findAll();
    }

    public Conta findById(Long id) {
        return contaRepository.findById(id).orElseThrow();
    }

    public Double findBalance(Long id) {
        Conta conta = contaRepository.findById(id).orElseThrow();
        return conta.getSaldo();
    }

}
