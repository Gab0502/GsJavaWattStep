package com.example.GSjava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue
    private UUID clienteUuid;
    private String nome;
    private String endereco;
    private String documento;
    private String tipo;
    private String cep;
    private boolean ativo = true;

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}

