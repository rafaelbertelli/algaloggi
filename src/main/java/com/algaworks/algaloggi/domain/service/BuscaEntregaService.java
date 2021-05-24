package com.algaworks.algaloggi.domain.service;

import com.algaworks.algaloggi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algaloggi.domain.model.Entrega;
import com.algaworks.algaloggi.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

  private EntregaRepository entregaRepository;

  public Entrega buscar(Long entregaId) {
    return entregaRepository.findById(entregaId)
        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
  }

}
