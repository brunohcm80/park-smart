package br.com.parksmart.repository;

import br.com.parksmart.model.Estadia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstadiaRepository extends MongoRepository<Estadia, String> {
}