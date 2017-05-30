package co.edu.udea.compumovil.gr09_20171.usuario.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julian on 30/05/17.
 */

public class Materia {
    List<Nota> notas;
    String materia;
    String grupo;
    String grado;

    public Materia() {
        notas=new ArrayList<>();
    }

    public Materia(String materia, String grupo, String grado) {
        this.materia = materia;
        this.grupo = grupo;
        this.grado = grado;
        notas=new ArrayList<>();
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
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

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
}
