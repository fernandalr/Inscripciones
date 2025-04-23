package mx.uam.integracion.Inscripciones.service;

import mx.uam.integracion.Inscripciones.dto.CursoDTO;
import mx.uam.integracion.Inscripciones.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoService{
    List<Curso> getAll();
    CursoDTO save(CursoDTO cursoDTO);
    Optional<CursoDTO> getById(Long id);
    CursoDTO update(Long id, CursoDTO cursoDTO);
    void delete(Long id);
}