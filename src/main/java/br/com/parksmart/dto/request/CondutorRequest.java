package br.com.parksmart.dto.request;

import br.com.parksmart.model.Condutor;
import br.com.parksmart.model.Endereco;
import br.com.parksmart.model.FormaPagamento;
import br.com.parksmart.model.Veiculo;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondutorRequest {
    private String codigoCondutor;
    @CPF
    private String cpf;
    @NotNull
    private String nome;

    private String telefone;
    private String email;
    private List<Veiculo> veiculos;
    private Endereco endereco;
    private FormaPagamento formaPagamento;

    public Condutor toCondutor() {
        return new Condutor(this.codigoCondutor, this.cpf,this.nome, this.telefone, this.email, this.veiculos,
                this.endereco, this.formaPagamento);
    }
}
