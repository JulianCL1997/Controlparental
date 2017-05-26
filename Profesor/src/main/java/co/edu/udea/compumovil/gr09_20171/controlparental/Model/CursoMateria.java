package co.edu.udea.compumovil.gr09_20171.controlparental.Model;

/**
 * Created by julian on 21/05/17.
 */

public class CursoMateria {
    String materia;
    String grado;
    String grupo;

    public CursoMateria(String materia, String grado, String grupo) {
        this.materia = materia;
        this.grado = grado;
        this.grupo = grupo;
    }

    public CursoMateria() {
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}