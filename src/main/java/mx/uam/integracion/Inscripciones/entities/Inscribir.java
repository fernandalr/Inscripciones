package mx.uam.integracion.Inscripciones.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inscribir")
public class Inscribir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estatus", nullable = false, length = 30)
    private String estatus;

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDate fechaInscripcion;

    @ManyToOne
    @JoinColumn(name = "alumno_id", referencedColumnName = "id", nullable = false)
    private Alumno alumnoId;

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    private Curso cursoId;

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

    public Alumno getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Alumno alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Curso getCursoId() {
        return cursoId;
    }

    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }
}