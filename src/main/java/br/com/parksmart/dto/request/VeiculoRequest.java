package br.com.parksmart.dto.request;

import br.com.parksmart.model.Veiculo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoRequest {

    private String placa;
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotBlank
    private String cor;

    public Veiculo toVeiculo() {
        return new Veiculo(this.placa,
                this.marca,
                this.modelo,
                this.cor);
    }
}