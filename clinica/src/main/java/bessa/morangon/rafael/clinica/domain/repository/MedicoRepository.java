package bessa.morangon.rafael.clinica.domain.repository;

import bessa.morangon.rafael.clinica.domain.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
