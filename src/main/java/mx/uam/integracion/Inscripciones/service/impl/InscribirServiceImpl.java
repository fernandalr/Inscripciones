package mx.uam.integracion.Inscripciones.service.impl;

import mx.uam.integracion.Inscripciones.dto.InscribirDTO;
import mx.uam.integracion.Inscripciones.entities.Alumno;
import mx.uam.integracion.Inscripciones.entities.Curso;
import mx.uam.integracion.Inscripciones.entities.Inscribir;
import mx.uam.integracion.Inscripciones.mapper.InscribirMapper;
import mx.uam.integracion.Inscripciones.repository.IInscribirRepository;
import mx.uam.integracion.Inscripciones.service.IInscribirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mx.uam.integracion.Inscripciones.repository.IAlumnoRepository;
import mx.uam.integracion.Inscripciones.repository.ICursoRepository;

@Service
public class InscribirServiceImpl implements IInscribirService {

    private final IInscribirRepository inscribirRepository;
    private final IAlumnoRepository alumnoRepository; // Añadir este repositorio
    private final ICursoRepository cursoRepository;   // Añadir este repositorio

    @Autowired
    public InscribirServiceImpl(IInscribirRepository inscribirRepository,
                                IAlumnoRepository alumnoRepository, 
                                ICursoRepository cursoRepository) {
        this.inscribirRepository = inscribirRepository;
        this.alumnoRepository = alumnoRepository; // Inyectar el repositorio
        this.cursoRepository = cursoRepository;   // Inyectar el repositorio
    }

    @Override
    public List<InscribirDTO> getAll() {
        List<Inscribir> inscripciones = inscribirRepository.findAll();
        return inscripciones.stream()
                .map(InscribirMapper.INSTANCE::inscribirToInscribirDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InscribirDTO save(InscribirDTO inscribirDTO) {
        Inscribir inscribir = InscribirMapper.INSTANCE.inscribirDTOToInscribir(inscribirDTO);
        Inscribir savedInscribir = inscribirRepository.save(inscribir);
        return InscribirMapper.INSTANCE.inscribirToInscribirDTO(savedInscribir);
    }

    @Override
    public InscribirDTO update(Long id, InscribirDTO inscribirDTO) {
        Optional<Inscribir> optionalInscripcion = inscribirRepository.findById(id);
        if (!optionalInscripcion.isPresent()) {
            throw new RuntimeException("Inscripción no encontrada con el ID: " + id);
        }

        Inscribir inscripcionExistente = optionalInscripcion.get();
        if (inscribirDTO.getEstatus() != null) {
            inscripcionExistente.setEstatus(inscribirDTO.getEstatus());
        }
        if (inscribirDTO.getAlumno() != null) {
            if (inscribirDTO.getAlumno().getId() != null) {
                Alumno alumno = buscarAlumnoPorId(inscribirDTO.getAlumno().getId());
                inscripcionExistente.setAlumnoId(alumno);
            } else {
                throw new RuntimeException("El Alumno debe tener un ID válido.");
            }
        }
        if (inscribirDTO.getCurso() != null) {
            if (inscribirDTO.getCurso().getId() != null) {
                Curso curso = buscarCursoPorId(inscribirDTO.getCurso().getId());
                inscripcionExistente.setCursoId(curso);
            } else {
                throw new RuntimeException("El Curso debe tener un ID válido.");
            }
        }
        if (inscribirDTO.getFechaInscripcion() != null) {
            inscripcionExistente.setFechaInscripcion(inscribirDTO.getFechaInscripcion());
        }

        Inscribir updatedInscripcion = inscribirRepository.save(inscripcionExistente);
        return InscribirMapper.INSTANCE.inscribirToInscribirDTO(updatedInscripcion);
    }

    @Override
    public void delete(Long id) {
        if (!inscribirRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el ID: " + id);
        }
        inscribirRepository.deleteById(id);
    }

    @Override
    public InscribirDTO findById(Long id) {
        Optional<Inscribir> resultado = inscribirRepository.findById(id);
        if (resultado.isPresent()) {
            return InscribirMapper.INSTANCE.inscribirToInscribirDTO(resultado.get());
        } else {
            throw new RuntimeException("No se encontró una inscripción con id: " + id);
        }
    }

    private Alumno buscarAlumnoPorId(Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + id));
    }

    private Curso buscarCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
    }
}