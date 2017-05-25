package co.edu.udea.compumovil.gr09_20171.controlparental.Controller;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Profesor;

//posiblemente no se necesite
public class ProfesorController {
    private List<Profesor> profesores;
    private String VALUE_REF="Profesores";
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public ProfesorController() {
        database=FirebaseDatabase.getInstance();
        reference=database.getReference(VALUE_REF);
    }

    //Retorna una lista con todos los profesores registrados
    public List<Profesor> ListaDeProfesores(){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profesores.removeAll(profesores);
                for (DataSnapshot snapshop :
                        dataSnapshot.getChildren()) {
                    Profesor value=snapshop.getValue(Profesor.class);
                    profesores.add(value);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return profesores;
    }
}
