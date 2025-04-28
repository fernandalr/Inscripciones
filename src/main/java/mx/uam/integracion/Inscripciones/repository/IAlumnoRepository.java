package mx.uam.integracion.Inscripciones.repository;

import mx.uam.integracion.Inscripciones.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {
    Alumno findByMatricula(String matricula);
    List<Alumno> findByNombre(String nombre);
    List<Alumno> findByDivision(String division);

}
