package mx.uam.integracion.Inscripciones.service;
import mx.uam.integracion.Inscripciones.entities.Curso;
import java.util.List;
import java.util.Optional;

public interface ICursoService {
    List<Curso> getAll();
    Optional<Curso> getById(Long id);
    Curso save(Curso curso);
    Curso update(Long id, Curso curso);
    void delete(Long id);
}
