package com.example.GSjava.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroConsumo {
    @Id
    @GeneratedValue
    private UUID registroConsumoUuid;

    @Column(nullable = false)
    private UUID instalacaoUuid;

    @Column(nullable = false)
    private double consumoKwh;

    @Column(nullable = false)
    private long medicaoTimestamp;

    @Column(nullable = false, updatable = false)
    private long createdAt = Instant.now().getEpochSecond();

    public double getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(double consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public long getMedicaoTimestamp() {
        return medicaoTimestamp;
    }

    public void setMedicaoTimestamp(long medicaoTimestamp) {
        this.medicaoTimestamp = medicaoTimestamp;
    }

    public UUID getInstalacaoUuid() {
        return instalacaoUuid;
    }

    public void setInstalacaoUuid(UUID instalacaoUuid) {
        this.instalacaoUuid = instalacaoUuid;
    }
}
