package com.whiz.br.repository;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.enums.EstadoTransferencia;
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
        Transferencia savedtransferencia = novaTransferencia();
        this.transferenciaRepository.save(savedtransferencia);
        Assertions.assertThat(savedtransferencia).isNotNull();
        Assertions.assertThat(savedtransferencia.getId()).isNotNull();
    }

    @Test
    @DisplayName("save Transferencia check if the values are the same when successful")
    void save_TransferenciaCheckIfValuesSame_WhenSuccessful() {
        Transferencia savedtransferencia = novaTransferencia();
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getValue()).isEqualTo(novaTransferencia().getValue());
        Assertions.assertThat(savedtransferencia.getEstado()).isEqualTo(novaTransferencia().getEstado());
        Assertions.assertThat(savedtransferencia.getDate()).isEqualTo(novaTransferencia().getDate());
        Assertions.assertThat(savedtransferencia.getValue()).isEqualTo(novaTransferencia().getValue());
    }

    @Test
    @DisplayName("save Transferencia check if the value are greater than 0 and not null when successful")
    void save_TransferenciaCheckIfValueGreaterThan0AndNotNull_WhenSuccessful() {
        Transferencia savedtransferencia = novaTransferencia();
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getValue()).isNotNull();
        Assertions.assertThat(savedtransferencia.getValue()).isNotNull();
        Assertions.assertThat(savedtransferencia.getValue()).isGreaterThan(0);
    }

    @Test
    @DisplayName("save Transferencia check if the value are less than 1000.0 when successful")
    void save_TransferenciaCheckIfValueLessThan1000AndNotNull_WhenSuccessful() {
        Transferencia savedtransferencia = novaTransferencia();
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getValue()).isLessThan(1000);
    }

    @Test
    @DisplayName("update check if the all values of Transferencia has been modified when successful")
    void update_CheckIfAllValuesTransferenciaHasBeenModified_WhenSuccessful() {
        Transferencia savedtransferencia = novaTransferencia();
        savedtransferencia.setId(99L);
        savedtransferencia.setValue(400.0);
        savedtransferencia.setEstado(EstadoTransferencia.PROGRAMADA);
        savedtransferencia.setDate(LocalDate.now().plusMonths(1));
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getId()).isNotEqualTo(novaTransferencia().getId());
        Assertions.assertThat(savedtransferencia.getValue()).isNotEqualTo(novaTransferencia().getValue());
        Assertions.assertThat(savedtransferencia.getEstado()).isNotEqualTo(novaTransferencia().getEstado());
        Assertions.assertThat(savedtransferencia.getDate()).isNotEqualTo(novaTransferencia().getDate());
    }

    @Test
    @DisplayName("update Transferencia check if the value has been modified when successful")
    void update_TransferenciaCheckIfValueHasBeenModified_WhenSuccessful() {
        Transferencia savedtransferencia = novaTransferencia();
        savedtransferencia.setValue(400.0);
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getValue()).isNotEqualTo(novaTransferencia().getValue());
    }

    @Test
    @DisplayName("update Transferencia check if the Estado has been modified when successful")
    void update_TransferenciaCheckIfEstadoHasBeenModified_WhenSuccessful() {
        Transferencia savedtransferencia = novaTransferencia();
        savedtransferencia.setEstado(EstadoTransferencia.CANCELADA);
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getEstado()).isNotEqualByComparingTo(novaTransferencia().getEstado());
    }

    @Test
    @DisplayName("update check if new Transferencia date is after or equal to old Transferencia has been modified when successful")
    void update_CheckIfNewTransferenciaDateIsAfterOrEqualOldTransferencia_WhenSuccessful() {
        Transferencia savedtransferencia = novaTransferencia();
        savedtransferencia.setDate(novaTransferencia().getDate().plusMonths(1));
        this.transferenciaRepository.save(savedtransferencia);

        Assertions.assertThat(savedtransferencia.getDate()).isAfterOrEqualTo(novaTransferencia().getDate());
    }

    @Test
    @DisplayName("exception find By Id Transferencia not find when return throw InvalidDataAccessApiUsageException successful")
    void exception_findByIdTransferenciaNotFind_WhenReturnThrowInvalidDataAccessApiUsageExceptionSuccessful() {
        Transferencia savedtransferencia = novaTransferencia();
        this.transferenciaRepository.delete(savedtransferencia);

        Assertions.assertThatThrownBy(() ->  this.transferenciaRepository.findById(savedtransferencia.getId()))
                .isInstanceOf(InvalidDataAccessApiUsageException.class);
    }

    private Transferencia novaTransferencia(){
        return new Transferencia(null, 300.0, EstadoTransferencia.CONCLUIDA, LocalDate.now(),
                new Conta());
    }


}