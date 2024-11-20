package com.example.GSjava.controller;

import com.example.GSjava.model.Instalacao;
import com.example.GSjava.service.InstalacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/instalacoes")
public class InstalacaoController {

    @Autowired
    private InstalacaoService instalacaoService;

    // Endpoint POST para criar uma nova instalação
    @PostMapping
    public ResponseEntity<Instalacao> createInstalacao(@RequestBody Instalacao instalacao) {
        Instalacao savedInstalacao = instalacaoService.createInstalacao(instalacao);
        return new ResponseEntity<>(savedInstalacao, HttpStatus.CREATED);
    }

    // Endpoint GET para listar todas as instalações
    @GetMapping
    public ResponseEntity<List<Instalacao>> getAllInstalacoes() {
        List<Instalacao> instalacoes = instalacaoService.getAllInstalacoes();
        return new ResponseEntity<>(instalacoes, HttpStatus.OK);
    }

    // Endpoint GET para buscar uma instalação específica por UUID
    @GetMapping("/{instalacaoUuid}")
    public ResponseEntity<Instalacao> getInstalacaoById(@PathVariable UUID instalacaoUuid) {
        Optional<Instalacao> instalacao = instalacaoService.getInstalacaoById(instalacaoUuid);
        return instalacao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint DELETE para deletar uma instalação (logicamente)
    @DeleteMapping("/{instalacaoUuid}")
    public ResponseEntity<Instalacao> deleteInstalacao(@PathVariable UUID instalacaoUuid) {
        Optional<Instalacao> deletedInstalacao = instalacaoService.deleteInstalacao(instalacaoUuid);
        return deletedInstalacao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
