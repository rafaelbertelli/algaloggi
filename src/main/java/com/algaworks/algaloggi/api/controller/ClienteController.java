package com.algaworks.algaloggi.api.controller;

import java.util.Arrays;
import java.util.List;
import com.algaworks.algaloggi.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

  @GetMapping("/clientes")
  public List<Cliente> listar() {
    Cliente cliente1 =
        Cliente.builder().id(1L).nome("JoãoX").email("joao@gmail.com").telefone("2345678").build();

    Cliente cliente2 = Cliente.builder().id(2L).nome("MariaX").email("maria@gmail.com")
        .telefone("123465789").build();

    System.out.println(cliente1.toString());
    System.out.println(cliente1.equals(cliente2));

    return Arrays.asList(cliente1, cliente2);
  }

}
