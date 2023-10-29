package br.com.parksmart.service;

import br.com.parksmart.dto.response.ReciboResponse;
import br.com.parksmart.model.Recibo;
import br.com.parksmart.repository.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class ReciboService {

    @Autowired
    private ReciboRepository reciboRepository;

    public ReciboResponse emitirRecibo (BigDecimal valor){
        Recibo recibo = new Recibo(Instant.now());
        recibo.setValorRecibo(valor);
        reciboRepository.save(recibo);
        return recibo.toReciboResponse();
    }
}