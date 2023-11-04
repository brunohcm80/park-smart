package br.com.parksmart.dto.response;

import br.com.parksmart.model.Condutor;
import br.com.parksmart.model.Endereco;
import br.com.parksmart.model.FormaPagamento;
import br.com.parksmart.model.Veiculo;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondutorResponse {

    private String codigoCondutor;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private List<Veiculo> veiculos;
    private Endereco endereco;
    private FormaPagamento formaPagamento;

    public CondutorResponse(Optional<Condutor> condutor) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.veiculos = veiculos;
        this.endereco = endereco;
        this.formaPagamento = formaPagamento;
    }

    public CondutorResponse toCondutorResponse(Condutor condutor) {

        setCpf(condutor.getCpf());
        setNome(condutor.getNome());
        setTelefone(condutor.getTelefone());
        setEmail(condutor.getEmail());
        setVeiculos(condutor.getVeiculos());
        setEndereco(condutor.getEndereco());
        setFormaPagamento(condutor.getFormaPagamento());
        return this;
    }

    public CondutorResponse(Condutor condutor) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.veiculos = veiculos;
        this.endereco = endereco;
        this.formaPagamento = formaPagamento;
    }

}
