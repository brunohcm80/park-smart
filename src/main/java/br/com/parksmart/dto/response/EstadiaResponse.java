package br.com.parksmart.dto.response;

import br.com.parksmart.model.Cobranca;
import br.com.parksmart.model.Condutor;
import br.com.parksmart.model.Estadia;
import br.com.parksmart.model.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadiaResponse {
    private String codigoEstadia;
    private Condutor condutor;
    private Veiculo veiculo;
    private Cobranca cobranca;
    private String codigoParquimetro;
    private Instant horarioEntrada;
    private Instant horarioSaida;

    public EstadiaResponse toEstadiaResponse (Estadia estadia) {
        setCodigoEstadia(estadia.getCodigoEstadia());
        setCondutor(estadia.getCondutor());
        setVeiculo(estadia.getVeiculo());
        setCobranca(estadia.getCobranca());
        setCodigoParquimetro(estadia.getCodigoParquimetro());
        setHorarioEntrada(estadia.getHorarioEntrada());
        setHorarioSaida(estadia.getHorarioSaida());
        return this;
    }
}