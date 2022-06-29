package com.whiz.br.service;

import com.whiz.br.domain.Transferencia;
import com.whiz.br.dto.NewTransferenciaDTO;
import com.whiz.br.dto.ReverterTransferenciaDTO;
import com.whiz.br.dto.TransferenciaParceladaDTO;
import com.whiz.br.repository.ContaRepository;
import com.whiz.br.repository.ParcelaRepository;
import com.whiz.br.repository.TransferenciaRepository;
import com.whiz.util.conta.ContaCreator;
import com.whiz.util.transferencia.TransferenciaCreator;
import com.whiz.util.transferencia.TransferenciaDTOReverter;
import com.whiz.util.transferencia.TransferenciaParceladaDTOCreator;
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
//
//    @InjectMocks //Utiliza o InjectMocks quando se quer testa a classe em si.
//    private TransferenciaService transferenciaService;
//
//    @Mock //Utilizar para todas as classes que estão sendo utilizadas dentro do TransferenciaResource(InjectMocks).
//    private TransferenciaRepository transferenciaRepositoryMock;
//
//    @BeforeEach
//    void setUp() {
//        Transferencia transferenciaValidCreator = TransferenciaCreator.creatorValidTransferencia();
//        BDDMockito.when(transferenciaRepositoryMock.findById(ArgumentMatchers.any()))
//                .thenReturn(Optional.of(transferenciaValidCreator));
//
//        BDDMockito.when(transferenciaRepositoryMock.findAll())
//                .thenReturn(List.of(transferenciaValidCreator));
//
//        BDDMockito.doNothing().when(transferenciaRepositoryMock)
//                .transferencia(ArgumentMatchers.any(NewTransferenciaDTO.class));
//
//        BDDMockito.doNothing().when(transferenciaRepositoryMock)
//                .reverterTransferencia(ArgumentMatchers.any(Long.class),
//                        ArgumentMatchers.any(ReverterTransferenciaDTO.class));
//
//        BDDMockito.doNothing().when(transferenciaRepositoryMock)
//                .transferenciaParcelada(ArgumentMatchers.any(TransferenciaParceladaDTO.class));
//    }
//
//    @Test
//    @DisplayName("find by id return Transferencia by id when successful")
//    void findById_ReturnTransferenciaById_WhenSuccessful() {
//        Long exceptedId = TransferenciaCreator.creatorValidTransferencia().getId();
//        Transferencia transferenciaBody = transferenciaService.findById(null);
//
//        Assertions.assertThat(transferenciaBody).isNotNull();
//        Assertions.assertThat(transferenciaBody.getId()).isEqualTo(exceptedId);
//    }
//
//    @Test
//    @DisplayName("return List of Transferencia when successful")
//    void findAll_ReturnListOfTransferencia_WhenSuccessful() {
//        List<Transferencia> transferencias = transferenciaService.findAll();
//
//        Assertions.assertThat(transferencias).isNotNull()
//                .isNotEmpty()
//                .hasSize(1);
//        Assertions.assertThat(transferencias.get(0)).isEqualTo(TransferenciaCreator.creatorValidTransferencia());
//    }
//
//    @Test
//    @DisplayName("tranferencia insert Transferencia is different from null when successful")
//    void tranferencia_InsertTransferenciaIsDifferentFromNull_WhenSuccessful() {
//        Long idEnviador = ContaCreator.creatorValidConta().getId();
//        Long idRecebor = ContaCreator.creatorUpdateValidConta().getId();
//        List<Transferencia> transferencias = ContaCreator.creatorValidConta().getTransferencias();
//
//        Assertions.assertThat(idEnviador).isNotNull();
//        Assertions.assertThat(idRecebor).isNotNull();
//        Assertions.assertThat(idEnviador).isNotEqualTo(idRecebor);
//        Assertions.assertThat(transferencias).isNotNull();
//    }
//
////    @Test
////    @DisplayName("reverter tranferencia idEnviadorReembolso is different from idRecebedorReembolso when successful")
////    void reverterTransferencia_TransferenciaIdIsNotEqual_WhenSuccessful() {
////        Long idEnviadorReembolso = TransferenciaCreator.creatorValidTransferencia().getId();
////        Long idRecebedorReembolso = TransferenciaDTOReverter.creatorValidUpdateTransferencia().getIdRecebedorReembolso();
////
////        Assertions.assertThatCode(() -> transferenciaService.reverterTransferencia(
////                        TransferenciaCreator.creatorValidTransferencia().getId(),
////                        TransferenciaDTOReverter.creatorValidUpdateTransferencia()))
////                .doesNotThrowAnyException();
////
////        Assertions.assertThat(idEnviadorReembolso).isNotNull();
////        Assertions.assertThat(idRecebedorReembolso).isNotNull();
////        Assertions.assertThat(idEnviadorReembolso).isNotEqualTo(idRecebedorReembolso);
////    }
////
////    @Test
////    @DisplayName("tranferencia Parcelada insert Transferencia is different from null when successful")
////    void tranferenciaParcelada_InsertTransferenciaIsDifferentFromNull_WhenSuccessful() {
////
////        Assertions.assertThatCode(() -> transferenciaService.transferenciaParcelada(
////                        TransferenciaParceladaDTOCreator.creatorVadidTransferenciaParcelada()))
////                .doesNotThrowAnyException();
////
////        transferenciaService.transferenciaParcelada(
////                TransferenciaParceladaDTOCreator.creatorVadidTransferenciaParcelada());
////
////        Assertions.assertThat(TransferenciaParceladaDTOCreator.creatorVadidTransferenciaParcelada()).isNotNull();
////        Assertions.assertThat(TransferenciaParceladaDTOCreator.creatorVadidTransferenciaParcelada()
////                .getIdEnviadorTransferencia()).isGreaterThan(0);
////        Assertions.assertThat(TransferenciaParceladaDTOCreator.creatorVadidTransferenciaParcelada()
////                .getNumeroParcelas()).isEqualTo(3);
////    }
////

}