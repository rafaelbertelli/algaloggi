package com.algaworks.algaloggi.api.controller;

import java.util.List;
import javax.validation.Valid;
import com.algaworks.algaloggi.api.mapper.OcorrenciaMapper;
import com.algaworks.algaloggi.api.model.OcorrenciaModel;
import com.algaworks.algaloggi.api.model.input.OcorrenciaInput;
import com.algaworks.algaloggi.domain.model.Entrega;
import com.algaworks.algaloggi.domain.model.Ocorrencia;
import com.algaworks.algaloggi.domain.service.BuscaEntregaService;
import com.algaworks.algaloggi.domain.service.RegistroOcorrenciaService;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

  private RegistroOcorrenciaService registroOcorrenciaService;
  private OcorrenciaMapper ocorrenciaMapper;
  private BuscaEntregaService buscaEntregaService;

  @GetMapping
  public List<OcorrenciaModel> listar(@Valid @PathVariable Long entregaId) {
    Entrega entrega = buscaEntregaService.buscar(entregaId);
    return ocorrenciaMapper.toCollectionModel(entrega.getOcorrencias());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public OcorrenciaModel registrar(@Valid @PathVariable Long entregaId,
      @RequestBody OcorrenciaInput ocorrenciaInput) {

    Ocorrencia ocorrenciaRegistrada =
        registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

    return ocorrenciaMapper.toModel(ocorrenciaRegistrada);
  }

}
