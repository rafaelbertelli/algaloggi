package com.algaworks.algaloggi.domain.service;

import java.time.LocalDateTime;
import com.algaworks.algaloggi.domain.model.Cliente;
import com.algaworks.algaloggi.domain.model.Entrega;
import com.algaworks.algaloggi.domain.model.StatusEntrega;
import com.algaworks.algaloggi.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

  private EntregaRepository entregaRepository;

  private CatalogoClienteService catalogoClienteService;

  @Transactional
  public Entrega solicitar(Entrega entrega) {
    Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

    entrega.setCliente(cliente);
    entrega.setStatus(StatusEntrega.PENDENTE);
    entrega.setDataPedido(LocalDateTime.now());

    return entregaRepository.save(entrega);
  }

}
