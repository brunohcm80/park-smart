package br.com.parksmart.service;

import br.com.parksmart.dto.request.CondutorRequest;
import br.com.parksmart.dto.response.CondutorResponse;
import br.com.parksmart.exception.CondutorInvalidoException;
import br.com.parksmart.model.Condutor;
import br.com.parksmart.repository.CondutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    public CondutorResponse cadastrarCondutor(CondutorRequest condutorRequest) throws CondutorInvalidoException{
        Condutor condutor = condutorRequest.toCondutor();

        if(!(condutorRepository.findByCpf(condutor.getCpf()).isEmpty())){
            throw new CondutorInvalidoException("O CPF informado já existe!");
        }

        var condutorSave = condutorRepository.save(condutor);
        return new CondutorResponse(condutorSave);
    }
    public CondutorResponse obterCondutorPorCPF(String cpf) throws CondutorInvalidoException {

        Optional<Condutor> condutor = condutorRepository.findByCpf(cpf);
        if (condutor.isEmpty()){
            throw new CondutorInvalidoException("Condutor não localizado.");
        }

        return new CondutorResponse(condutor);
    }

    public Page<CondutorResponse> obterTodos(Pageable paginacao) {

        var condutor = condutorRepository.findAll();
        var condutorResponse = condutor.stream().map(condutores -> new CondutorResponse().toCondutorResponse(condutores)).toList();

        return new PageImpl<>(condutorResponse, paginacao, condutorResponse.size());
    }

    public CondutorResponse atualizaCondutor(CondutorRequest condutorRequest) throws CondutorInvalidoException{

        Optional<Condutor> atualizarCondutor = condutorRepository.findByCpf(condutorRequest.getCpf()).stream().findFirst();

        if (atualizarCondutor.isEmpty()){
            throw new CondutorInvalidoException("Condutor não localizado.");
        }

        Condutor condutor = condutorRequest.toCondutor();
        condutor.setNome(atualizarCondutor.get().getNome());
        condutor.setTelefone(atualizarCondutor.get().getTelefone());
        condutor.setEmail(atualizarCondutor.get().getEmail());

        return condutorRepository.save(condutor).toCondutorResponse();
    }

}
