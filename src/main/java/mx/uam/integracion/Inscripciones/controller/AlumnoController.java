package mx.uam.integracion.Inscripciones.controller;

import mx.uam.integracion.Inscripciones.dto.AlumnoDTO;
import mx.uam.integracion.Inscripciones.service.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/alumnos")
public class AlumnoController {

    private final IAlumnoService alumnoService;

    @Autowired
    public AlumnoController(IAlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // Obtener todos los registros
    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> getAllAlumnos() {
        List<AlumnoDTO> alumnos = alumnoService.getAll();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    // Obtener el registro del alumno por id
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> getAlumno(@PathVariable Long id) {
        return alumnoService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear alumno
    @PostMapping
    public ResponseEntity<String> createAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        try {
            AlumnoDTO createdAlumno = alumnoService.save(alumnoDTO);
            System.out.println("Alumno creado: " + createdAlumno);
            return new ResponseEntity<>("Alumno creado con éxito.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el alumno. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar alumno completamente por ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAlumno(@PathVariable Long id, @RequestBody AlumnoDTO alumnoDTO) {
        try {
            AlumnoDTO updatedAlumno = alumnoService.update(id, alumnoDTO);
            System.out.println("Alumno actualizado: " + updatedAlumno);
            return new ResponseEntity<>("Alumno actualizado con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el alumno. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Actualización parcial del alumno
    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateAlumno(@PathVariable Long id, @RequestBody AlumnoDTO alumnoDTO) {
        try {
            // Implementación lógica para actualizar parcialmente, reutilizando `update`
            AlumnoDTO updatedAlumno = alumnoService.update(id, alumnoDTO);
            System.out.println("Alumno actualizado parcialmente: " + updatedAlumno);
            return new ResponseEntity<>("Alumno actualizado parcialmente con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar parcialmente el alumno. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlumno(@PathVariable Long id) {
        try {
            alumnoService.delete(id);
            System.out.println("Alumno eliminado con ID: " + id);
            return new ResponseEntity<>("Alumno eliminado con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el alumno. " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}