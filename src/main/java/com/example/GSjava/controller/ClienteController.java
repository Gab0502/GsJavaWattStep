package com.example.GSjava.controller;

import com.example.GSjava.model.Cliente;
import com.example.GSjava.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // POST: Criar cliente
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente clienteCriado = clienteService.criarCliente(cliente);
        return new ResponseEntity<>(clienteCriado, HttpStatus.CREATED);
    }

    // GET: Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // GET: Buscar cliente por UUID
    @GetMapping("/{clienteUuid}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable UUID clienteUuid) {
        Optional<Cliente> clienteOptional = clienteService.buscarCliente(clienteUuid);
        if (clienteOptional.isPresent()) {
            return new ResponseEntity<>(clienteOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: Deletar cliente logicamente (setar ativo como false)
    @DeleteMapping("/{clienteUuid}")
    public ResponseEntity<Cliente> deletarCliente(@PathVariable UUID clienteUuid) {
        Optional<Cliente> clienteOptional = clienteService.deletarCliente(clienteUuid);
        if (clienteOptional.isPresent()) {
            return new ResponseEntity<>(clienteOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
