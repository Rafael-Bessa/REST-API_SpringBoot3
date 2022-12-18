package bessa.morangon.rafael.clinica.controller;

import bessa.morangon.rafael.clinica.domain.dto.AdminDTO;
import bessa.morangon.rafael.clinica.domain.model.Admin;
import bessa.morangon.rafael.clinica.domain.repository.AdminImplentJPA;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminImplentJPA repository;

    @GetMapping
    public ResponseEntity<List<AdminDTO>> listaTodosAdmins(){
        return repository.buscaTodosAdminsSistema();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> mostraAdmin(@PathVariable UUID id){
        return repository.buscaAdminPeloID(id);
    }
    @PostMapping
    public ResponseEntity<AdminDTO> criaNovoAdminNoSistema(@RequestBody @Valid Admin admin, UriComponentsBuilder buider){

        AdminDTO adminDTO = repository.cadastraNovoAdmin(admin);

        if(adminDTO == null){
            return ResponseEntity.badRequest().build();
        }
        URI uri = buider.path("/admin/{id}").buildAndExpand(admin.getId()).toUri();
        return ResponseEntity.created(uri).body(adminDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletaAdmin(UUID id){
        return repository.deletaAdmin(id);
    }

}
