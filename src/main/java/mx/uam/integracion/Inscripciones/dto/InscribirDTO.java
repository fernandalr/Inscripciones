package mx.uam.integracion.Inscripciones.dto;

import java.time.LocalDate;

public class InscribirDTO {
    private Long id;
    private Long alumnoId; // Usado en el POST
    private Long cursoId;  // Usado en el POST
    private String estatus;
    private LocalDate fechaInscripcion;

    private AlumnoDTO alumno; // Usado en el GET
    private CursoDTO curso;   // Usado en el GET

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

    public Long getAlumnoId() {
        return alumnoId;
    }
    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Long getCursoId() {
        return cursoId;
    }
    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public AlumnoDTO getAlumno() {
        return alumno;
    }
    public void setAlumno(AlumnoDTO alumno) {
        this.alumno = alumno;
    }

    public CursoDTO getCurso() {
        return curso;
    }
    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }
}