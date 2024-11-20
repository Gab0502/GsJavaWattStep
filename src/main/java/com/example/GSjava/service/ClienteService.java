package com.example.GSjava.service;

import com.example.GSjava.model.Cliente;
import com.example.GSjava.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Criar novo cliente
    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Buscar cliente por UUID
    public Optional<Cliente> buscarCliente(UUID clienteUuid) {
        return clienteRepository.findById(clienteUuid);
    }

    // Deletar (logicamente) cliente
    public Optional<Cliente> deletarCliente(UUID clienteUuid) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteUuid);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setAtivo(false);
            clienteRepository.save(cliente);
            return Optional.of(cliente);
        }
        return Optional.empty();
    }
}
