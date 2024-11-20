package com.example.GSjava.service;

import com.example.GSjava.model.Contrato;
import com.example.GSjava.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    // Lista de timeframes válidos
    private static final List<Integer> VALID_TIMEFRAMES = List.of(90, 180, 270, 360, 450, 540, 630, 720, 810);

    // Criar um novo contrato
    public Contrato createContrato(Contrato contrato) {
        if (!VALID_TIMEFRAMES.contains(contrato.getTimeframe())) {
            throw new IllegalArgumentException("Invalid timeframe! Available timeframes: " + VALID_TIMEFRAMES);
        }

        contrato.setContratoInicioTimestamp(System.currentTimeMillis() / 1000); // Timestamp atual em segundos
        return contratoRepository.save(contrato);
    }

    // Buscar contrato por UUID
    public Optional<Contrato> getContratoById(UUID contratoUuid) {
        return contratoRepository.findById(contratoUuid);
    }

    // Listar todos os contratos
    public List<Contrato> getAllContratos() {
        return contratoRepository.findAll();
    }

    // Deletar contrato (logicamente, alterando o status para "Cancelado")
    public Optional<Contrato> deleteContrato(UUID contratoUuid) {
        Optional<Contrato> contrato = contratoRepository.findById(contratoUuid);
        if (contrato.isPresent()) {
            Contrato contratoToCancel = contrato.get();
            if ("Cancelado".equals(contratoToCancel.getStatus())) {
                throw new IllegalArgumentException("This contract is already canceled!");
            }
            contratoToCancel.setStatus("Cancelado");
            return Optional.of(contratoRepository.save(contratoToCancel));
        }
        return Optional.empty();
    }
}
