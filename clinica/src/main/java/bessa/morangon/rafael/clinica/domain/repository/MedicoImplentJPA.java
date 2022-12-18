package bessa.morangon.rafael.clinica.domain.repository;

import bessa.morangon.rafael.clinica.domain.dto.MedicoDTO;
import bessa.morangon.rafael.clinica.domain.model.Atividade;
import bessa.morangon.rafael.clinica.domain.model.Medico;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoImplentJPA implements MedicoInterface{

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<MedicoDTO> buscaPeloID(Long id) {

        Optional<Medico> medico = repository.findById(id);
        if(medico.isPresent()){
            MedicoDTO dto = mapper.map(medico.get(), MedicoDTO.class);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Page<MedicoDTO>> buscaTodosMedicosCadastradosComPaginacao(Pageable pageable) {

        Page<Medico> all = repository.findAll(pageable);
        Page<MedicoDTO> medicos = all.map(medico -> mapper.map(medico, MedicoDTO.class));

        return ResponseEntity.ok(medicos);
    }

    @Override
    public MedicoDTO cadastraNovoMedico(Medico medico) {
        repository.save(medico);
        return mapper.map(medico, MedicoDTO.class);
    }

    @Override
    public ResponseEntity<MedicoDTO> atualizaMedico(Long id, Medico medico) {
        Optional<Medico> med = repository.findById(id);

        if(med.isPresent()){
            med.get().setCrm(medico.getCrm());
            med.get().setNome(medico.getNome());
            med.get().setEmail(medico.getEmail());
            med.get().setEndereco(medico.getEndereco());
            med.get().setEspecialidade(medico.getEspecialidade());
            med.get().setTelefone(medico.getTelefone());
            return ResponseEntity.ok(mapper.map(med.get(), MedicoDTO.class));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> exclusaoLogica(Long id) {
        Optional<Medico> medico = repository.findById(id);

        if(medico.isPresent() && medico.get().getAtividade().equals(Atividade.ATIVO)){
            medico.get().setAtividade(Atividade.INATIVO);
            return ResponseEntity.noContent().build();
        }
        if(medico.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> ativaMedico(Long id) {
        Optional<Medico> medico = repository.findById(id);

        if(medico.isPresent() && medico.get().getAtividade().equals(Atividade.INATIVO)){
            medico.get().setAtividade(Atividade.ATIVO);
            return ResponseEntity.ok(medico.get());
        }
        return ResponseEntity.notFound().build();
    }
    }

