package com.algaworks.algaloggi.domain.service;

import javax.transaction.Transactional;
import com.algaworks.algaloggi.domain.model.Entrega;
import com.algaworks.algaloggi.domain.model.Ocorrencia;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

  private BuscaEntregaService buscaEntregaService;

  @Transactional
  public Ocorrencia registrar(Long entregaId, String descricao) {
    Entrega entrega = buscaEntregaService.buscar(entregaId);

    return entrega.adicionarOcorrencia(descricao);
  }

}
