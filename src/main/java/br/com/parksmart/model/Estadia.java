package br.com.parksmart.model;

import br.com.parksmart.model.enums.ModeloCobrancaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public Estadia(Condutor condutor, Veiculo veiculo, String codigoParquimetro) {
        this.condutor = condutor;
        this.veiculo = veiculo;
        this.codigoParquimetro = codigoParquimetro;
    }
}