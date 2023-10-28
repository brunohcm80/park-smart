package br.com.parksmart.dto.response;

import br.com.parksmart.dto.request.CobrancaRequest;
import br.com.parksmart.model.Recibo;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import br.com.parksmart.model.enums.ModeloCobrancaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CobrancaResponse {

    private String codigoCobranca;
    private ModeloCobrancaEnum modeloCobranca;
    private MeioPagamentoEnum meioPagamento;
    private BigDecimal valorCobranca;
    private Recibo recibo;
    public CobrancaRequest toCobrancaRequest() {
        return new CobrancaRequest(this.codigoCobranca,
                this.modeloCobranca, this.meioPagamento, this.valorCobranca, this.recibo);
    }
}