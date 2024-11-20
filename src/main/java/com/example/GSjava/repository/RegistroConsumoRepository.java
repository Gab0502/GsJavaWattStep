package com.example.GSjava.repository;

import com.example.GSjava.model.RegistroConsumo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RegistroConsumoRepository extends JpaRepository<RegistroConsumo, UUID> {
    List<RegistroConsumo> findByInstalacaoUuidAndMedicaoTimestampBetween(
            UUID instalacaoUuid, long startTimestamp, long endTimestamp
    );
}
