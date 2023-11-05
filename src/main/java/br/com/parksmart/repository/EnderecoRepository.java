package br.com.parksmart.repository;

import br.com.parksmart.model.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnderecoRepository extends MongoRepository<Endereco, String> {
}