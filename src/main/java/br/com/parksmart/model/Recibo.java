package br.com.parksmart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.server.UID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recibo {
    private UID codigoRecibo;
    private Pagamento pagamento;
}