package com.example.GSjava.service;

import com.example.GSjava.model.RegistroProducao;
import com.example.GSjava.repository.RegistroProducaoRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.UUID;

@Service
public class RegistroProducaoService {
    private final RegistroProducaoRepository producaoRepository;

    public RegistroProducaoService(RegistroProducaoRepository producaoRepository) {
        this.producaoRepository = producaoRepository;
    }

    public RegistroProducao criarRegistroProducao(RegistroProducao registroProducao) {
        return producaoRepository.save(registroProducao);
    }

    public List<RegistroProducao> calcularProducaoMensal(UUID instalacaoUuid) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        long startOfMonthTimestamp = firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        long endOfMonthTimestamp = lastDayOfMonth.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        return producaoRepository.findByInstalacaoUuidAndMedicaoTimestampBetween(
                instalacaoUuid, startOfMonthTimestamp, endOfMonthTimestamp
        );
    }
}
