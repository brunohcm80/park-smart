package br.com.parksmart.controller;

import br.com.parksmart.dto.request.CobrancaRequest;
import br.com.parksmart.dto.request.EstadiaRequest;
import br.com.parksmart.dto.response.EstadiaResponse;
import br.com.parksmart.exception.*;
import br.com.parksmart.service.EstadiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/estadias")
public class EstadiaController {

    @Autowired
    private EstadiaService estadiaService;

    @PostMapping(value = "/iniciar")
    public ResponseEntity<EstadiaResponse> iniciarEstadia(@RequestBody @Valid EstadiaRequest estadiaRequest, UriComponentsBuilder uriBuilder)
            throws EstadiaInvalidaException, PrecoInvalidoException, CondutorInvalidoException,
            VeiculoInvalidoException {
        EstadiaResponse estadiaResponse = estadiaService.iniciarEstadia(estadiaRequest);
        URI uriLocation = uriBuilder.path("/estadias/buscar/{codigoEstadia}").buildAndExpand(estadiaResponse.getCodigoEstadia()).toUri();
        return ResponseEntity.created(uriLocation).body(estadiaResponse);
    }

    @GetMapping(value = "/buscar/{codigoEstadia}")
    public ResponseEntity<EstadiaResponse> obterEstadiaPorCodigo(@PathVariable String codigoEstadia)
            throws EstadiaInvalidaException {
        return ResponseEntity.ok(estadiaService.obterEstadiaPorCodigo(codigoEstadia));
    }

    @GetMapping(value = "/buscarTodas")
    public ResponseEntity<Page<EstadiaResponse>> listarEstadias(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(estadiaService.listarEstadias(paginacao));
    }

    @PutMapping(value = "/finalizar/{codigoEstadia}")
    public ResponseEntity<EstadiaResponse> finalizarEstadia(@PathVariable String codigoEstadia,
                                                            @RequestBody @Valid CobrancaRequest cobrancaRequest)
            throws EstadiaInvalidaException, CobrancaInvalidaException {
        return ResponseEntity.ok(estadiaService.finalizarEstadia(codigoEstadia, cobrancaRequest));
    }
}