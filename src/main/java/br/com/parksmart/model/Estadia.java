package br.com.parksmart.model;

import br.com.parksmart.model.enums.ModeloCobrancaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estadia {
    private Condutor condutor;
    private Veiculo veiculo;
    private ModeloCobrancaEnum modeloCobranca;
    private Instant horarioEntrada;
    private Instant horarioSaida;
}