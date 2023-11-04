package br.com.parksmart.dto.request;

import br.com.parksmart.model.FormaPagamento;
import br.com.parksmart.model.Preco;
import br.com.parksmart.model.enums.MeioPagamentoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoRequest {

    private MeioPagamentoEnum tipoPagamentoPreferencial;

    private String numeroCartao;

    private String titularCartao;

    private String codSeguranca;

    private Date dtValidadeCartao;

    public FormaPagamento toFormaPagamento() {

        return new FormaPagamento(tipoPagamentoPreferencial, numeroCartao, titularCartao, codSeguranca, dtValidadeCartao);
    }
}