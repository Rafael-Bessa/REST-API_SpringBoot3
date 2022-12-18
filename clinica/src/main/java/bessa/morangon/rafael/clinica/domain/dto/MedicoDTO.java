package bessa.morangon.rafael.clinica.domain.dto;

import bessa.morangon.rafael.clinica.domain.model.Atividade;
import bessa.morangon.rafael.clinica.domain.model.Especialidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {

    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    private Especialidade especialidade;
    private Atividade atividade;
}
