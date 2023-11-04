package br.com.parksmart.model;

import br.com.parksmart.dto.response.CondutorResponse;
import br.com.parksmart.dto.response.PrecoResponse;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Condutor {
    @Id
    private String codigoCondutor;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    @DBRef
    private List<Veiculo> veiculos;
   // private MeioPagamentoEnum meioPagamentoPreferencial;
    @Indexed
    private Endereco endereco;
    @DBRef
    private FormaPagamento formaPagamento;

    public Condutor(String codigoCondutor, String cpf, String nome, String telefone, String email,
                    List<Veiculo> veiculos,
                    Endereco endereco,FormaPagamento formaPagamento ) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.veiculos = veiculos;
        this.endereco = endereco;
        this.formaPagamento = formaPagamento;
    }
    public CondutorResponse toCondutorResponse(){
        return new CondutorResponse(this.codigoCondutor, this.cpf, this.nome,
                this.telefone, this.email, this.veiculos,
                this.endereco, this.formaPagamento);
    }
}