package br.com.parksmart.repository;

import br.com.parksmart.model.Condutor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface CondutorRepository extends MongoRepository<Condutor, String> {
    Condutor getByCpf(String cpf);

    Collection<Object> findByCpf();
}