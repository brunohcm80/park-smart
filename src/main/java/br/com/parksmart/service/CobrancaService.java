package br.com.parksmart.service;

import br.com.parksmart.dto.request.CobrancaRequest;
import br.com.parksmart.dto.response.CobrancaResponse;
import br.com.parksmart.exception.PrecoInvalidoException;
import br.com.parksmart.model.Cobranca;
import br.com.parksmart.model.Estadia;
import br.com.parksmart.repository.CobrancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CobrancaService {

    @Autowired
    private CobrancaRepository cobrancaRepository;
    @Autowired
    private PrecoService precoService;
    @Autowired
    private ReciboService reciboService;

    public CobrancaResponse iniciarCobranca (CobrancaRequest cobrancaRequest) throws PrecoInvalidoException{
        if(precoService.consultaTabelaPrecos().isEmpty()){
            throw new PrecoInvalidoException("Tabela de Precos não definida.");
        }

        Cobranca cobranca = cobrancaRequest.toCobranca();
        return cobrancaRepository.save(cobranca).toCobrancaResponse();
    }

    public CobrancaResponse finalizarCobranca (Estadia estadia){
        Cobranca cobranca = estadia.getCobranca();

        cobranca.setValorCobranca(precoService.consultaTabelaPrecos().
                get().
                getPrecoHoraFixa().
                multiply(BigDecimal.valueOf(estadia.getQuantidadeHoras().longValue())));

        cobranca.setRecibo(reciboService.emitirRecibo(cobranca.getValorCobranca())
                .toReciboRequest()
                .toRecibo());
        return cobrancaRepository.save(cobranca).toCobrancaResponse();
    }
}