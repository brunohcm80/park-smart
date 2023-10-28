package br.com.parksmart.service;

import br.com.parksmart.dto.request.EstadiaRequest;
import br.com.parksmart.dto.response.EstadiaResponse;
import br.com.parksmart.model.Estadia;
import br.com.parksmart.repository.CondutorRepository;
import br.com.parksmart.repository.EstadiaRepository;
import br.com.parksmart.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EstadiaService {

    @Autowired
    private EstadiaRepository estadiaRepository;

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private CobrancaService cobrancaService;

    public EstadiaResponse iniciarEstadia (EstadiaRequest estadiaRequest){
        Estadia estadia = estadiaRequest.toEstadia(condutorRepository, veiculoRepository, cobrancaService);

        estadia.setHorarioEntrada(Instant.now());
        estadiaRepository.save(estadia);

        return new EstadiaResponse().toEstadiaResponse(estadia);
    }

    public Page<EstadiaResponse> listarEstadias (Pageable paginacao) {
        var estadias = estadiaRepository.findAll();
        var estadiasResponse = estadias.stream().map(est -> new EstadiaResponse().toEstadiaResponse(est)).toList();
        return new PageImpl<>(estadiasResponse, paginacao, estadiasResponse.size());
    }

    public EstadiaResponse obterEstadiaPorCodigo (String codigoEstadia) {
        Estadia possivelEstadia = estadiaRepository
                .findById(codigoEstadia)
                .orElseThrow(() -> new IllegalArgumentException("Estadia não localizada."));

        return new EstadiaResponse().toEstadiaResponse(possivelEstadia);
    }

    public EstadiaResponse finalizarEstadia (String codigoEstadia){

        Estadia estadia = estadiaRepository
                .findById(codigoEstadia)
                .orElseThrow(()-> new IllegalArgumentException("Estadia não localizada."));

        estadia.setHorarioSaida(Instant.now());

        cobrancaService.finalizarCobranca(estadia.getCobranca().toCobrancaResponse().toCobrancaRequest());
        estadiaRepository.save(estadia);

        return new EstadiaResponse().toEstadiaResponse(estadia);
    }
}