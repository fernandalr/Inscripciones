package mx.uam.integracion.Inscripciones.service.impl;

import mx.uam.integracion.Inscripciones.entities.Inscribir;
import mx.uam.integracion.Inscripciones.repository.IInscribirRepository;
import mx.uam.integracion.Inscripciones.service.IInscribirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscribirServiceImpl implements IInscribirService {

    private final IInscribirRepository inscribirRepository;

    @Autowired
    public InscribirServiceImpl(IInscribirRepository inscribirRepository) {
        this.inscribirRepository = inscribirRepository;
    }

    @Override
    public List<Inscribir> getAll() {
        return inscribirRepository.findAll();
    }

    @Override
    public Optional<Inscribir> getById(Long id) {
        return inscribirRepository.findById(id);
    }

    @Override
    public Inscribir save(Inscribir inscribir) {
        return inscribirRepository.save(inscribir);
    }

    @Override
    public Inscribir update(Long id, Inscribir inscribir) {
        Optional<Inscribir> existente = inscribirRepository.findById(id);
        if (existente.isPresent()) {
            inscribir.setId(id);
            return inscribirRepository.save(inscribir);
        }
        return null;
    }

    @Override
    public Inscribir patch(Long id, Inscribir parcial) {
        Optional<Inscribir> existente = inscribirRepository.findById(id);
        if (existente.isPresent()) {
            Inscribir actual = existente.get();

            if (parcial.getEstatus() != null) actual.setEstatus(parcial.getEstatus());
            if (parcial.getFechaInscripcion() != null) actual.setFechaInscripcion(parcial.getFechaInscripcion());
            if (parcial.getAlumno() != null) actual.setAlumno(parcial.getAlumno());
            if (parcial.getCurso() != null) actual.setCurso(parcial.getCurso());

            return inscribirRepository.save(actual);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        inscribirRepository.deleteById(id);
    }
}
