package br.com.parksmart.model;

import br.com.parksmart.dto.response.CobrancaResponse;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import br.com.parksmart.model.enums.ModeloCobrancaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Cobranca {
    @Id
    private String codigoCobranca;

    private ModeloCobrancaEnum modeloCobranca;
    private MeioPagamentoEnum meioPagamento;
    private BigDecimal valorCobranca;
    @DBRef
    private Recibo recibo;

    public Cobranca(ModeloCobrancaEnum modeloCobranca, MeioPagamentoEnum meioPagamento, BigDecimal valorCobranca) {
        this.modeloCobranca = modeloCobranca;
        this.meioPagamento = meioPagamento;
        this.valorCobranca = valorCobranca;
    }

    public CobrancaResponse toCobrancaResponse() {
        return new CobrancaResponse(this.codigoCobranca,
                this.modeloCobranca,
                this.meioPagamento,
                this.valorCobranca,
                this.recibo);
    }
}