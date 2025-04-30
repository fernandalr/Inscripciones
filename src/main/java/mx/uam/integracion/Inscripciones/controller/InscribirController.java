package mx.uam.integracion.Inscripciones.controller;

import mx.uam.integracion.Inscripciones.dto.InscribirDTO;
import mx.uam.integracion.Inscripciones.service.IInscribirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            InscribirDTO createdInscripcion = inscribirService.save(inscribirDTO);
            System.out.println("Inscripción creada: " + createdInscripcion);
            return new ResponseEntity<>("El registro fue guardado con éxito.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la inscripción. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInscripcion(@PathVariable Long id, @RequestBody InscribirDTO inscribirDTO) {
        try {
            InscribirDTO updatedInscripcion = inscribirService.update(id, inscribirDTO);
            System.out.println("Inscripción actualizada: " + updatedInscripcion);
            return new ResponseEntity<>("El registro ha sido actualizado exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la inscripción. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateInscripcion(@PathVariable Long id, @RequestBody InscribirDTO inscribirDTO) {
        try {
            // Reutilizamos `update` para realizar actualizaciones parciales
            InscribirDTO updatedInscripcion = inscribirService.update(id, inscribirDTO);
            System.out.println("Inscripción actualizada parcialmente: " + updatedInscripcion);
            return new ResponseEntity<>("El registro ha sido actualizado parcialmente con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar parcialmente la inscripción. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInscripcion(@PathVariable Long id) {
        try {
            // Intentar eliminar directamente
            inscribirService.delete(id);
            System.out.println("Inscripción eliminada con ID: " + id);
            return new ResponseEntity<>("El registro ha sido borrado con éxito.", HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            // Si el elemento no existe, capturar la excepción
            System.out.println("Intento de eliminar inscripción inexistente con ID: " + id);
            return new ResponseEntity<>("Error: No se encontró una inscripción con el ID " + id, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            // Manejar otros errores generales
            System.out.println("Error al eliminar inscripción: " + ex.getMessage());
            return new ResponseEntity<>("Error al eliminar la inscripción. " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}