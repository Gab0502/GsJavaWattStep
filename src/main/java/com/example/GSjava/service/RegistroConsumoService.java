package com.example.GSjava.service;

import com.example.GSjava.model.RegistroConsumo;
import com.example.GSjava.repository.RegistroConsumoRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.UUID;

@Service
public class RegistroConsumoService {
    private final RegistroConsumoRepository consumoRepository;

    public RegistroConsumoService(RegistroConsumoRepository consumoRepository) {
        this.consumoRepository = consumoRepository;
    }

    public RegistroConsumo criarRegistroConsumo(RegistroConsumo registroConsumo) {
        return consumoRepository.save(registroConsumo);
    }

    public List<RegistroConsumo> calcularConsumoMensal(UUID instalacaoUuid) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        long startOfMonthTimestamp = firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        long endOfMonthTimestamp = lastDayOfMonth.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

        return consumoRepository.findByInstalacaoUuidAndMedicaoTimestampBetween(
                instalacaoUuid, startOfMonthTimestamp, endOfMonthTimestamp
        );
    }

    public double calcularMedia(List<RegistroConsumo> registros) {
        if (registros.isEmpty()) return 0.0;

        RegistroConsumo primeiro = registros.get(0);
        RegistroConsumo ultimo = registros.get(registros.size() - 1);

        double consumoMensal = ultimo.getConsumoKwh() - primeiro.getConsumoKwh();
        long dias = Duration.between(
                Instant.ofEpochSecond(primeiro.getMedicaoTimestamp()),
                Instant.ofEpochSecond(ultimo.getMedicaoTimestamp())
        ).toDays();

        return dias > 0 ? consumoMensal / dias : consumoMensal;
    }
}
