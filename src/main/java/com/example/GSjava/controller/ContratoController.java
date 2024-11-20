package com.example.GSjava.controller;

import com.example.GSjava.model.Contrato;
import com.example.GSjava.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    // Endpoint POST para criar um novo contrato
    @PostMapping
    public ResponseEntity<?> createContrato(@RequestBody Contrato contrato) {
        try {
            Contrato savedContrato = contratoService.createContrato(contrato);
            return new ResponseEntity<>(savedContrato, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse("INVALID_TIMEFRAME", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint GET para buscar um contrato específico pelo UUID
    @GetMapping("/{contratoUuid}")
    public ResponseEntity<?> getContratoById(@PathVariable UUID contratoUuid) {
        Optional<Contrato> contrato = contratoService.getContratoById(contratoUuid);
        return contrato.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint DELETE para deletar um contrato (logicamente, status = "Cancelado")
    @DeleteMapping("/{contratoUuid}")
    public ResponseEntity<?> deleteContrato(@PathVariable UUID contratoUuid) {
        try {
            Optional<Contrato> deletedContrato = contratoService.deleteContrato(contratoUuid);
            return deletedContrato.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ErrorResponse("INVALID_DELETE_REQUEST", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint GET para listar todos os contratos
    @GetMapping
    public ResponseEntity<List<Contrato>> getAllContratos() {
        List<Contrato> contratos = contratoService.getAllContratos();
        return new ResponseEntity<>(contratos, HttpStatus.OK);
    }
}
