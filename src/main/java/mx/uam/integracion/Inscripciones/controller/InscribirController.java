package mx.uam.integracion.Inscripciones.controller;

import mx.uam.integracion.Inscripciones.dto.InscribirDTO;
import mx.uam.integracion.Inscripciones.service.IInscribirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/inscripciones")
public class InscribirController {

    private final IInscribirService inscribirService;

    @Autowired
    public InscribirController(IInscribirService inscribirService) {
        this.inscribirService = inscribirService;
    }

    @GetMapping
    public ResponseEntity<List<InscribirDTO>> getAllInscripciones() {
        List<InscribirDTO> inscripciones = inscribirService.getAll();
        return new ResponseEntity<>(inscripciones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createInscripcion(@RequestBody InscribirDTO inscribirDTO) {
        InscribirDTO createdInscripcion = inscribirService.save(inscribirDTO);
        return new ResponseEntity<>("El regitro fué guardado con éxito", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInscripcion(@PathVariable Long id, @RequestBody InscribirDTO inscribirDTO) {
        InscribirDTO updatedInscripcion = inscribirService.update(id, inscribirDTO);
        return new ResponseEntity<>("El registro ha sido actualizado exitosamente", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInscripcion(@PathVariable Long id) {
        inscribirService.delete(id);
        return new ResponseEntity<>("El registro ha sido borrado con éxito.", HttpStatus.OK);
    }
}