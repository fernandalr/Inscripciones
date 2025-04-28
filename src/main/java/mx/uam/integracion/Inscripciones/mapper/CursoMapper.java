package mx.uam.integracion.Inscripciones.mapper;

import mx.uam.integracion.Inscripciones.dto.CursoDTO;
import mx.uam.integracion.Inscripciones.entities.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CursoMapper {
    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    Curso cursoDTOToCurso(CursoDTO cursoDTO);
    CursoDTO cursoToCursoDTO(Curso curso);
}