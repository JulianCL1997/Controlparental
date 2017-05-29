package co.edu.udea.compumovil.gr09_20171.usuario.Models;

/**
 * Created by julian on 28/05/17.
 */

public class Asistencia {
    String materia;
   String fecha;

    public Asistencia() {
    }

    public Asistencia(String materia, String fecha) {
        this.materia = materia;
        this.fecha = fecha;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }



    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
