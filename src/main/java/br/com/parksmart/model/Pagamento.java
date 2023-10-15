package br.com.parksmart.model;

import br.com.parksmart.model.enums.MeioPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    private Estadia estadia;
    private MeioPagamentoEnum meioPagamento;
    private BigDecimal valorPago;
}