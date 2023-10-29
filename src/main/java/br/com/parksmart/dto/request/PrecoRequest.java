package br.com.parksmart.dto.request;

import br.com.parksmart.model.Preco;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrecoRequest {
    private String codigoTabelaPreco;
    @NotNull
    private BigDecimal precoHoraFixa;
    @NotNull
    private BigDecimal precoHoraVariavel;
    public Preco toPreco() {
        return new Preco (codigoTabelaPreco, precoHoraFixa, precoHoraVariavel);
    }
}