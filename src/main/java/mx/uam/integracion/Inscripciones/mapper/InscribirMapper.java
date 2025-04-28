package mx.uam.integracion.Inscripciones.mapper;

import mx.uam.integracion.Inscripciones.dto.InscribirDTO;
import mx.uam.integracion.Inscripciones.entities.Alumno;
import mx.uam.integracion.Inscripciones.entities.Curso;
import mx.uam.integracion.Inscripciones.entities.Inscribir;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InscribirMapper {
    InscribirMapper INSTANCE = Mappers.getMapper(InscribirMapper.class);

    Inscribir inscribirDTOToInscribir(InscribirDTO inscribirDTO);

    InscribirDTO inscribirToInscribirDTO(Inscribir inscribir);

    // Método para mapear Long a Alumno
    default Alumno map(Long alumnoId) {
        if (alumnoId == null) {
            return null;
        }
        Alumno alumno = new Alumno();
        alumno.setId(alumnoId);
        return alumno;
    }

    // Método para mapear Long a Curso
    default Curso mapCurso(Long cursoId) {
        if (cursoId == null) {
            return null;
        }
        Curso curso = new Curso();
        curso.setId(cursoId);
        return curso;
    }

    // Método para mapear Alumno a Long
    default Long map(Alumno alumno) {
        if (alumno == null) {
            return null;
        }
        return alumno.getId();
    }

    // Método para mapear Curso a Long
    default Long map(Curso curso) {
        if (curso == null) {
            return null;
        }
        return curso.getId();
    }
}