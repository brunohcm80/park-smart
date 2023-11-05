package br.com.parksmart.controller;

import br.com.parksmart.dto.request.VeiculoRequest;
import br.com.parksmart.dto.response.VeiculoResponse;
import br.com.parksmart.exception.VeiculoInvalidoException;
import br.com.parksmart.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping(value = "/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Operation(description = "Cadastra um novo veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo cadastrado")
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<VeiculoResponse> cadastrarVeiculo(@RequestBody @Valid VeiculoRequest veiculoRequest,
                                                            UriComponentsBuilder uriBuilder) {
        VeiculoResponse veiculoResponse = veiculoService.cadastrarVeiculo(veiculoRequest);
        URI uriLocation = uriBuilder.path("/veiculos/buscar/{placaVeiculo}").buildAndExpand(veiculoResponse.getPlaca()).toUri();
        return ResponseEntity.created(uriLocation).body(veiculoResponse);
    }

    @Operation(description = "Consulta veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna veiculo"),
            @ApiResponse(responseCode = "400", description = "Veiculo inexistente")
    })
    @GetMapping("/buscar/{placaVeiculo}")
    public ResponseEntity<VeiculoResponse> obterVeiculoPorId(@PathVariable String placaVeiculo)
            throws VeiculoInvalidoException {
        return ResponseEntity.ok().body(veiculoService.obterVeiculoPorId(placaVeiculo));
    }

    @Operation(description = "Atualizar dados do veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna veiculo"),
            @ApiResponse(responseCode = "400", description = "Veiculo inexistente")
    })
    @PutMapping("/atualizar/{placaVeiculo}")
    public ResponseEntity<VeiculoResponse> atualizarVeiculo(
            @PathVariable String placaVeiculo, @RequestBody @Valid VeiculoRequest veiculoRequest)
            throws VeiculoInvalidoException {
        return ResponseEntity.ok().body(veiculoService.atualizarVeiculo(placaVeiculo, veiculoRequest));
    }

    @Operation(description = "Listar veículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna veiculos"),
            @ApiResponse(responseCode = "400", description = "Veiculos inexistentes")
    })
    @GetMapping("/buscarTodos")
    public ResponseEntity<Page<VeiculoResponse>> listarVeiculos(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok().body(veiculoService.listarVeiculos(paginacao));
    }
}