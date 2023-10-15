package br.com.parksmart.model;

import br.com.parksmart.model.enums.MeioPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Condutor {
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private List<Veiculo> veiculos;
    private MeioPagamentoEnum meioPagamentoPreferencial;
}