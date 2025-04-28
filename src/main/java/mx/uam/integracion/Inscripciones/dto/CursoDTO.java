package mx.uam.integracion.Inscripciones.dto;

public class CursoDTO {
    private Long id;
    private String nombreCurso;
    private Long claveCurso;
    private String division;

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Long getClaveCurso() {
        return claveCurso;
    }
    public void setClaveCurso(Long claveCurso) {
        this.claveCurso = claveCurso;
    }

    public String getDivision() {
        return division;
    }
    public void setDivision(String division) {
        this.division = division;
    }
}