package br.com.parksmart.dto.response;

import br.com.parksmart.dto.request.EnderecoRequest;
import br.com.parksmart.model.enums.EstadosEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {

    private String logradouro;
    private String numero;
    private String cep;
    private String complemento;
    private String cidade;
    private EstadosEnum estado;

    public EnderecoRequest toEnderecoRequest() {
        return new EnderecoRequest(
                this.logradouro,
                this.numero,
                this.cep,
                this.complemento,
                this.cidade,
                this.estado);
    }
}
