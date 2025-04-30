package mx.uam.integracion.Inscripciones.service.impl;

import mx.uam.integracion.Inscripciones.dto.AlumnoDTO;
import mx.uam.integracion.Inscripciones.entities.Alumno;
import mx.uam.integracion.Inscripciones.mapper.AlumnoMapper;
import mx.uam.integracion.Inscripciones.repository.IAlumnoRepository;
import mx.uam.integracion.Inscripciones.service.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

    private final IAlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoServiceImpl(IAlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<AlumnoDTO> getAll() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos.stream()
                .map(this::convertToDTO) // Convierte cada Alumno a AlumnoDTO
                .collect(Collectors.toList());
    }
    private AlumnoDTO convertToDTO(Alumno alumno) {AlumnoDTO dto = new AlumnoDTO();
        dto.setId(alumno.getId());
        dto.setMatricula(alumno.getMatricula());
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());
        dto.setDivision(alumno.getDivision());
        return dto;
    }

    @Override
    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public Alumno update(Long id, Alumno alumno) {
        if (!alumnoRepository.existsById(id)) {
            throw new RuntimeException("Alumno no encontrado con id: " + id);
        }
        alumno.setId(id);
        return alumnoRepository.save(alumno);
    }

    @Override
    public AlumnoDTO save(AlumnoDTO alumnoDTO) {
        Alumno alumno = AlumnoMapper.INSTANCE.alumnoDTOToAlumno(alumnoDTO);
        Alumno saved = alumnoRepository.save(alumno);
        return AlumnoMapper.INSTANCE.alumnoToAlumnoDTO(saved);
    }

    @Override
    public Optional<AlumnoDTO> getById(Long id) {
        return alumnoRepository.findById(id)
                .map(AlumnoMapper.INSTANCE::alumnoToAlumnoDTO);
    }

    @Override
    public void delete(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("No se encontró el ID: " + id, 1);
        }
        alumnoRepository.deleteById(id);
    }

    @Override
    public List<Alumno> getByNombre(String nombre) {
        return alumnoRepository.findByNombre(nombre);
    }

    @Override
    public List<Alumno> getByDivision(String division) {
        return alumnoRepository.findByDivision(division);
    }
    
    @Override
    public AlumnoDTO update(Long id, AlumnoDTO alumnoDTO) {
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(id);
        if (optionalAlumno.isPresent()) {
            Alumno alumno = optionalAlumno.get();

            alumno.setMatricula(alumnoDTO.getMatricula());
            alumno.setNombre(alumnoDTO.getNombre());
            alumno.setApellido(alumnoDTO.getApellido());
            alumno.setDivision(alumnoDTO.getDivision());
            Alumno updatedAlumno = alumnoRepository.save(alumno);

            return convertirADTO(updatedAlumno);
        } else {
            throw new RuntimeException("Alumno no encontrado"); // O maneja la excepción de otra manera
        }
    }

    private AlumnoDTO convertirADTO(Alumno alumno) {
        AlumnoDTO dto = new AlumnoDTO();
        dto.setId(alumno.getId());
        dto.setMatricula(alumno.getMatricula());
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());
        dto.setDivision(alumno.getDivision());
        return dto;
    }
}