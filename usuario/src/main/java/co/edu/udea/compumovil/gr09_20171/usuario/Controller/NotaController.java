package co.edu.udea.compumovil.gr09_20171.usuario.Controller;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.usuario.Models.Nota;

/**
 * Created by julian on 25/05/17.
 */

public class NotaController {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private List<Nota> notas;
    private String REF_MATERIAS="Materias";
    private String REF_NOTAS="NOTAS";

    public NotaController() {
        database=FirebaseDatabase.getInstance();
        reference=database.getReference(REF_MATERIAS);
        notas=new ArrayList<>();
    }

    public void ObtenerNotasEstudiante(String materia,String profesor,String grupo,String estudiante){
        reference.child(materia).child(profesor).child(grupo).child(estudiante)
        .addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            notas.removeAll(notas);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                     ) {
                    Nota value=new Nota();
                    value.setDesc(snapshot.getKey());
                    value.setValor(snapshot.getValue(Float.class));
                    notas.add(value);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
