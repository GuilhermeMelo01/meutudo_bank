package com.whiz.br.resource;

import com.whiz.br.domain.Transferencia;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.dto.ReverterTransferenciaDTO;
import com.whiz.br.dto.TransferenciaParceladaDTO;
import com.whiz.br.service.TransferenciaService;
import com.whiz.util.transferencia.TransferenciaCreator;
import com.whiz.util.transferencia.TransferenciaDTOCreator;
import com.whiz.util.transferencia.TransferenciaParceladaDTOCreator;
import com.whiz.util.transferencia.TransferenciaDTOReverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class TransferenciaResourceTest {

    @InjectMocks //Utiliza o InjectMocks quando se quer testa a classe em si.
    private TransferenciaResource transferenciaResource;

    @Mock //Utilizar para todas as classes que estão sendo utilizadas dentro do TransferenciaResource(InjectMocks).
    private TransferenciaService transferenciaServiceMock;

    @BeforeEach
    void setUp() {
        Transferencia transferenciaValidCreator = TransferenciaCreator.creatorValidTransferencia();
        BDDMockito.when(transferenciaServiceMock.findById(ArgumentMatchers.any()))
                .thenReturn(transferenciaValidCreator);

        BDDMockito.when(transferenciaServiceMock.findAll())
                .thenReturn(List.of(transferenciaValidCreator));

        BDDMockito.doNothing().when(transferenciaServiceMock)
                .transferencia(ArgumentMatchers.any(NewTransferenciaDTO.class));

        BDDMockito.doNothing().when(transferenciaServiceMock)
                .reverterTransferencia(ArgumentMatchers.any(Long.class),
                        ArgumentMatchers.any(ReverterTransferenciaDTO.class));

        BDDMockito.doNothing().when(transferenciaServiceMock)
                .transferenciaParcelada(ArgumentMatchers.any(TransferenciaParceladaDTO.class));
    }

    @Test
    @DisplayName("find by id return Transferencia by id when successful")
    void findById_ReturnTransferenciaById_WhenSuccessful() {
        Long exceptedId = TransferenciaCreator.creatorValidTransferencia().getId();
        Transferencia transferenciaBody = transferenciaResource.findById(null).getBody();

        Assertions.assertThat(transferenciaBody).isNotNull();
        Assertions.assertThat(transferenciaBody.getId()).isEqualTo(exceptedId);
    }

    @Test
    @DisplayName("return List of Transferencia when successful")
    void findAll_ReturnListOfTransferencia_WhenSuccessful() {
        List<Transferencia> transferencias = transferenciaResource.findAll().getBody();

        Assertions.assertThat(transferencias).isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(transferencias.get(0)).isEqualTo(TransferenciaCreator.creatorValidTransferencia());
    }

    @Test
    @DisplayName("tranferencia insert Transferencia is different from null when successful")
    void tranferencia_InsertTransferenciaIsDifferentFromNull_WhenSuccessful() {

        Assertions.assertThatCode(() -> transferenciaResource.tranferencia(
                        TransferenciaDTOCreator.creatorNewTransferenciaDTOToBeSaved()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = transferenciaResource.tranferencia(
                TransferenciaDTOCreator.creatorNewTransferenciaDTOToBeSaved());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("reverter tranferencia idEnviadorReembolso is different from idRecebedorReembolso when successful")
    void reverterTransferencia_TransferenciaIdIsNotEqual_WhenSuccessful() {
        Long idEnviadorReembolso = TransferenciaCreator.creatorValidTransferencia().getId();
        Long idRecebedorReembolso = TransferenciaDTOReverter.creatorValidUpdateTransferencia().getIdRecebedorReembolso();

        Assertions.assertThatCode(() -> transferenciaResource.reverterTransferencia(
                TransferenciaCreator.creatorValidTransferencia().getId(),
                TransferenciaDTOReverter.creatorValidUpdateTransferencia()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = transferenciaResource.reverterTransferencia(
                TransferenciaCreator.creatorValidTransferencia().getId(),
                TransferenciaDTOReverter.creatorValidUpdateTransferencia());

        Assertions.assertThat(idEnviadorReembolso).isNotEqualTo(idRecebedorReembolso);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("tranferencia Parcelada insert Transferencia is different from null when successful")
    void tranferenciaParcelada_InsertTransferenciaIsDifferentFromNull_WhenSuccessful() {

        Assertions.assertThatCode(() -> transferenciaResource.tranferenciaParcelada(
                        TransferenciaParceladaDTOCreator.creatorVadidTransferenciaParcelada()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = transferenciaResource.tranferenciaParcelada(
                TransferenciaParceladaDTOCreator.creatorVadidTransferenciaParcelada());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}