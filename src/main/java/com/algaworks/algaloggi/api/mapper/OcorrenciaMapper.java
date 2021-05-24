package com.algaworks.algaloggi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.algaworks.algaloggi.api.model.OcorrenciaModel;
import com.algaworks.algaloggi.domain.model.Ocorrencia;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OcorrenciaMapper {

  private ModelMapper modelMapper;

  public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
    return modelMapper.map(ocorrencia, OcorrenciaModel.class);
  }

  public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias) {
    return ocorrencias.stream().map(this::toModel).collect(Collectors.toList());
  }

}
