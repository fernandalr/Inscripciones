package mx.uam.integracion.Inscripciones.repository;

import mx.uam.integracion.Inscripciones.entities.Inscribir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInscribirRepository extends JpaRepository<Inscribir, Long> {
}
