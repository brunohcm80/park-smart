package br.com.parksmart.dto.request;

import br.com.parksmart.model.Cobranca;
import br.com.parksmart.model.Condutor;
import br.com.parksmart.model.Estadia;
import br.com.parksmart.model.Veiculo;
import br.com.parksmart.model.enums.ModeloCobrancaEnum;
import br.com.parksmart.repository.CondutorRepository;
import br.com.parksmart.repository.VeiculoRepository;
import br.com.parksmart.service.CobrancaService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadiaRequest {

    @CPF
    private String cpfCondutor;
    @NotBlank
    private String placaVeiculo;
    @NotBlank
    private String codigoParquimetro;
    @NotNull
    private ModeloCobrancaEnum modeloCobranca;

    public Estadia toEstadia (CondutorRepository condutorRepository, VeiculoRepository veiculoRepository,
                              CobrancaService cobrancaService){
        Condutor condutor = condutorRepository
                .findById(this.cpfCondutor)
                .orElseThrow(()->new IllegalArgumentException("Condutor não localizado."));

        Veiculo veiculo = veiculoRepository
                .findById(this.placaVeiculo)
                .orElseThrow(()->new IllegalArgumentException("Veiculo não localizado."));

        CobrancaRequest cobrancaRequest = new CobrancaRequest();
        cobrancaRequest.setModeloCobranca(this.modeloCobranca);
        Cobranca cobranca = cobrancaService.iniciarCobranca(cobrancaRequest)
                .toCobrancaRequest().toCobranca();

        return new Estadia(condutor, veiculo, this.codigoParquimetro, cobranca);
    }
}