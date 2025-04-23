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

    @Column(name = "fecha_inscripcion", nullable = false, length = 30)
    private LocalDate fechaInscripcion;

    @ManyToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "id", nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id", nullable = false)
    private Curso curso;

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

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}