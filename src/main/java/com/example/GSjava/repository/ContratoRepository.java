package com.example.GSjava.repository;

import com.example.GSjava.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
}
