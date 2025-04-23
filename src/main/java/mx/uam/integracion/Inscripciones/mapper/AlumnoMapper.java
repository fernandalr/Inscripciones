package mx.uam.integracion.Inscripciones.mapper;

import mx.uam.integracion.Inscripciones.dto.AlumnoDTO;
import mx.uam.integracion.Inscripciones.entities.Alumno;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlumnoMapper {
    AlumnoMapper INSTANCE = Mappers.getMapper(AlumnoMapper.class);

    AlumnoDTO alumnoToAlumnoDTO(Alumno alumno);
    Alumno alumnoDTOToAlumno(AlumnoDTO alumnoDTO);
}