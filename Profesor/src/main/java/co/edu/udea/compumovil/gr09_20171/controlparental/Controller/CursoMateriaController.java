package co.edu.udea.compumovil.gr09_20171.controlparental.Controller;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;

/**
 * Created by julian on 24/05/17.
 */

public class CursoMateriaController extends Thread {
    private List<Estudiante> estudiantes;
    private List<String> estudiantesGrupo;
    private List<CursoMateria> cursoMaterias;
    private final String REF_MATERIA = "Materias";
    // dos referencias globales para poder usarlas en cualquier punto
    private String REF_PROFESOR;
    private String REF_EESTUDIANTE;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public void Run() {

    }

    public CursoMateriaController() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(REF_MATERIA);
        estudiantes = new ArrayList<>();
        cursoMaterias = new ArrayList<>();
        estudiantesGrupo = new ArrayList<>();
    }

    //retorna una lista de grupos que da el profesor
    public void MateriasDadas(final String profesor) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //se elimina la lista para no repetir datos
                cursoMaterias.removeAll(cursoMaterias);
                //se recorre el dataSnapshot para tener los valores internos de la base de datos
                for (DataSnapshot datesnap : dataSnapshot.getChildren()) {
                    //se verifica que el profesor dicte esa materia
                    if (datesnap.child(profesor).exists()) {
                        //si el profesor da la materia se recorre los grupos que dicta
                        for (DataSnapshot snap : datesnap.child(profesor).getChildren()) {
                            if (snap.exists()) {
                                //se toma el nombre de la materia
                                CursoMateria value = datesnap.getValue(CursoMateria.class);
                                //se toma el grupo de la materia
                                value.setGrupo(snap.getKey());
                                //se agrega el grupo a la Lista
                                cursoMaterias.add(value);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    //lista los estudiantes que ven una materia especifica
    public void EstudiantesGrupo(final String materia, final String profesor, final String grupo) {
        reference.child(materia).child(profesor).child(grupo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()
                        ) {
                    estudiantesGrupo.add(snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void SubirNota(String materia, String profesor, String grupo, String estudiante, String desNota, float nota) {
        reference.child(materia).child(profesor)
                .child(grupo).child(estudiante).child("NOTAS").child(desNota).setValue(nota);
    }

    public void RegistrarAsistencia(String materia, String profesor, String grupo, String estudiante, boolean asistio) {
        //tomo los valores de la fecha actual del celular
        Calendar calendar = Calendar.getInstance();
        String dia = String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
        String mes = String.valueOf(calendar.get(calendar.MONTH) + 1);
        String anio = String.valueOf(calendar.get(calendar.YEAR));
        String fecha = dia + "-" + mes + "-" + anio;
        //Registro si asistio
        reference.child(materia).child(profesor)
                .child(grupo).child(estudiante).child("ASISTENCIAS").child(fecha).child("Asistencia").setValue(asistio);
        //registro la fecha
        reference.child(materia).child(profesor)
                .child(grupo).child(estudiante).child("ASISTENCIAS").child(fecha).child("Fecha").setValue(fecha);
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<CursoMateria> getCursoMaterias() {
        return cursoMaterias;
    }

    public void setCursoMaterias(List<CursoMateria> cursoMaterias) {
        this.cursoMaterias = cursoMaterias;
    }

    public List<String> getEstudiantesGrupo() {
        return estudiantesGrupo;
    }

    public void setEstudiantesGrupo(List<String> estudiantesGrupo) {
        this.estudiantesGrupo = estudiantesGrupo;
    }
}
