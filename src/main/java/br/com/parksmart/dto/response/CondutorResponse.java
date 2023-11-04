package br.com.parksmart.dto.response;

import br.com.parksmart.model.Condutor;
import br.com.parksmart.model.Endereco;
import br.com.parksmart.model.Veiculo;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondutorResponse {

    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private List<Veiculo> veiculos;
    private MeioPagamentoEnum meioPagamentoPreferencial;
    private Endereco endereco;

    public CondutorResponse toCondutorResponse(Condutor condutor) {

        setCpf(condutor.getCpf());
        setNome(condutor.getNome());
        setTelefone(condutor.getTelefone());
        setEmail(condutor.getEmail());
        setVeiculos(condutor.getVeiculos());
        setMeioPagamentoPreferencial(condutor.getMeioPagamentoPreferencial());
        setEndereco(condutor.getEndereco());
        return this;
    }

    public CondutorResponse(Condutor condutor) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.veiculos = veiculos;
        this.meioPagamentoPreferencial = meioPagamentoPreferencial;
        this.endereco = endereco;
    }

}
