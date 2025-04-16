package mx.uam.integracion.Inscripciones.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_curso")
    private String nombreCurso;

    @Column(name = "clave_curso")
    private long claveCurso;

    @Column(name = "division")
    private String division;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public long getClaveCurso() {
        return claveCurso;
    }

    public void setClaveCurso(long claveCurso) {
        this.claveCurso = claveCurso;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}