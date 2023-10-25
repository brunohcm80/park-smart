package br.com.parksmart.model;

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
    @DBRef
    private Estadia estadia;
    private ModeloCobrancaEnum modeloCobranca;
    private MeioPagamentoEnum meioPagamento;
    private BigDecimal valorPago;
    @DBRef
    private Recibo recibo;
}