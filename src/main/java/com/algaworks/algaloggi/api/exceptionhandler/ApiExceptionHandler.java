package com.algaworks.algaloggi.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.algaworks.algaloggi.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    List<Problema.Campo> campos = new ArrayList<>();
    Problema problema = new Problema();

    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      String nome = ((FieldError) error).getField();
      String mensagem = error.getDefaultMessage();

      campos.add(new Problema.Campo(nome, mensagem));
    }

    problema.setStatus(status.value());
    problema.setDataHora(LocalDateTime.now());
    problema.setTitulo("Um ou mais campos estão inválidos");
    problema.setCampos(campos);

    return handleExceptionInternal(ex, problema, headers, status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    Problema problema = new Problema();
    problema.setStatus(status.value());
    problema.setDataHora(LocalDateTime.now());
    problema.setTitulo(ex.getMessage());

    return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);

  }

}