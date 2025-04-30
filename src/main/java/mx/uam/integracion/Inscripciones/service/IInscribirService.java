package mx.uam.integracion.Inscripciones.service;

import mx.uam.integracion.Inscripciones.dto.InscribirDTO;

import java.util.List;

public interface IInscribirService {
    List<InscribirDTO> getAll();
    InscribirDTO save(InscribirDTO inscribirDTO);
    InscribirDTO update(Long id, InscribirDTO inscribirDTO);
    void delete(Long id);
}