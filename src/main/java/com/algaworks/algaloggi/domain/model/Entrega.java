package com.algaworks.algaloggi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import com.algaworks.algaloggi.domain.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Valid
  @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
  @NotNull
  @ManyToOne
  private Cliente cliente;

  @Valid
  @NotNull
  @Embedded
  private Destinatario destinatario;

  @NotNull
  private BigDecimal taxa;

  @Enumerated(EnumType.STRING)
  private StatusEntrega status;

  private OffsetDateTime dataPedido;

  private OffsetDateTime dataFinalizacao;

  @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
  private List<Ocorrencia> ocorrencias = new ArrayList<>();

  public Ocorrencia adicionarOcorrencia(String descricao) {
    Ocorrencia ocorrencia = new Ocorrencia();
    ocorrencia.setDescricao(descricao);
    ocorrencia.setDataRegistro(OffsetDateTime.now());
    ocorrencia.setEntrega(this);

    this.getOcorrencias().add(ocorrencia);

    return ocorrencia;
  }

}
