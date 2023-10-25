package br.com.parksmart.repository;

import br.com.parksmart.model.Recibo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReciboRepository extends MongoRepository<Recibo, String> {
}