package com.example.GSjava.repository;

import com.example.GSjava.model.Instalacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstalacaoRepository extends JpaRepository<Instalacao, UUID> {
}
