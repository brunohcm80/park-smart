package br.com.parksmart.dto.response;

import br.com.parksmart.dto.request.ReciboRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReciboResponse {
    private String codigoRecibo;
    private Instant horarioEmissaoRecibo;
    private BigDecimal valorRecibo;

    public ReciboRequest toReciboRequest(){
        return new ReciboRequest(this.codigoRecibo, this.horarioEmissaoRecibo, this.valorRecibo);
    }
}