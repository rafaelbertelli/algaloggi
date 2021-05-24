package com.algaworks.algaloggi.api.controller;

import java.util.List;
import javax.validation.Valid;
import com.algaworks.algaloggi.api.mapper.EntregaMapper;
import com.algaworks.algaloggi.api.model.EntregaModel;
import com.algaworks.algaloggi.api.model.input.EntregaInput;
import com.algaworks.algaloggi.domain.model.Entrega;
import com.algaworks.algaloggi.domain.repository.EntregaRepository;
import com.algaworks.algaloggi.domain.service.SolicitacaoEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

  private SolicitacaoEntregaService solicitacaoEntregaService;
  private EntregaRepository entregaRepository;
  private EntregaMapper entregaMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
    Entrega novaEntrega = entregaMapper.toEntity(entregaInput);
    Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
    return entregaMapper.toModel(entregaSolicitada);
  }

  @GetMapping
  public List<EntregaModel> listar() {
    List<Entrega> listaSolicitada = entregaRepository.findAll();
    return entregaMapper.toCollectionModel(listaSolicitada);
  }

  @GetMapping("/{entregaId}")
  public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
    return entregaRepository.findById(entregaId)
        .map(entrega -> ResponseEntity.ok(entregaMapper.toModel(entrega)))
        .orElse(ResponseEntity.notFound().build());
  }

}
