package com.whiz.br.repository;

import com.whiz.br.domain.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
}
