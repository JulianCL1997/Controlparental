package co.edu.udea.compumovil.gr09_20171.controlparental.Model;

/**
 * Created by julian on 21/05/17.
 */

public class CursoMateria {
    String nombre;
    String grupo;

    public CursoMateria(String nombre, String grupo) {
        this.nombre = nombre;
        this.grupo = grupo;
    }

    public CursoMateria() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
