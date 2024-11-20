package com.example.GSjava.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {
    @Id
    @GeneratedValue
    private UUID contratoUuid;

    private UUID instalacaoUuid;
    private UUID clienteUuid;
    private int timeframe;
    private String status = "Ativo";
    private long contratoInicioTimestamp;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getContratoInicioTimestamp() {
        return contratoInicioTimestamp;
    }

    public void setContratoInicioTimestamp(long contratoInicioTimestamp) {
        this.contratoInicioTimestamp = contratoInicioTimestamp;
    }

    public int getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(int timeframe) {
        this.timeframe = timeframe;
    }
}
