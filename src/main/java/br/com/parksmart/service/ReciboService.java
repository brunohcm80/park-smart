package br.com.parksmart.service;

import br.com.parksmart.dto.response.ReciboResponse;
import br.com.parksmart.model.Recibo;
import br.com.parksmart.repository.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ReciboService {

    @Autowired
    private ReciboRepository reciboRepository;

    public ReciboResponse emitirRecibo (){
        Recibo recibo = new Recibo(Instant.now());
        reciboRepository.save(recibo);
        return recibo.toReciboResponse();
    }
}