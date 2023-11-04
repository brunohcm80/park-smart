package br.com.parksmart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Endereco {

    private String logradouro;
    private String numero;
    private String cep;
    private String complemento;
    private String cidade;
    private String estado;
}
