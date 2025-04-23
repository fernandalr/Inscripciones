package mx.uam.integracion.Inscripciones.service.impl;

import mx.uam.integracion.Inscripciones.dto.AlumnoDTO;
import mx.uam.integracion.Inscripciones.entities.Alumno;
import mx.uam.integracion.Inscripciones.mapper.AlumnoMapper;
import mx.uam.integracion.Inscripciones.repository.IAlumnoRepository;
import mx.uam.integracion.Inscripciones.service.IAlumnoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

    private final IAlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(IAlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> getAll() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> getById(Long id) {
        return alumnoRepository.findById(id);
    }

    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public Alumno update(Long id, Alumno alumno) {
        alumno.setId(id);
        return alumnoRepository.save(alumno);
    }
    public AlumnoDTO save(AlumnoDTO alumnoDTO) {
        Alumno alumno = AlumnoMapper.INSTANCE.alumnoDTOToAlumno(alumnoDTO);
        Alumno saved = alumnoRepository.save(alumno);
        return AlumnoMapper.INSTANCE.alumnoToAlumnoDTO(saved);
    }

    public void delete(Long id) {
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


}
