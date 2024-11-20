package com.example.GSjava.repository;

import com.example.GSjava.model.RegistroProducao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RegistroProducaoRepository extends JpaRepository<RegistroProducao, UUID> {
    List<RegistroProducao> findByInstalacaoUuidAndMedicaoTimestampBetween(
            UUID instalacaoUuid, long startTimestamp, long endTimestamp
    );
}
