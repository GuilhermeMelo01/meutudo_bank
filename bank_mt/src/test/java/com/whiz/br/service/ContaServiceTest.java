package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.repository.ContaRepository;
import com.whiz.util.conta.ContaCreator;
import com.whiz.util.transferencia.TransferenciaCreator;
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

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepositoryMock;

    @BeforeEach
    void setUp() {
        Conta contaCreator = ContaCreator.creatorValidConta();

        BDDMockito.when(contaRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(contaCreator));

        BDDMockito.when(contaRepositoryMock.findAll())
                .thenReturn(List.of(contaCreator));
    }

    @Test
    @DisplayName("find by id return Conta by id when successful")
    void findById_ReturnContaById_WhenSuccessful() {
        Long exceptedId = ContaCreator.creatorValidConta().getId();
        Conta conta = contaService.findById(null);

        Assertions.assertThat(conta).isNotNull();
        Assertions.assertThat(conta.getId()).isEqualTo(exceptedId);
    }

    @Test
    @DisplayName("return List of Conta when successful")
    void findAll_ReturnListOfConta_WhenSuccessful() {
        List<Conta> contas = contaService.findAll();

        Assertions.assertThat(contas).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(contas.get(0)).isEqualTo(ContaCreator.creatorValidConta());
    }
}