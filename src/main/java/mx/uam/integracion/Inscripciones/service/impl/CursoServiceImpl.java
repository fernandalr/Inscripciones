package mx.uam.integracion.Inscripciones.service.impl;
import mx.uam.integracion.Inscripciones.dto.CursoDTO;
import mx.uam.integracion.Inscripciones.entities.Curso;
import mx.uam.integracion.Inscripciones.mapper.CursoMapper;
import mx.uam.integracion.Inscripciones.repository.ICursoRepository;
import mx.uam.integracion.Inscripciones.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements ICursoService {

    private final ICursoRepository cursoRepository;

    @Autowired
    public CursoServiceImpl(ICursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    @Override
    public CursoDTO save(CursoDTO cursoDTO) {
        Curso curso = CursoMapper.INSTANCE.cursoDTOToCurso(cursoDTO);
        Curso saved = cursoRepository.save(curso);
        return CursoMapper.INSTANCE.cursoToCursoDTO(saved);
    }

    @Override
    public Optional<CursoDTO> getById(Long id) {
        return cursoRepository.findById(id)
                .map(CursoMapper.INSTANCE::cursoToCursoDTO);
    }

    @Override
    public CursoDTO update(Long id, CursoDTO cursoDTO) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado con id: " + id);
        }
        Curso curso = CursoMapper.INSTANCE.cursoDTOToCurso(cursoDTO);
        curso.setId(id);
        Curso updated = cursoRepository.save(curso);
        return CursoMapper.INSTANCE.cursoToCursoDTO(updated);
    }
    @Override
    public void delete(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("No se encontró el ID: " + id, 1);
        }
        cursoRepository.deleteById(id);
    }
}