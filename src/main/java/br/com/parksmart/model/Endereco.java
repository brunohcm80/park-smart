package br.com.parksmart.model;

import br.com.parksmart.model.enums.EstadosEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Endereco {

    //private Long codigoEndereco;
    @Indexed
    private String logradouro;
    private String numero;
    private String cep;
    private String complemento;
    private String cidade;
    private EstadosEnum estado;
}
