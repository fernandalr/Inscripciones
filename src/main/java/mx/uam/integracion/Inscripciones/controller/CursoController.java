package mx.uam.integracion.Inscripciones.controller;

import mx.uam.integracion.Inscripciones.dto.CursoDTO;
import mx.uam.integracion.Inscripciones.entities.Curso;
import mx.uam.integracion.Inscripciones.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/cursos")
public class CursoController {

    private final ICursoService cursoService;

    @Autowired
    public CursoController(ICursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> getAll() {
        return cursoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getCursoById(@PathVariable Long id) {
        return cursoService.getById(id)
                .map(cursoDTO -> new ResponseEntity<>(cursoDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createCurso(@RequestBody CursoDTO cursoDTO) {
        try {
            CursoDTO createdCurso = cursoService.save(cursoDTO);
            System.out.println("Curso creado: " + createdCurso);
            return new ResponseEntity<>("Curso creado con éxito.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el curso. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        try {
            CursoDTO updatedCurso = cursoService.update(id, cursoDTO);
            System.out.println("Curso actualizado: " + updatedCurso);
            return new ResponseEntity<>("Curso actualizado con éxito.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error al actualizar el curso. " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        try {
            CursoDTO updatedCurso = cursoService.update(id, cursoDTO);
            System.out.println("Curso actualizado parcialmente: " + updatedCurso);
            return new ResponseEntity<>("Curso actualizado parcialmente con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el curso parcialmente. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurso(@PathVariable Long id) {
        try {
            cursoService.delete(id);
            System.out.println("Curso eliminado con ID: " + id);
            return new ResponseEntity<>("Curso eliminado con éxito.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error al eliminar el curso. " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}