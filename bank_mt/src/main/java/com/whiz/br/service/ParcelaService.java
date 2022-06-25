package com.whiz.br.service;

import com.whiz.br.domain.Parcela;
import com.whiz.br.repository.ParcelaRepository;
import com.whiz.br.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelaService {

    @Autowired
    private ParcelaRepository parcelaRepository;
    @Autowired
    private ContaService contaService;
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private TransferenciaService transferenciaService;

    public Parcela findById(Long id){
        return parcelaRepository.findById(id).orElseThrow();
    }



}
