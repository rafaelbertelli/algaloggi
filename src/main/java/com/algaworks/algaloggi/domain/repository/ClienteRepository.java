package com.algaworks.algaloggi.domain.repository;

import java.util.List;
import com.algaworks.algaloggi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  List<Cliente> findByNome(String nome);

  List<Cliente> findByNomeContaining(String nome);
}
