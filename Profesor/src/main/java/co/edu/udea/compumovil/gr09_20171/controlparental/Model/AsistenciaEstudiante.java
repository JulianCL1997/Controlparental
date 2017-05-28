package co.edu.udea.compumovil.gr09_20171.controlparental.Model;

/**
 * Created by julian on 27/05/17.
 */

public class AsistenciaEstudiante {
    private String nombre;
    private String apellido;
    private boolean asistencia;

    public AsistenciaEstudiante() {
    }

    public AsistenciaEstudiante(String nombre, String apellido, boolean asistencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.asistencia = asistencia;
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

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
}
