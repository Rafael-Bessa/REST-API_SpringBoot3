package bessa.morangon.rafael.clinica.domain.repository;

import bessa.morangon.rafael.clinica.domain.dto.AdminDTO;
import bessa.morangon.rafael.clinica.domain.model.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminImplentJPA implements AdminInterface {
    @Autowired
    private AdminRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public ResponseEntity<List<AdminDTO>> buscaTodosAdminsSistema() {
        List<Admin> admins = repository.findAll();
        List<AdminDTO> adminsdto = admins.stream().map(a -> mapper.map(a, AdminDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(adminsdto);
    }
    @Override
    public ResponseEntity<?> buscaAdminPeloID(UUID id) {
        Optional<Admin> admin = repository.findById(id);
        if(admin.isPresent()){
            return ResponseEntity.ok(mapper.map(admin.get(), AdminDTO.class));
        }
        return ResponseEntity.notFound().build();
    }
    @Override
    public AdminDTO cadastraNovoAdmin(Admin admin) {

        Optional<Admin> adminJaCadastrado = repository.findByLogin(admin.getLogin());

        if(adminJaCadastrado.isPresent()){
            return null;
        }
        admin.setSenha(encoder.encode(admin.getSenha()));
        repository.save(admin);
        return mapper.map(admin, AdminDTO.class);
    }
    @Override
    public ResponseEntity<?> deletaAdmin(UUID id) {

        Optional<Admin> admin = repository.findById(id);
        if (admin.isPresent()) {
            repository.delete(admin.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}