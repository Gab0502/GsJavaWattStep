package com.example.GSjava.service;

import com.example.GSjava.model.Instalacao;
import com.example.GSjava.repository.InstalacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstalacaoService {

    @Autowired
    private InstalacaoRepository instalacaoRepository;

    // Criar uma nova instalação
    public Instalacao createInstalacao(Instalacao instalacao) {
        return instalacaoRepository.save(instalacao);
    }

    // Listar todas as instalações
    public List<Instalacao> getAllInstalacoes() {
        return instalacaoRepository.findAll();
    }

    // Buscar uma instalação por UUID
    public Optional<Instalacao> getInstalacaoById(UUID instalacaoUuid) {
        return instalacaoRepository.findById(instalacaoUuid);
    }

    // Deletar (logicamente) uma instalação
    public Optional<Instalacao> deleteInstalacao(UUID instalacaoUuid) {
        Optional<Instalacao> instalacao = instalacaoRepository.findById(instalacaoUuid);
        if (instalacao.isPresent()) {
            Instalacao instalacaoToDelete = instalacao.get();
            instalacaoToDelete.setAtivo(false); // Marca como inativo
            return Optional.of(instalacaoRepository.save(instalacaoToDelete));
        }
        return Optional.empty();
    }
}
