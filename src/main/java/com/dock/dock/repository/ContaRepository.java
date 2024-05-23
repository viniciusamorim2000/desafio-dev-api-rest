package com.dock.dock.repository;

import com.dock.dock.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<ContaEntity, Integer> {

    Optional<ContaEntity> findByNumero(Integer numero);
}
