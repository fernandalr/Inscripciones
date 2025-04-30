package mx.uam.integracion.Inscripciones.mapper;

import mx.uam.integracion.Inscripciones.dto.InscribirDTO;
import mx.uam.integracion.Inscripciones.entities.Inscribir;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InscribirMapper {
    InscribirMapper INSTANCE = Mappers.getMapper(InscribirMapper.class);

    // Mapear entidad -> DTO (incluidos los datos completos de alumno y curso)
    @Mappings({
        @Mapping(source = "alumnoId.id", target = "alumnoId"),
        @Mapping(source = "cursoId.id", target = "cursoId"),
        @Mapping(source = "alumnoId", target = "alumno"),
        @Mapping(source = "cursoId", target = "curso")
    })
    InscribirDTO inscribirToInscribirDTO(Inscribir inscribir);

    // Mapear DTO -> entidad (usamos solo los IDs para crear o actualizar)
    @Mappings({
        @Mapping(source = "alumnoId", target = "alumnoId.id"),
        @Mapping(source = "cursoId", target = "cursoId.id")
    })
    Inscribir inscribirDTOToInscribir(InscribirDTO inscribirDTO);
}