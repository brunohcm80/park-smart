package br.com.parksmart.repository;

import br.com.parksmart.model.Cobranca;
import br.com.parksmart.model.FormaPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FormaPagamentoRepository extends MongoRepository<FormaPagamento, Long> {
    FormaPagamento findByCodigoFormaPagamento(String id);
}