package mx.uam.integracion.Inscripciones.dto;

import java.time.LocalDate;

public class InscribirDTO {
    private Long id;
    private Long alumnoId;
    private Long cursoId;
    private String estatus;
    private LocalDate fechaInscripcion;

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEstatus() {
        return estatus;
    }
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }
    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Long getAlumnoId() { return alumnoId; }
    public void setAlumnoId(Long alumnoId) { this.alumnoId = alumnoId; }

    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
}