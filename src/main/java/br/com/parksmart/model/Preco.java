package br.com.parksmart.model;

import br.com.parksmart.dto.response.PrecoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Preco {
    @Id
    private String codigoTabelaPreco;
    private BigDecimal precoHoraFixa;
    private BigDecimal precoHoraVariavel;

    public PrecoResponse toPrecoResponse(){
        return new PrecoResponse(this.codigoTabelaPreco, this.precoHoraFixa, this.precoHoraVariavel);
    }
}