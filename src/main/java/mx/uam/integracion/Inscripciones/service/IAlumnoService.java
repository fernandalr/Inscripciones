package mx.uam.integracion.Inscripciones.service;

import mx.uam.integracion.Inscripciones.entities.Alumno;

import java.util.List;
import java.util.Optional;

public interface IAlumnoService {
    List<Alumno> getAll();
    Optional<Alumno> getById(Long id);
    Alumno save(Alumno alumno);
    Alumno update(Long id, Alumno alumno);
    void delete(Long id);
}

