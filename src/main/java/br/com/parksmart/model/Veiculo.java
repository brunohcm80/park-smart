package br.com.parksmart.model;

import br.com.parksmart.dto.response.VeiculoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Veiculo {
    @Id
    private String placa;
    private String marca;
    private String modelo;
    private String cor;

    public VeiculoResponse toVeiculoResponse() {
        return new VeiculoResponse(this.placa,
                this.marca,
                this.modelo,
                this.cor);
    }
}