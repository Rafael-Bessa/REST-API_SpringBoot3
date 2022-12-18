package bessa.morangon.rafael.clinica.domain.repository;

import bessa.morangon.rafael.clinica.domain.dto.MedicoDTO;
import bessa.morangon.rafael.clinica.domain.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MedicoInterface {

  public ResponseEntity<MedicoDTO> buscaPeloID(Long id);
  public ResponseEntity<Page<MedicoDTO>> buscaTodosMedicosCadastradosComPaginacao(Pageable pageable);
  public MedicoDTO cadastraNovoMedico(Medico medico);

  public ResponseEntity<?> atualizaMedico(Long id, Medico medico);

  public ResponseEntity<?> exclusaoLogica(Long id);

  public ResponseEntity<?> ativaMedico(Long id);

}
