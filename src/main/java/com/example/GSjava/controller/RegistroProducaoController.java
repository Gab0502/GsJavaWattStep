package com.example.GSjava.controller;

import com.example.GSjava.model.RegistroProducao;
import com.example.GSjava.service.RegistroProducaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/producao")
public class RegistroProducaoController {
    private final RegistroProducaoService producaoService;

    public RegistroProducaoController(RegistroProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    @PostMapping
    public ResponseEntity<RegistroProducao> criarRegistroProducao(@RequestBody RegistroProducao registroProducao) {
        RegistroProducao novoRegistro = producaoService.criarRegistroProducao(registroProducao);
        return ResponseEntity.ok(novoRegistro);
    }

    @GetMapping("/{instalacaoUuid}")
    public ResponseEntity<?> calcularProducaoMensal(@PathVariable UUID instalacaoUuid) {
        List<RegistroProducao> registros = producaoService.calcularProducaoMensal(instalacaoUuid);

        if (registros.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum registro encontrado para a instalação.");
        }

        return ResponseEntity.ok(registros);
    }
}
