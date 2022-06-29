package com.whiz.br.resource;

import com.whiz.br.domain.Conta;
import com.whiz.br.service.ContaService;
import com.whiz.util.conta.ContaCreator;
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

@ExtendWith(SpringExtension.class)
class ContaResourceTest {

    @InjectMocks 
    private ContaResource contaResource;

    @Mock
    private ContaService contaServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(contaServiceMock.findAll())
                .thenReturn(List.of(ContaCreator.creatorContaToBeSaved()));

        BDDMockito.when(contaServiceMock.findById(ArgumentMatchers.any()))
                .thenReturn(ContaCreator.creatorValidConta());

        BDDMockito.when(contaServiceMock.findBalance(ArgumentMatchers.any()))
                .thenReturn(ContaCreator.creatorSaldoValidConta());
    }

    @Test
    @DisplayName("return List of Conta when successful")
    void findAll_ReturnListOfConta_WhenSuccessful() {
        List<Conta> contas = contaResource.findAll().getBody();

        Assertions.assertThat(contas).isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(contas.get(0)).isEqualTo(ContaCreator.creatorContaToBeSaved());
        Assertions.assertThat(contas.get(0).getSaldo()).isEqualTo(ContaCreator.creatorContaToBeSaved().getSaldo());
        Assertions.assertThat(contas.get(0).getSaldo()).isGreaterThan(0);
    }

    @Test
    @DisplayName("find by id return Conta by id when successful")
    void findById_ReturnContaById_WhenSuccessful() {
        Long exceptedId = ContaCreator.creatorValidConta().getId();
        Conta ContaBody = contaResource.findById(null).getBody();

        Assertions.assertThat(ContaBody).isNotNull();
        Assertions.assertThat(ContaBody.getId()).isEqualTo(exceptedId);
        Assertions.assertThat(ContaBody.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("find Balance return String Balance is not null when successful")
    void findBalance_ReturnBalanceIsNotNull_WhenSuccessful() {
        String balanceBody = contaResource.findBalance(null).getBody();

        Assertions.assertThat(balanceBody).isNotNull();
        Assertions.assertThat(balanceBody).isEqualTo(ContaCreator.creatorSaldoValidConta());
    }

}