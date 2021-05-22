package com.algaworks.algaloggi.api.controller;

import java.util.List;
import javax.validation.Valid;
import com.algaworks.algaloggi.domain.model.Cliente;
import com.algaworks.algaloggi.domain.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

  private ClienteRepository repository;

  @GetMapping
  public List<Cliente> listar() {
    return repository.findAll();
  }

  @GetMapping("/{clienteId}")
  public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
    // Optional<Cliente> cliente = repository.findById(clienteId);
    // if (!cliente.isPresent())
    // return ResponseEntity.notFound().build();
    // return ResponseEntity.ok(cliente.get());

    // return repository.findById(clienteId).map(cliente -> ResponseEntity.ok(cliente))
    // .orElse(ResponseEntity.notFound().build());

    return repository.findById(clienteId).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
    return repository.save(cliente);
  }

  @PutMapping("/{clienteId}")
  public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId,
      @RequestBody Cliente cliente) {

    if (!repository.existsById(clienteId)) {
      return ResponseEntity.notFound().build();
    }

    cliente.setId(clienteId);
    cliente = repository.save(cliente);
    return ResponseEntity.ok(cliente);
  }

  @DeleteMapping("/{clienteId}")
  public ResponseEntity<Void> remover(@Valid @PathVariable Long clienteId) {

    if (!repository.existsById(clienteId))
      return ResponseEntity.notFound().build();

    repository.deleteById(clienteId);

    return ResponseEntity.noContent().build();
  }
}
