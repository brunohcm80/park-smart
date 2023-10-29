package br.com.parksmart.dto.response;

import br.com.parksmart.model.Cobranca;
import br.com.parksmart.model.Condutor;
import br.com.parksmart.model.Estadia;
import br.com.parksmart.model.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadiaResponse {
    private String codigoEstadia;
    private Condutor condutor;
    private Veiculo veiculo;
    private String codigoParquimetro;
    private Instant horarioEntrada;
    private Instant horarioSaida;
    private Cobranca cobranca;
    private BigInteger quantidadeHoras;

    public EstadiaResponse toEstadiaResponse(Estadia estadia) {
        setCodigoEstadia(estadia.getCodigoEstadia());
        setCondutor(estadia.getCondutor());
        setVeiculo(estadia.getVeiculo());
        setCodigoParquimetro(estadia.getCodigoParquimetro());
        setHorarioEntrada(estadia.getHorarioEntrada());
        setHorarioSaida(estadia.getHorarioSaida());
        setCobranca(estadia.getCobranca());
        setQuantidadeHoras(estadia.getQuantidadeHoras());
        return this;
    }
}