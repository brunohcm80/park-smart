package br.com.parksmart.service;

import br.com.parksmart.dto.request.CobrancaRequest;
import br.com.parksmart.dto.response.CobrancaResponse;
import br.com.parksmart.model.Cobranca;
import br.com.parksmart.repository.CobrancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CobrancaService {

    @Autowired
    private CobrancaRepository cobrancaRepository;

    @Autowired
    private ReciboService reciboService;

    public CobrancaResponse iniciarCobranca (CobrancaRequest cobrancaRequest){
        Cobranca cobranca = cobrancaRequest.toCobranca();
        return cobrancaRepository.save(cobranca).toCobrancaResponse();
    }

    public CobrancaResponse finalizarCobranca (CobrancaRequest cobrancaRequest){
        Cobranca cobranca = cobrancaRequest.toCobranca();

        cobranca.setRecibo(reciboService.emitirRecibo().toReciboRequest().toRecibo());
        return cobrancaRepository.save(cobranca).toCobrancaResponse();
    }
}