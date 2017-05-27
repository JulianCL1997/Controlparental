package co.edu.udea.compumovil.gr09_20171.controlparental.Model;

import java.io.Serializable;

/**
 * Created by julian on 21/05/17.
 */

public class CursoMateria implements Serializable{
    String materia;
    String grado;
    String grupo;
    String profesor;

    public CursoMateria(String materia, String grado, String grupo, String profesor) {
        this.materia = materia;
        this.grado = grado;
        this.grupo = grupo;
        this.profesor = profesor;
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

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}