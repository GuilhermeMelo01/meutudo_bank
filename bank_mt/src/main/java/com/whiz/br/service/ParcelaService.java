package com.whiz.br.service;

import com.whiz.br.domain.Parcela;
import com.whiz.br.repository.ParcelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParcelaService {

    private final ParcelaRepository parcelaRepository;

    public Parcela findById(Long id){
        return parcelaRepository.findById(id).orElseThrow();
    }
}
