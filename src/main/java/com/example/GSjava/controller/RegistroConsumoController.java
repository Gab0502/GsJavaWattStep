package com.example.GSjava.controller;

import com.example.GSjava.model.RegistroConsumo;
import com.example.GSjava.service.RegistroConsumoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/consumo")
public class RegistroConsumoController {
    private final RegistroConsumoService consumoService;

    public RegistroConsumoController(RegistroConsumoService consumoService) {
        this.consumoService = consumoService;
    }

    @PostMapping
    public ResponseEntity<RegistroConsumo> criarRegistroConsumo(@RequestBody RegistroConsumo registroConsumo) {
        RegistroConsumo novoRegistro = consumoService.criarRegistroConsumo(registroConsumo);
        return ResponseEntity.ok(novoRegistro);
    }

    @GetMapping("/{instalacaoUuid}")
    public ResponseEntity<?> calcularConsumoMensal(@PathVariable UUID instalacaoUuid) {
        List<RegistroConsumo> registros = consumoService.calcularConsumoMensal(instalacaoUuid);

        if (registros.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum registro encontrado para a instalação.");
        }

        RegistroConsumo primeiro = registros.get(0);
        RegistroConsumo ultimo = registros.get(registros.size() - 1);
        double consumoMensal = ultimo.getConsumoKwh() - primeiro.getConsumoKwh();
        double consumoDiario = consumoService.calcularMedia(registros);
        double consumoEstimado = consumoDiario * Instant.ofEpochSecond(ultimo.getMedicaoTimestamp()).atZone(ZoneId.systemDefault())
                .toLocalDate()
                .lengthOfMonth();

        return ResponseEntity.ok(new Object() {
            public UUID instalacaoUuid = ultimo.getInstalacaoUuid();
            public long timestampCalculo = Instant.now().getEpochSecond();
            public String diaReferencia = String.valueOf(Instant.now().atZone(ZoneId.systemDefault()).getDayOfMonth());
            public String mesReferencia = Instant.now().atZone(ZoneId.systemDefault()).getMonth().name();
            public int diasParaAcabarOMes = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().lengthOfMonth() -
                    Instant.now().atZone(ZoneId.systemDefault()).getDayOfMonth();
            public double consumoMensalMedioKwh = consumoMensal;
            public double consumoDiarioMedioKwh = consumoDiario;
            public double consumoMensalEstimadoKwh = consumoEstimado;
        });
    }
}
