package bessa.morangon.rafael.clinica.domain.repository;

import bessa.morangon.rafael.clinica.domain.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    public Optional<Admin> findByLogin(String login);
}
