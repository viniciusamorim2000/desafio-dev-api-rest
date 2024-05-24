package com.dock.dock.repository;

import com.dock.dock.domain.entity.ContaEntity;
import com.dock.dock.domain.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity , Integer> {
    List<TransacaoEntity> findByNumeroConta(@Param("numero_conta")ContaEntity numeroConta);

//    @Query(value = "SELECT \tt.id_transacao, \n" +
//            "\t\tc.numero, \n" +
//            "\t\tc.saldo, \n" +
//            "\t\tt.data_hora, \n" +
//            "\t\tt.tipo, \n" +
//            "\t\tt.valor_transacao \n" +
//            "\t\tFROM  transacao t, Conta c\n" +
//            "   where \n" +
//            "\t\tc.numero = t.numero_conta ", nativeQuery = true)
//    List<ExtratoModel> buscar
}
