package br.com.parksmart.controller;

import br.com.parksmart.dto.request.PrecoRequest;
import br.com.parksmart.dto.response.PrecoResponse;
import br.com.parksmart.exception.PrecoInvalidoException;
import br.com.parksmart.service.PrecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/precos")
public class PrecoController {

    @Autowired
    private PrecoService precoService;

    @Operation(description = "Cadastra uma nova tabela de preços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tabela de preços cadastrada")
    })
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<PrecoResponse> criarTabelaPrecos(@RequestBody @Valid PrecoRequest precoRequest, UriComponentsBuilder uriBuilder)
            throws PrecoInvalidoException {
        PrecoResponse precoResponse = precoService.criaTabelaPrecos(precoRequest);
        URI uriLocation = uriBuilder.path("/precos/consultar").buildAndExpand(precoResponse.getCodigoTabelaPreco()).toUri();

        return ResponseEntity.created(uriLocation).body(precoResponse);
    }

    @Operation(description = "Consulta tabela de preços vigente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna tabela de preços"),
            @ApiResponse(responseCode = "400", description = "Tabela de preços inexistente")
    })
    @GetMapping(value = "/consultar")
    public ResponseEntity<Optional<PrecoResponse>> consultarTabelaPrecos() {
        return ResponseEntity.ok().body(precoService.consultaTabelaPrecos());
    }

    @Operation(description = "Atualiza uma tabela de preços existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tabela de preços atualizada"),
            @ApiResponse(responseCode = "400", description = "Tabela de preços inválida")
    })
    @PutMapping(value = "/atualizar")
    public ResponseEntity<PrecoResponse> atualizarTabelaPrecos(@RequestBody @Valid PrecoRequest precoRequest)
            throws PrecoInvalidoException {
        return ResponseEntity.ok(precoService.atualizaTabelaPrecos(precoRequest));
    }
}