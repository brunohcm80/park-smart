package br.com.parksmart.repository;

import br.com.parksmart.model.Preco;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrecoRepository extends MongoRepository<Preco, String> {
}
