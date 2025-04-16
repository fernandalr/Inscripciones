package mx.uam.integracion.Inscripciones.repository;

import mx.uam.integracion.Inscripciones.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository extends JpaRepository<Curso, Long> {
    Curso findByClaveCurso(Long claveCurso);
}
