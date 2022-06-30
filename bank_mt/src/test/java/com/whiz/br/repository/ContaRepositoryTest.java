package com.whiz.br.repository;

import com.whiz.br.domain.Conta;
import com.whiz.br.domain.Transferencia;
import com.whiz.br.enums.EstadoTransferencia;
import com.whiz.br.service.ContaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@DisplayName("Testes para a classe ContaRepository")
class ContaRepositoryTest {

    @Autowired
    private ContaRepository contaRepository;

    @Test
    @DisplayName("save persists conta when successful")
    void save_PersistConta_WhenSuccessful() {
        Conta contaToBeSaved = createConta();
        Conta savedConta = this.contaRepository.save(contaToBeSaved);
        Assertions.assertThat(savedConta).isNotNull();
        Assertions.assertThat(savedConta.getId()).isNotNull();
        Assertions.assertThat(savedConta.getNumeroConta()).isEqualTo(contaToBeSaved.getNumeroConta());
        Assertions.assertThat(savedConta.getSaldo()).isGreaterThan(0);
    }

    @Test
    @DisplayName("save updates conta when successful")
    void save_UpdatesConta_WhenSuccessful() {
        Conta contaToBeSaved = createConta();
        Conta savedConta = this.contaRepository.save(contaToBeSaved);
        savedConta.setNumeroConta("8045");
        Conta updatedConta = this.contaRepository.save(savedConta);

        Assertions.assertThat(updatedConta).isNotNull();
        Assertions.assertThat(updatedConta.getId()).isNotNull();
        Assertions.assertThat(updatedConta.getNumeroConta()).isEqualTo(savedConta.getNumeroConta());
        Assertions.assertThat(updatedConta.getSaldo()).isGreaterThan(0);
    }

    @Test
    @DisplayName("save removes Conta when successful")
    void save_RemovesConta_WhenSuccessful() {
        Conta contaToBeSaved = createConta();
        Conta savedConta = this.contaRepository.save(contaToBeSaved);
        this.contaRepository.delete(savedConta);
        Optional<Conta> contaOptional = this.contaRepository.findById(savedConta.getId());

        Assertions.assertThat(contaOptional).isEmpty();
    }

    @Test
    @DisplayName("find By Id return Conta when successful")
    void findById_ReturnConta_WhenSuccessful() {
        Conta contaToBeSaved = createConta();
        Conta savedConta = this.contaRepository.save(contaToBeSaved);
        Optional<Conta> conta = this.contaRepository.findById(savedConta.getId());

        Assertions.assertThat(conta).isNotEmpty();
        Assertions.assertThat(conta).isNotNull();
        Assertions.assertThat(conta.get().getId()).isEqualTo(savedConta.getId());
    }

    @Test
    @DisplayName("find By Id return empty list when successful")
    void findById_ReturnEmptyList_WhenContaIsNotFound() {
        Optional<Conta> idNotExist = this.contaRepository.findById(234L);

        Assertions.assertThat(idNotExist).isEmpty();
    }

    @Test
    @DisplayName("save Conta and returns list Transferencia when Transferencia is not empty")
    void save_ContaReturnTransferenciaList_WhenTransferenciaIsNotEmpty() {
        Conta contaSaved = this.contaRepository.save(createConta());
        Transferencia transferencia = new Transferencia(null, 300.0,
                EstadoTransferencia.CONCLUIDA, LocalDate.now(), contaSaved, new Conta());
        contaSaved.getTransferencias().add(transferencia);

        Assertions.assertThat(contaSaved.getTransferencias().get(0)).isEqualTo(transferencia);
        Assertions.assertThat(contaSaved.getTransferencias()).isNotEmpty();
    }

    private Conta createConta() {
        return new Conta(null, "9043", 5000.0);
    }


}