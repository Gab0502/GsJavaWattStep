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
public class RegistroProducao {
    @Id
    @GeneratedValue
    private UUID registroProducaoUuid;

    @Column(nullable = false)
    private UUID instalacaoUuid;

    @Column(nullable = false)
    private double producaoKwh;

    @Column(nullable = false)
    private long medicaoTimestamp;

    @Column(nullable = false, updatable = false)
    private long createdAt = Instant.now().getEpochSecond(); // Data de criação automática
}
