package com.dock.dock.repository;

import com.dock.dock.entity.ContaEntity;
import com.dock.dock.entity.PortadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<ContaEntity, Integer> {

    Optional<ContaEntity> findByNumero(Integer numero);
    Optional<ContaEntity> findByPortadorEntity(@Param("cpf_portador")PortadorEntity portadorEntity);
}
