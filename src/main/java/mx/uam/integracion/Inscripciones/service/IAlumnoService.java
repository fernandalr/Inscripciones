package mx.uam.integracion.Inscripciones.service;

import mx.uam.integracion.Inscripciones.dto.AlumnoDTO;
import mx.uam.integracion.Inscripciones.entities.Alumno;

import java.util.List;
import java.util.Optional;

public interface IAlumnoService {
    List<AlumnoDTO> getAll();
    Alumno save(Alumno alumno);
    Alumno update(Long id, Alumno alumno);
    void delete(Long id);
    List<Alumno> getByNombre(String nombre);
    List<Alumno> getByDivision(String division);

    AlumnoDTO save(AlumnoDTO alumnoDTO);
    Optional <AlumnoDTO>getById(Long id);

    AlumnoDTO update(Long id, AlumnoDTO alumnoDTO);
}

