package com.whiz.br.service;

import com.whiz.br.domain.Parcela;
import com.whiz.br.repository.ParcelaRepository;
import com.whiz.util.parcela.ParcelaCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ParcelaServiceTest {
    
    @InjectMocks
    private ParcelaService parcelaService;

    @Mock
    private ParcelaRepository parcelaRepositoryMock;

    @BeforeEach
    void setUp() {
        Parcela parcelaCreator = ParcelaCreator.creatorValidParcelaWithTransferencia();

        BDDMockito.when(parcelaRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(parcelaCreator));
    }

    @Test
    @DisplayName("find by id return Conta by id when successful")
    void findById_ReturnContaById_WhenSuccessful() {
        Long exceptedId = ParcelaCreator.creatorValidParcelaWithTransferencia().getId();
        Parcela parcela = parcelaService.findById(null);

        Assertions.assertThat(parcela).isNotNull();
        Assertions.assertThat(parcela.getId()).isEqualTo(exceptedId);
    }

}