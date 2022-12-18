package bessa.morangon.rafael.clinica.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotBlank @Pattern(regexp = "\\d{8}")
    private String cep;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;
    private String complemento;
    private String numero;

}
