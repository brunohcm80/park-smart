package br.com.parksmart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErroDetalhadoResponse {

    private String titulo;

    private int status;

    private List<String> detalhes;

    private long timestamp;
}