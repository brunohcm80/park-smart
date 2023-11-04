package br.com.parksmart.model;

import br.com.parksmart.dto.response.FormaPagamentoResponse;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class FormaPagamento {
    @Id
    private String codigoFormaPagamento;
    private MeioPagamentoEnum tipoPagamentoPreferencial;
    private String numeroCartao;
    private String titularCartao;
    private String codSeguranca;
    private Date dtValidadeCartao;
    private String codigoPix;

    public FormaPagamento(MeioPagamentoEnum tipoPagamentoPreferencial, String numeroCartao, String titularCartao, String codSeguranca, Date dtValidadeCartao) {
    }

    public FormaPagamentoResponse toFormaPagResponse() {
        return new FormaPagamentoResponse( this.codigoFormaPagamento, this.tipoPagamentoPreferencial, this.numeroCartao,
                this.titularCartao,
                this.codSeguranca,
                this.dtValidadeCartao,
                this.codigoPix);
    }
}
