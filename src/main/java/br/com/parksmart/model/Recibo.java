package br.com.parksmart.model;

import br.com.parksmart.dto.response.ReciboResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Recibo {
    @Id
    private String codigoRecibo;
    private Instant horarioEmissaoRecibo;
    private BigDecimal valorRecibo;

    public Recibo(Instant horarioEmissaoRecibo) {
        this.horarioEmissaoRecibo = horarioEmissaoRecibo;
    }

    public ReciboResponse toReciboResponse() {
        return new ReciboResponse (this.codigoRecibo, this.horarioEmissaoRecibo, this.valorRecibo);
    }
}