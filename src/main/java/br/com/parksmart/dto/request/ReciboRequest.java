package br.com.parksmart.dto.request;

import br.com.parksmart.model.Recibo;
import jakarta.validation.constraints.NotNull;
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
public class ReciboRequest  {

    private String codigoRecibo;
    @NotNull
    private Instant dataEmissaoRecibo;
    @NotNull
    private BigDecimal valorRecibo;

    public Recibo toRecibo (){
        return new Recibo(this.codigoRecibo, this.dataEmissaoRecibo, this.valorRecibo);
    }
}