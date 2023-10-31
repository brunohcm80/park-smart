package br.com.parksmart.service;

import br.com.parksmart.dto.request.CobrancaRequest;
import br.com.parksmart.dto.request.EstadiaRequest;
import br.com.parksmart.dto.response.EstadiaResponse;
import br.com.parksmart.exception.*;
import br.com.parksmart.model.Estadia;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import br.com.parksmart.model.enums.ModeloCobrancaEnum;
import br.com.parksmart.repository.CondutorRepository;
import br.com.parksmart.repository.EstadiaRepository;
import br.com.parksmart.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

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

    public EstadiaResponse iniciarEstadia (EstadiaRequest estadiaRequest)
            throws EstadiaInvalidaException, PrecoInvalidoException, CondutorInvalidoException,
            VeiculoInvalidoException {
        Estadia estadia = estadiaRequest.toEstadia(condutorRepository, veiculoRepository, cobrancaService);

        Optional<BigInteger> possivelQuantidadeHoras = Optional.ofNullable(estadia.getQuantidadeHoras());
        if (estadia.getCobranca().getModeloCobranca().equals(ModeloCobrancaEnum.FIXO)
                && possivelQuantidadeHoras.isEmpty()) {
            throw new EstadiaInvalidaException("Quantidade de horas da estadia fixa não informada.");
        }

        if (estadia.getCobranca().getModeloCobranca().equals(ModeloCobrancaEnum.VARIAVEL)
                && possivelQuantidadeHoras.isPresent()) {
            throw new EstadiaInvalidaException("Quantidade de horas não deve ser informada se variável.");
        }

        estadia.setHorarioEntrada(Instant.now());
        estadiaRepository.save(estadia);

        return new EstadiaResponse().toEstadiaResponse(estadia);
    }

    public Page<EstadiaResponse> listarEstadias (Pageable paginacao) {
        var estadias = estadiaRepository.findAll();
        var estadiasResponse = estadias.stream().map(est -> new EstadiaResponse().toEstadiaResponse(est)).toList();
        return new PageImpl<>(estadiasResponse, paginacao, estadiasResponse.size());
    }

    public EstadiaResponse obterEstadiaPorCodigo (String codigoEstadia) throws EstadiaInvalidaException {
        Estadia possivelEstadia = estadiaRepository
                .findById(codigoEstadia)
                .orElseThrow(() -> new EstadiaInvalidaException("Estadia não localizada."));

        return new EstadiaResponse().toEstadiaResponse(possivelEstadia);
    }

    public EstadiaResponse finalizarEstadia (String codigoEstadia, CobrancaRequest cobrancaRequest)
            throws EstadiaInvalidaException, CobrancaInvalidaException{

        Estadia estadia = estadiaRepository
                .findById(codigoEstadia)
                .orElseThrow(()-> new EstadiaInvalidaException("Estadia não localizada."));

        Optional<Instant> possivelHorarioSaida = Optional.ofNullable(estadia.getHorarioSaida());
        if(possivelHorarioSaida.isPresent()){
            throw new EstadiaInvalidaException("Estadia já finalizada.");
        }

        if(cobrancaRequest.getMeioPagamento().equals(null)){
            throw new CobrancaInvalidaException("Meio de pagamento não informado.");
        }

        if(cobrancaRequest.getMeioPagamento().equals(MeioPagamentoEnum.PIX) &&
            !estadia.getCobranca().getModeloCobranca().equals(ModeloCobrancaEnum.FIXO)){
            throw new CobrancaInvalidaException("Meio de pagamento invalido para modelo de cobrança.");
        }

        estadia.setHorarioSaida(Instant.now());
        estadia.getCobranca().setMeioPagamento(cobrancaRequest.getMeioPagamento());

        if(estadia.getCobranca().getModeloCobranca().equals(ModeloCobrancaEnum.VARIAVEL)) {
            Duration tempoEstacionado = Duration.between(estadia.getHorarioEntrada(),
                    estadia.getHorarioSaida());
            BigInteger quantidadeHorasEstacionadas = (BigInteger
                    .valueOf(tempoEstacionado.toHoursPart()));

            estadia.setQuantidadeHoras(quantidadeHorasEstacionadas.equals(BigInteger.ZERO)
                    ? BigInteger.valueOf(1) : quantidadeHorasEstacionadas);
        }

        cobrancaService.finalizarCobranca(estadia);
        estadiaRepository.save(estadia);

        return new EstadiaResponse().toEstadiaResponse(estadia);
    }
}