package com.dock.dock.repository;

import com.dock.dock.domain.entity.ContaEntity;
import com.dock.dock.domain.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity , Integer> {
    List<TransacaoEntity> findByNumeroConta(@Param("numero_conta")ContaEntity numeroConta);

    @Query("SELECT t FROM TransacaoEntity t WHERE t.dataHoraTransacao BETWEEN :dataInicio AND :dataFim AND t.numeroConta = :numeroConta")
    List<TransacaoEntity> findByDataHoraTransacaoBetweenAndNumeroConta(@Param("dataInicio")LocalDateTime dataInicio,
                                                                       @Param("dataFim")LocalDateTime dataFim,
                                                                       @Param("numeroConta")ContaEntity numeroConta);


}
