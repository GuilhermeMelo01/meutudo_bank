package com.whiz.br.repository;

import com.whiz.br.domain.Transferencia;
import com.whiz.br.enums.EstadoTransferencia;
import com.whiz.util.TransferenciaCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.LocalDate;

@DataJpaTest
@DisplayName("Testes para a classe TransferenciaRepository")
class TransferenciaRepositoryTest {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Test
    @DisplayName("save persists Transferencia when successful")
    void save_PersistTransferencia_WhenSuccessful() {
        Transferencia savedtransferencia = TransferenciaCreator.creatorTransferenciaToBeSaved();
        this.transferenciaRepository.save(savedtransferencia);
        Assertions.assertThat(savedtransferencia).isNotNull();
        Assertions.assertThat(savedtransferencia.getId()).isNotNull();
    }

    @Test
    @DisplayName("save Transferencia check if the values are the same when successful")
    void save_TransferenciaCheckIfValuesSame_WhenSuccessful() {
        Transferencia savedtransferencia = TransferenciaCreator.creatorTransferenciaToBeSaved();
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getValue()).isEqualTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getValue());
        Assertions.assertThat(savedtransferencia.getEstado()).isEqualTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getEstado());
        Assertions.assertThat(savedtransferencia.getDate()).isEqualTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getDate());
        Assertions.assertThat(savedtransferencia.getValue()).isEqualTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getValue());
    }

    @Test
    @DisplayName("save Transferencia check if the value are greater than 0 and not null when successful")
    void save_TransferenciaCheckIfValueGreaterThan0AndNotNull_WhenSuccessful() {
        Transferencia savedtransferencia = TransferenciaCreator.creatorTransferenciaToBeSaved();
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getValue()).isNotNull();
        Assertions.assertThat(savedtransferencia.getValue()).isNotNull();
        Assertions.assertThat(savedtransferencia.getValue()).isGreaterThan(0);
    }

    @Test
    @DisplayName("save Transferencia check if the value are less than 1000.0 when successful")
    void save_TransferenciaCheckIfValueLessThan1000AndNotNull_WhenSuccessful() {
        Transferencia savedtransferencia = TransferenciaCreator.creatorTransferenciaToBeSaved();
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getValue()).isLessThan(1000);
    }

    @Test
    @DisplayName("update check if the all values of Transferencia has been modified when successful")
    void update_CheckIfAllValuesTransferenciaHasBeenModified_WhenSuccessful() {
        Transferencia savedtransferencia = TransferenciaCreator.creatorTransferenciaToBeSaved();
        savedtransferencia.setId(99L);
        savedtransferencia.setValue(400.0);
        savedtransferencia.setEstado(EstadoTransferencia.PROGRAMADA);
        savedtransferencia.setDate(LocalDate.now().plusMonths(1));
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getId()).isNotEqualTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getId());
        Assertions.assertThat(savedtransferencia.getValue()).isNotEqualTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getValue());
        Assertions.assertThat(savedtransferencia.getEstado()).isNotEqualTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getEstado());
        Assertions.assertThat(savedtransferencia.getDate()).isNotEqualTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getDate());
    }

    @Test
    @DisplayName("update Transferencia check if the value has been modified when successful")
    void update_TransferenciaCheckIfValueHasBeenModified_WhenSuccessful() {
        Transferencia savedtransferencia = TransferenciaCreator.creatorTransferenciaToBeSaved();
        savedtransferencia.setValue(400.0);
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getValue()).isNotEqualTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getValue());
    }

    @Test
    @DisplayName("update Transferencia check if the Estado has been modified when successful")
    void update_TransferenciaCheckIfEstadoHasBeenModified_WhenSuccessful() {
        Transferencia savedtransferencia = TransferenciaCreator.creatorTransferenciaToBeSaved();
        savedtransferencia.setEstado(EstadoTransferencia.CANCELADA);
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getEstado()).isNotEqualByComparingTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getEstado());
    }

    @Test
    @DisplayName("update check if new Transferencia date is after or equal to old Transferencia has been modified when successful")
    void update_CheckIfNewTransferenciaDateIsAfterOrEqualOldTransferencia_WhenSuccessful() {
        Transferencia savedtransferencia = TransferenciaCreator.creatorTransferenciaToBeSaved();
        savedtransferencia.setDate(TransferenciaCreator.creatorTransferenciaToBeSaved().getDate().plusMonths(1));
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getDate()).isAfterOrEqualTo(TransferenciaCreator.creatorTransferenciaToBeSaved().getDate());
    }

    @Test
    @DisplayName("exception find By Id Transferencia not find when return throw InvalidDataAccessApiUsageException successful")
    void exception_findByIdTransferenciaNotFind_WhenReturnThrowInvalidDataAccessApiUsageExceptionSuccessful() {
        Transferencia savedtransferencia = TransferenciaCreator.creatorTransferenciaToBeSaved();
        this.transferenciaRepository.delete(savedtransferencia);

        Assertions.assertThatThrownBy(() ->  this.transferenciaRepository.findById(savedtransferencia.getId()))
                .isInstanceOf(InvalidDataAccessApiUsageException.class);
    }



}