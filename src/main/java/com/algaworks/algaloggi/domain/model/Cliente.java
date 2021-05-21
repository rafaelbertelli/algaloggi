package com.algaworks.algaloggi.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Cliente {

  @Id
  private Long id;

  private String nome;

  private String email;

  private String telefone;

}
