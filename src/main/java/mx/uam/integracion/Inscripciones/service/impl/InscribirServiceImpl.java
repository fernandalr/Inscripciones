package mx.uam.integracion.Inscripciones.service.impl;

import mx.uam.integracion.Inscripciones.dto.InscribirDTO;
import mx.uam.integracion.Inscripciones.entities.Inscribir;
import mx.uam.integracion.Inscripciones.mapper.InscribirMapper;
import mx.uam.integracion.Inscripciones.repository.IInscribirRepository;
import mx.uam.integracion.Inscripciones.service.IInscribirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscribirServiceImpl implements IInscribirService {

    private final IInscribirRepository inscribirRepository;

    @Autowired
    public InscribirServiceImpl(IInscribirRepository inscribirRepository) {
        this.inscribirRepository = inscribirRepository;
    }

    @Override
    public List<InscribirDTO> getAll() {
        // Cargar entidades completas de las relaciones (alumno y curso) al obtener inscripciones
        List<Inscribir> inscripciones = inscribirRepository.findAll();
        return inscripciones.stream()
                .map(InscribirMapper.INSTANCE::inscribirToInscribirDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InscribirDTO save(InscribirDTO inscribirDTO) {
        // Solo se procesan los IDs al hacer POST
        Inscribir inscribir = InscribirMapper.INSTANCE.inscribirDTOToInscribir(inscribirDTO);
        Inscribir savedInscribir = inscribirRepository.save(inscribir);
        return InscribirMapper.INSTANCE.inscribirToInscribirDTO(savedInscribir);
    }

    @Override
    public InscribirDTO update(Long id, InscribirDTO inscribirDTO) {
        if (!inscribirRepository.existsById(id)) {
            throw new RuntimeException("Inscripción no encontrada con id: " + id);
        }
        Inscribir inscribir = InscribirMapper.INSTANCE.inscribirDTOToInscribir(inscribirDTO);
        inscribir.setId(id);
        Inscribir updatedInscribir = inscribirRepository.save(inscribir);
        return InscribirMapper.INSTANCE.inscribirToInscribirDTO(updatedInscribir);
    }

    @Override
    public void delete(Long id) {
        if (!inscribirRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("No se encontró el ID: " + id, 1);
        }
        inscribirRepository.deleteById(id);
    }

}