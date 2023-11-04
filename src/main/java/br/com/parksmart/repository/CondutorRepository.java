package br.com.parksmart.repository;

import br.com.parksmart.model.Condutor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CondutorRepository extends MongoRepository<Condutor, String> {
    Condutor getByCpf(String cpf);

    Optional<Condutor> findByCpf(String cpf);

}