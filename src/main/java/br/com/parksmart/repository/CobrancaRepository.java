package br.com.parksmart.repository;

import br.com.parksmart.model.Cobranca;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CobrancaRepository extends MongoRepository<Cobranca, String> {
}