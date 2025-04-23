package mx.uam.integracion.Inscripciones.service;

import mx.uam.integracion.Inscripciones.entities.Inscribir;

import java.util.List;
import java.util.Optional;

public interface IInscribirService {
    List<Inscribir> getAll();
    Optional<Inscribir> getById(Long id);
    Inscribir save(Inscribir inscribir);
    Inscribir update(Long id, Inscribir inscribir);
    Inscribir patch(Long id, Inscribir inscribir);
    void delete(Long id);
}
