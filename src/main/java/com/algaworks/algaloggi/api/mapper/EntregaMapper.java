package com.algaworks.algaloggi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.algaworks.algaloggi.api.model.EntregaModel;
import com.algaworks.algaloggi.api.model.input.EntregaInput;
import com.algaworks.algaloggi.domain.model.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntregaMapper {

  private ModelMapper modelMapper;

  public EntregaModel toModel(Entrega entrega) {
    return modelMapper.map(entrega, EntregaModel.class);
  }

  public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
    // return entregas.stream().map(entrega -> this.toModel(entrega)).collect(Collectors.toList());
    return entregas.stream().map(this::toModel).collect(Collectors.toList());
  }

  public Entrega toEntity(EntregaInput entregaInput) {
    return modelMapper.map(entregaInput, Entrega.class);
  }
}
