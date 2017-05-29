package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Adapter.NotasAdapter;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Nota;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

public class FragmentNota extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Nota> notaList;
    private NotasAdapter adapter;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference;
    private CursoMateria cursoMateria;
    private Estudiante estudiante;


    public FragmentNota() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        notaList = new ArrayList<>();
        cursoMateria = (CursoMateria) getIntent().getSerializableExtra("materia");
        estudiante = (Estudiante) getIntent().getSerializableExtra("estudiante");
        reference = database.getReference("Materias").child(cursoMateria.getMateria()).child(cursoMateria.getGrado())
                .child(cursoMateria.getGrupo()).child(estudiante.getUid()).child("Notas");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new NotasAdapter(notaList);
        recyclerView.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notaList.removeAll(notaList);
                for (DataSnapshot data :
                        dataSnapshot.getChildren()) {
                    Nota value = new Nota(data.getKey(), data.getValue(String.class));
                    notaList.add(value);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    private void iniciar() {
        notaList = new ArrayList<>(3);

        notaList.add(new Nota("Taller", "4.4"));
        notaList.add(new Nota("Laboratorios", "4.7"));
        notaList.add(new Nota("Quices", "4.5"));
    }
}