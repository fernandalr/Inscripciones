package mx.uam.integracion.Inscripciones.service.impl;

import mx.uam.integracion.Inscripciones.entities.Curso;
import mx.uam.integracion.Inscripciones.repository.ICursoRepository;
import mx.uam.integracion.Inscripciones.service.ICursoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements ICursoService {

    private final ICursoRepository cursoRepository;

    public CursoServiceImpl(ICursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> getById(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso update(Long id, Curso curso) {
        return cursoRepository.findById(id).map(c -> {
            c.setNombreCurso(curso.getNombreCurso());
            c.setClaveCurso(curso.getClaveCurso());
            c.setDivision(curso.getDivision());
            return cursoRepository.save(c);
        }).orElse(null);
    }

    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }
}