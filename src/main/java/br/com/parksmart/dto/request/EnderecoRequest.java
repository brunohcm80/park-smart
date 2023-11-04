package br.com.parksmart.dto.request;

import br.com.parksmart.model.Endereco;
import br.com.parksmart.model.enums.EstadosEnum;
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
public class EnderecoRequest {

    private String logradouro;
    private String numero;
    private String cep;
    private String complemento;
    private String cidade;
    private EstadosEnum estado;
    public Endereco toEndereco() {

        return new Endereco ( logradouro, numero, cep, complemento, cidade, estado);
    }

}
