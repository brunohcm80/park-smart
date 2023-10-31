package br.com.parksmart.controller.advice;

import br.com.parksmart.dto.response.ErroDetalhadoResponse;
import br.com.parksmart.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Arrays;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(CondutorInvalidoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroDetalhadoResponse> condutorInvalidoException(CondutorInvalidoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErroDetalhadoResponse("Condutor Inválido.",
                        HttpStatus.BAD_REQUEST.value(),
                        Arrays.asList(exception.getMessage()),
                        new Date().getTime()));
    }

    @ExceptionHandler(VeiculoInvalidoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroDetalhadoResponse> veiculoInvalidoException(VeiculoInvalidoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErroDetalhadoResponse("Veículo Inválido.",
                        HttpStatus.BAD_REQUEST.value(),
                        Arrays.asList(exception.getMessage()),
                        new Date().getTime()));
    }

    @ExceptionHandler(EstadiaInvalidaException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroDetalhadoResponse> estadiaInvalidaException(EstadiaInvalidaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErroDetalhadoResponse("Estadia Inválida.",
                        HttpStatus.BAD_REQUEST.value(),
                        Arrays.asList(exception.getMessage()),
                        new Date().getTime()));
    }

    @ExceptionHandler(CobrancaInvalidaException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroDetalhadoResponse> cobrancaInvalidaException(CobrancaInvalidaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErroDetalhadoResponse("Cobrança Inválida.",
                        HttpStatus.BAD_REQUEST.value(),
                        Arrays.asList(exception.getMessage()),
                        new Date().getTime()));
    }

    @ExceptionHandler(PrecoInvalidoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErroDetalhadoResponse> precoInvalidoException(PrecoInvalidoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ErroDetalhadoResponse("Tabela de Preço Inválida.",
                        HttpStatus.BAD_REQUEST.value(),
                        Arrays.asList(exception.getMessage()),
                        new Date().getTime()));
    }
}