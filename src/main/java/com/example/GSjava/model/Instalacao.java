package com.example.GSjava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instalacao {
    @Id
    @GeneratedValue
    private UUID instalacaoUuid;
    private String endereco;
    private String cep;
    private boolean ativo = true;

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
