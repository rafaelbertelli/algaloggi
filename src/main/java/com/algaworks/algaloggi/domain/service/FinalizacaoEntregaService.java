package com.algaworks.algaloggi.domain.service;

import javax.transaction.Transactional;
import com.algaworks.algaloggi.domain.model.Entrega;
import com.algaworks.algaloggi.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FinalizacaoEntregaService {

  private BuscaEntregaService buscaEntregaService;
  private EntregaRepository entregaRepository;

  @Transactional
  public void finalizar(Long entregaId) {
    Entrega entrega = buscaEntregaService.buscar(entregaId);
    entrega.finalizar();
    entregaRepository.save(entrega);
  }

}
