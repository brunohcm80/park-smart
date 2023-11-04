package br.com.parksmart.dto.request;

import br.com.parksmart.model.Cobranca;
import br.com.parksmart.model.Recibo;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import br.com.parksmart.model.enums.ModeloCobrancaEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
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
public class CobrancaRequest {
    @JsonIgnore
    private String codigoCobranca;
    @JsonIgnore
    private ModeloCobrancaEnum modeloCobranca;
    @NotNull
    private MeioPagamentoEnum meioPagamento;
    @JsonIgnore
    private BigDecimal valorCobranca;
    @JsonIgnore
    private Recibo recibo;

    public Cobranca toCobranca() {
        return new Cobranca(this.codigoCobranca,
                this.modeloCobranca, this.meioPagamento, this.valorCobranca, this.recibo);
    }
}