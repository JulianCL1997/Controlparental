package co.edu.udea.compumovil.gr09_20171.controlparental.Model;

import java.io.Serializable;

/**
 * Created by julian on 21/05/17.
 */

public class Estudiante implements Serializable{
    private String uid;
    private String nombre;
    private String apellido;

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


}
