package br.com.parksmart.repository;

import br.com.parksmart.model.Condutor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CondutorRepository extends MongoRepository<Condutor, String> {
}