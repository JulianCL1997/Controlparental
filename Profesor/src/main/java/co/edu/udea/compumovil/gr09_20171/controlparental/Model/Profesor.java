package co.edu.udea.compumovil.gr09_20171.controlparental.Model;

/**
 * Created by julian on 21/05/17.
 */

public class Profesor {
    String uid;
    String nombre;
    String apellido;

    public Profesor() {
    }

    public String getUid() {
        return uid;
    }

    public Profesor(String uid, String nombre, String apellido) {
        this.uid = uid;
        this.nombre = nombre;
        this.apellido = apellido;
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
