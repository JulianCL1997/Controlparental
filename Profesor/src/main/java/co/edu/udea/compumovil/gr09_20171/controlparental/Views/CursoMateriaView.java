package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Adapter.CursoMateriaAdapter;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

/**
 * Created by landres.perez on 26/05/17.
 */

public class CursoMateriaView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<CursoMateria> cursoMateriasList;
    private CursoMateriaAdapter adapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Si se piensa agregar un nuevo botón. Hay que crear un nuevo layout, dado que ese uso para meterlos todos :v.
        setContentView(R.layout.activity_recycler);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);    // Mirar si tira error.
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        cursoMateriasList = new ArrayList<>();
        adapter = new CursoMateriaAdapter(cursoMateriasList);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Materias");
        //final String profesor = user.getUid();
        final String profesor = "QZqTq4NxzeNiNNv4XQtOxjLQPwJ2";//valor para pruebas sin login
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cursoMateriasList.removeAll(cursoMateriasList);
                for (DataSnapshot materias : dataSnapshot.getChildren()

                        ) {
                    for (DataSnapshot grado : materias.getChildren()
                            ) {
                        for (DataSnapshot grupo : grado.getChildren()
                                ) {
                            String prueba = grupo.child("profesor").getValue(String.class);
                            if (profesor.equals(grupo.child("profesor").getValue(String.class))) {

                                CursoMateria value = new CursoMateria(
                                        materias.getKey(),
                                        grado.getKey(),
                                        grupo.getKey(),
                                        profesor);
                                cursoMateriasList.add(value);

                            }

                        }

                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
