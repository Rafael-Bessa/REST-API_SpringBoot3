package bessa.morangon.rafael.clinica.controller;

import bessa.morangon.rafael.clinica.domain.dto.MedicoDTO;
import bessa.morangon.rafael.clinica.domain.model.Medico;
import bessa.morangon.rafael.clinica.domain.repository.MedicoImplentJPA;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@AllArgsConstructor
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoImplentJPA repository;

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> buscaMedicoPorID(@PathVariable Long id){
        return repository.buscaPeloID(id);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDTO>> buscaTodosMedicos(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable){
        return repository.buscaTodosMedicosCadastradosComPaginacao(pageable);
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> cadastraMedicos(@RequestBody @Valid Medico medico, UriComponentsBuilder builder){

        MedicoDTO medicoDTO = repository.cadastraNovoMedico(medico);
        URI uri = builder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(medicoDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody @Valid Medico medico){
        return repository.atualizaMedico(id, medico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inativaMedico(@PathVariable Long id){
        return repository.exclusaoLogica(id);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<?> ativaMedico(@PathVariable Long id){
        return repository.ativaMedico(id);
    }
}
