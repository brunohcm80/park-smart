package br.com.parksmart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrecoResponse {
    private String codigoTabelaPreco;
    private BigDecimal precoHoraFixa;
    private BigDecimal precoHoraVariavel;
}