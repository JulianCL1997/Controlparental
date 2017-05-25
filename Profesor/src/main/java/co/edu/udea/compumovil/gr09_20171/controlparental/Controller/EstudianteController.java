package co.edu.udea.compumovil.gr09_20171.controlparental.Controller;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;

/**
 * Created by julian on 23/05/17.
 */

public class EstudianteController {
    private List<Estudiante> estudiantes;
    private String VALUE_REF = "Estudiantes";
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public EstudianteController() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(VALUE_REF);
        estudiantes = new ArrayList<>();
    }

    //Retorna una lista con todos los estudiantes registrados
    public void TodosLosEstudiantes() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                estudiantes.removeAll(estudiantes);
                for (DataSnapshot snapshop :
                        dataSnapshot.getChildren()) {
                    Estudiante value = snapshop.getValue(Estudiante.class);
                    estudiantes.add(value);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

public void EstudiantesGrupo(final List<String> est){
    reference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            estudiantes.removeAll(estudiantes);
            for (DataSnapshot snapshop:dataSnapshot.getChildren()
                 ) {
                if(est.contains(snapshop.getKey())){
                    Estudiante value=snapshop.getValue(Estudiante.class);
                    estudiantes.add(value);
                }

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
