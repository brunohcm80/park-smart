package br.com.parksmart.controller;

import br.com.parksmart.dto.request.PrecoRequest;
import br.com.parksmart.dto.response.PrecoResponse;
import br.com.parksmart.service.PrecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/precos")
public class PrecoController {

    @Autowired
    private PrecoService precoService;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<PrecoResponse> criarTabelaPrecos (@RequestBody @Valid PrecoRequest precoRequest) {
        return ResponseEntity.ok(precoService.criaTabelaPrecos(precoRequest));
    }

    @GetMapping(value = "/consultar")
    public ResponseEntity<Optional<PrecoResponse>> consultarTabelaPrecos () {
        return ResponseEntity.ok().body(precoService.consultaTabelaPrecos());
    }

    @PutMapping (value = "/atualizar")
    public ResponseEntity<PrecoResponse> atualizarTabelaPrecos (@RequestBody @Valid PrecoRequest precoRequest){
        return ResponseEntity.ok(precoService.atualizaTabelaPrecos(precoRequest));
    }
}