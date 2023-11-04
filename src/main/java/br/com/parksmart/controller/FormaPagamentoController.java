package br.com.parksmart.controller;

import br.com.parksmart.dto.request.FormaPagamentoRequest;
import br.com.parksmart.dto.response.FormaPagamentoResponse;
import br.com.parksmart.exception.FormaPagamentoException;
import br.com.parksmart.service.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/formapagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService service;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<FormaPagamentoResponse> cadastrarFormaPagamento (@RequestBody @Valid FormaPagamentoRequest pagamentoRequest, UriComponentsBuilder uriBuilder) throws FormaPagamentoException {

        FormaPagamentoResponse pagamentoResponse = service.cadastrarFormaPagamento(pagamentoRequest);
        URI formaPagamento = uriBuilder.path("/formapagamento/cadastrar/{codigoFormaPagamento}").buildAndExpand(pagamentoResponse.getCodigoFormaPagamento()).toUri();

        return ResponseEntity.created(formaPagamento).body(pagamentoResponse);
    }

    @GetMapping("/buscar/{codigoFormaPagamento}")
    public ResponseEntity<FormaPagamentoResponse> detalharFormaPagamento(@PathVariable String codigoFormaPagamento)
            throws FormaPagamentoException {
         return ResponseEntity.ok(service.obterFormaPagamentoPorCodigo(codigoFormaPagamento));
    }


    @GetMapping(value = "/buscarTodos")
    public Page<FormaPagamentoResponse> listarTodos(@PageableDefault(size = 10) Pageable paginacao){

        Page<FormaPagamentoResponse> formasPagamento = service.obterTodos(paginacao);

        return formasPagamento;
    }


}
