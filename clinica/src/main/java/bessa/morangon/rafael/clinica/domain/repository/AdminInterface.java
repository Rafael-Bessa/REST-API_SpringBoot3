package bessa.morangon.rafael.clinica.domain.repository;

import bessa.morangon.rafael.clinica.domain.dto.AdminDTO;
import bessa.morangon.rafael.clinica.domain.dto.MedicoDTO;
import bessa.morangon.rafael.clinica.domain.model.Admin;
import bessa.morangon.rafael.clinica.domain.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface AdminInterface {
    public ResponseEntity<List<AdminDTO>> buscaTodosAdminsSistema();
    public ResponseEntity<?> buscaAdminPeloID(UUID id);
    public AdminDTO cadastraNovoAdmin(Admin admin);
    public ResponseEntity<?> deletaAdmin(UUID id);
}
