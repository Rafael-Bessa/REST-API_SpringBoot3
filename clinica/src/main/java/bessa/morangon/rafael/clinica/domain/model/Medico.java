package bessa.morangon.rafael.clinica.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank @Email @Column(unique = true)
    private String email;

    @NotBlank @Column(unique = true)  @Pattern(regexp = "\\d{8,14}")
    private String telefone;

    @NotBlank @Column(unique = true) @Pattern(regexp = "\\d{6}")
    private String crm;

    @NotNull @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Enumerated(EnumType.STRING)
    private Atividade atividade = Atividade.ATIVO;

    @Embedded @Valid
    private Endereco endereco;



}
