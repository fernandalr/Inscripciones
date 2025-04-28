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
    public ResponseEntity<CursoDTO> createCurso(@RequestBody CursoDTO cursoDTO) {
        CursoDTO createdCurso = cursoService.save(cursoDTO);
        return new ResponseEntity<>(createdCurso, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> updateCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        try {
            CursoDTO updatedCurso = cursoService.update(id, cursoDTO);
            return new ResponseEntity<>(updatedCurso, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}