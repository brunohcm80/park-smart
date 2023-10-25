package br.com.parksmart.model;

import br.com.parksmart.model.enums.MeioPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Condutor {
    @Id
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    @DBRef
    private List<Veiculo> veiculos;
    private MeioPagamentoEnum meioPagamentoPreferencial;
}