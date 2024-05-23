package com.dock.dock.repository;

import com.dock.dock.entity.PortadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PortadorRepository extends JpaRepository<PortadorEntity, String> {

    Optional<PortadorEntity> findByCpf(String cpf);
}
