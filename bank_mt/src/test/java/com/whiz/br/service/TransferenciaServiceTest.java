package com.whiz.br.service;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.enums.EstadoTransferencia;
import com.whiz.br.repository.TransferenciaRepository;
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
class TransferenciaServiceTest {

    @InjectMocks
    private TransferenciaService transferenciaService;

    @Mock
    private TransferenciaRepository transferenciaRepositoryMock;

    @BeforeEach
    void setUp() {
        Transferencia transferenciaValidCreator = TransferenciaCreator.creatorValidTransferencia();
        Transferencia creatorValidContaWithTransferencia = TransferenciaCreator.creatorValidReverterTransferencia();

        BDDMockito.when(transferenciaRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(transferenciaValidCreator));

        BDDMockito.when(transferenciaRepositoryMock.findAll())
                .thenReturn(List.of(transferenciaValidCreator));

        BDDMockito.doReturn(List.of(creatorValidContaWithTransferencia)).when(transferenciaRepositoryMock)
                .saveAll(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("find by id return Transferencia by id when successful")
    void findById_ReturnTransferenciaById_WhenSuccessful() {
        Long exceptedId = TransferenciaCreator.creatorValidTransferencia().getId();
        Transferencia transferenciaBody = transferenciaService.findById(null);

        Assertions.assertThat(transferenciaBody).isNotNull();
        Assertions.assertThat(transferenciaBody.getId()).isEqualTo(exceptedId);
    }

    @Test
    @DisplayName("return List of Transferencia when successful")
    void findAll_ReturnListOfTransferencia_WhenSuccessful() {
        List<Transferencia> transferencias = transferenciaService.findAll();

        Assertions.assertThat(transferencias).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(transferencias.get(0)).isEqualTo(TransferenciaCreator.creatorValidTransferencia());
    }

    @Test
    @DisplayName("tranferencia check if Conta on Transferencia is different from null when successful")
    void tranferencia_CheckIfContaOnTransferenciaIsDifferentFromNull_WhenSuccessful() {
        Conta contaEnviador = ContaCreator.creatorValidConta();
        Conta contaRecebedor = ContaCreator.creatorUpdateValidConta();

        Assertions.assertThat(contaEnviador).isNotNull();
        Assertions.assertThat(contaRecebedor).isNotNull();
        Assertions.assertThat(contaEnviador.getId()).isNotEqualTo(contaRecebedor.getId());
        Assertions.assertThat(contaEnviador.getSaldo()).isNotNull();
    }

    @Test
    @DisplayName("transferencia and valor is different from null when successful")
    void transferencia_TransferenciaAndValorIsDifferentFromNull_WhenSuccessful() {
        Conta contaEnviador = ContaCreator.creatorValidContaWithTransferencia();

        Transferencia transferencia = contaEnviador.getTransferencias().get(0);

        Assertions.assertThat(contaEnviador.getTransferencias()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(transferencia.getValor()).isEqualTo(300.0);
        Assertions.assertThat(transferencia.getValor()).isGreaterThan(0);
    }

    @Test
    @DisplayName("reverter Transferencia check if ContaRecebedor saldo is greater is different from null when successful")
    void reverterTransferencia_CheckIfContaOnTransferenciaIsDifferentFromNull_WhenSuccessful() {
        Conta contaRecebedor = ContaCreator.creatorValidContaWithTransferencia();
        Transferencia transferencia = contaRecebedor.getTransferencias().get(0);
        contaRecebedor.setSaldo(contaRecebedor.getSaldo() + transferencia.getValor());

        Assertions.assertThat(contaRecebedor).isNotNull();
        Assertions.assertThat(contaRecebedor.getId()).isNotEqualTo(ContaCreator.creatorUpdateValidConta().getId());
        Assertions.assertThat(contaRecebedor.getSaldo()).isGreaterThan(0);
        Assertions.assertThat(contaRecebedor.getSaldo()).isGreaterThan(
                ContaCreator.creatorValidContaWithTransferencia().getSaldo());
    }

    @Test
    @DisplayName("reverter Transferencia check if Contar estadoTransferencia is Equal To CANCELADA when successful")
    void reverterTransferencia_CheckIfContaOnTransferenciaIsEqualToCancelada_WhenSuccessful() {
        Conta contaCancelada = ContaCreator.creatorValidContaWithTransferenciaCancelada();
        Transferencia transferencia = contaCancelada.getTransferencias().get(0);

        Assertions.assertThat(contaCancelada).isNotNull();
        Assertions.assertThat(transferencia.getEstado()).isEqualTo(EstadoTransferencia.CANCELADA);
    }

    @Test
    @DisplayName("Transferencia parcelada check if Transferencia has parcelas when successful")
    void ransferenciaParcelada_CheckIfTransferenciaHasParcelas_WhenSuccessful() {
        Transferencia transferencia = TransferenciaCreator.creatorValidTransferenciaWithParcela();

        Assertions.assertThat(transferencia.getParcelas()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(transferencia.getParcelas().get(0).getDataPagamento())
                .isAfter(transferencia.getData());
        Assertions.assertThat(transferencia.getEstado()).isEqualTo(EstadoTransferencia.PROGRAMADA);

    }
}