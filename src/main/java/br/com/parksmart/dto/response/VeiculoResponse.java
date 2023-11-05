package br.com.parksmart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoResponse {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
}