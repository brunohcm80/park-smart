package br.com.parksmart.controller;

import br.com.parksmart.dto.request.CondutorRequest;
import br.com.parksmart.dto.request.PrecoRequest;
import br.com.parksmart.dto.response.CondutorResponse;
import br.com.parksmart.dto.response.PrecoResponse;
import br.com.parksmart.exception.CondutorInvalidoException;
import br.com.parksmart.exception.PrecoInvalidoException;
import br.com.parksmart.service.CondutorService;
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
@RequestMapping("/condutor")
public class CondutorController {

    @Autowired
    private CondutorService service;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<CondutorResponse> cadastrarCondutor
            (@RequestBody @Valid CondutorRequest condutorRequest, UriComponentsBuilder uriBuilder)
            throws CondutorInvalidoException{

        CondutorResponse condutorResponse = service.cadastrarCondutor(condutorRequest);
        URI condutor = uriBuilder.path("/condutor/cadastrar/{cpf}").buildAndExpand(condutorResponse.getCpf()).toUri();

        return ResponseEntity.created(condutor).body(condutorResponse);
    }

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<CondutorResponse> detalharCondutores(@PathVariable String cpf)
            throws CondutorInvalidoException {
         return ResponseEntity.ok(service.obterCondutorPorCPF(cpf));
    }


    @GetMapping(value = "/buscarTodosCondutores")
    public Page<CondutorResponse> listarCondutores (@PageableDefault(size = 10) Pageable paginacao){

        Page<CondutorResponse> condutores = service.obterTodos(paginacao);

        return condutores;
    }

    @PutMapping(value = "/atualizar")
    public ResponseEntity<CondutorResponse> atualizarCondutor(@RequestBody @Valid CondutorRequest consultorRequest)
            throws CondutorInvalidoException {
        return ResponseEntity.ok(service.atualizaCondutor(consultorRequest));
    }

}
