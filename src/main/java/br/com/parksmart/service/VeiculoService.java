package br.com.parksmart.service;

import br.com.parksmart.dto.request.VeiculoRequest;
import br.com.parksmart.dto.response.VeiculoResponse;
import br.com.parksmart.exception.VeiculoInvalidoException;
import br.com.parksmart.model.Veiculo;
import br.com.parksmart.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public VeiculoResponse cadastrarVeiculo (VeiculoRequest veiculoRequest){
        Veiculo veiculo = veiculoRepository.save(veiculoRequest.toVeiculo());
        return veiculo.toVeiculoResponse();
    }

    public VeiculoResponse obterVeiculoPorId (String placa) throws VeiculoInvalidoException{
        Veiculo possivelVeiculo = veiculoRepository
                .findById(placa)
                .orElseThrow(() -> new VeiculoInvalidoException("Veículo não localizado."));

        return possivelVeiculo.toVeiculoResponse();
    }

    public VeiculoResponse atualizarVeiculo (String placa, VeiculoRequest veiculoRequest)
            throws VeiculoInvalidoException {
        Veiculo possivelVeiculo = veiculoRepository
                .findById(placa)
                .orElseThrow(() -> new VeiculoInvalidoException("Veículo não localizado."));

        veiculoRequest.setPlaca(placa);
        return veiculoRepository.save(veiculoRequest.toVeiculo()).toVeiculoResponse();
    }

    public Page<VeiculoResponse> listarVeiculos (Pageable paginacao){
        List<VeiculoResponse> veiculosResponse = veiculoRepository.findAll()
                .stream()
                .map(vr -> vr.toVeiculoResponse())
                .toList();

        return new PageImpl<>(veiculosResponse, paginacao, veiculosResponse.size());
    }
}