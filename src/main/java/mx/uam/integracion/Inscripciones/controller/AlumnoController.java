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

    //Obtener todos los registros
    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> getAllAlumnos() {
        List<AlumnoDTO> alumnos = alumnoService.getAll();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    //Obtener el registro del alumno por id
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> getAlumno(@PathVariable Long id) {
        return alumnoService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear alumno
    @PostMapping
    public ResponseEntity<AlumnoDTO> createAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        try {
            AlumnoDTO createdAlumno = alumnoService.save(alumnoDTO);
            return new ResponseEntity<>(createdAlumno, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //Actualizar por id
    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDTO> updateAlumno(@PathVariable Long id, @RequestBody AlumnoDTO alumnoDTO) {
        try {
            AlumnoDTO updatedAlumno = alumnoService.update(id, alumnoDTO);
            return new ResponseEntity<>(updatedAlumno, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        try {
            alumnoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}