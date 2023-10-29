package br.com.parksmart.service;

import br.com.parksmart.dto.request.PrecoRequest;
import br.com.parksmart.dto.response.PrecoResponse;
import br.com.parksmart.model.Preco;
import br.com.parksmart.repository.PrecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrecoService {

    @Autowired
    private PrecoRepository precoRepository;

    public PrecoResponse criaTabelaPrecos (PrecoRequest precoRequest){
        Optional<PrecoResponse> possivelTabelaPrecos = consultaTabelaPrecos();

        if (!possivelTabelaPrecos.isEmpty()){
            throw new IllegalArgumentException("Atualize tabela de preço já cadastrada");
        }

        Preco preco = precoRequest.toPreco();
        return precoRepository.save(preco).toPrecoResponse();
    }

    public Optional<PrecoResponse> consultaTabelaPrecos () {
        return precoRepository.findAll().stream()
                .findFirst()
                .map(precoResp -> precoResp.toPrecoResponse());
    }

    public PrecoResponse atualizaTabelaPrecos (PrecoRequest precoRequest){
        Optional<Preco> possivelTabelaPrecos = precoRepository.findAll().stream().findFirst();

        if (possivelTabelaPrecos.isEmpty()){
            throw new IllegalArgumentException("Cadastre nova tabela de preço");
        }

        Preco preco = precoRequest.toPreco();
        preco.setCodigoTabelaPreco(possivelTabelaPrecos.get().getCodigoTabelaPreco());

        return precoRepository.save(preco).toPrecoResponse();
    }
}