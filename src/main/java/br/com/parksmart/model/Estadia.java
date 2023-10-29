package br.com.parksmart.model;

import br.com.parksmart.dto.response.EstadiaResponse;
import br.com.parksmart.model.enums.ModeloCobrancaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Estadia {
    @Id
    private String codigoEstadia;
    @DBRef
    private Condutor condutor;
    @DBRef
    private Veiculo veiculo;
    private String codigoParquimetro;
    private Instant horarioEntrada;
    private Instant horarioSaida;
    @DBRef
    private Cobranca cobranca;
    private BigInteger quantidadeHoras;

    public Estadia(Condutor condutor, Veiculo veiculo, String codigoParquimetro, Cobranca cobranca, BigInteger quantidadeHoras) {
        this.condutor = condutor;
        this.veiculo = veiculo;
        this.codigoParquimetro = codigoParquimetro;
        this.quantidadeHoras = quantidadeHoras;
        this.cobranca = cobranca;
    }

    public EstadiaResponse toEstadiaResponse (){
        return new EstadiaResponse(this.codigoEstadia,
                this.condutor,
                this.veiculo,
                this.codigoParquimetro,
                this.horarioEntrada,
                this.horarioSaida,
                this.cobranca,
                this.quantidadeHoras);
    }
}