package br.com.parksmart.dto.response;

import br.com.parksmart.model.FormaPagamento;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FormaPagamentoResponse {

    private String codigoFormaPagamento;

    private MeioPagamentoEnum tipoPagamentoPreferencial;

    private String numeroCartao;

    private String titularCartao;

    private String codSeguranca;

    private Date dtValidadeCartao;

    private String codigoPix;

    public FormaPagamentoResponse(String codigoFormaPagamento, MeioPagamentoEnum tipoPagamentoPreferencial, String numeroCartao, String titularCartao, String codSeguranca, Date dtValidadeCartao, String codigoPix) {
        this.codigoFormaPagamento = codigoFormaPagamento;
        this.tipoPagamentoPreferencial = tipoPagamentoPreferencial;
        this.numeroCartao = numeroCartao;
        this.titularCartao = titularCartao;
        this.codSeguranca = codSeguranca;
        this.dtValidadeCartao = dtValidadeCartao;
        this.codigoPix = codigoPix;
    }

    public FormaPagamentoResponse(FormaPagamento pagamentoSave) {
        this.codigoFormaPagamento = codigoFormaPagamento;
        this.tipoPagamentoPreferencial = tipoPagamentoPreferencial;
        this.numeroCartao = numeroCartao;
        this.titularCartao = titularCartao;
        this.codSeguranca = codSeguranca;
        this.dtValidadeCartao = dtValidadeCartao;
    }

    public FormaPagamentoResponse toFormaPagamentoResponse(FormaPagamento pagamento) {
        return new FormaPagamentoResponse(this.codigoFormaPagamento, this.tipoPagamentoPreferencial,
                this.numeroCartao, this.titularCartao, this.codSeguranca, this.dtValidadeCartao, this.codigoPix);
    }
}