package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Adapter.AsistenciaAdapter;
import co.edu.udea.compumovil.gr09_20171.controlparental.Adapter.NotasAdapter;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

public class FragmentNotas extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<String> estudiantes;
    private List<Estudiante> estudianteList;
    private NotasAdapter adapter;
    private CursoMateria materia;

    //referencias base de datos
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference RefMat;
    private Query RefEst;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        materia = (CursoMateria) getArguments().getSerializable("materia");
        RefMat = database.getReference("Materias").child(materia.getMateria()).
                child(materia.getGrado()).child(materia.getGrupo());
        RefEst = database.getReference().child("Estudiantes").orderByChild("apellido");
        //Inicio arreglos
        estudianteList = new ArrayList<>();
        estudiantes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Declaramos e inicializamos.
        View view = inflater.inflate(R.layout.activity_recycler, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this.getContext());    // Mirar si tira error.
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        //Declaramos adaptador e iniciamos recycler
        adapter = new NotasAdapter(estudianteList);
        recyclerView.setAdapter(adapter);

        //iniciamos busqueda de los estudiantes del grupo
        RefMat.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                estudiantes.removeAll(estudiantes);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()
                        ) {
                    if (!"profesor".equals(snapshot.getKey()) && !"grupo".equals(snapshot.getKey()))
                        estudiantes.add(snapshot.getKey());

                }
                //filtramos estu
                RefEst.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        estudianteList.removeAll(estudianteList);
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()
                                ) {
                            if (estudiantes.contains(snapshot.getKey())) {
                                Estudiante value = snapshot.getValue(Estudiante.class);
                                estudianteList.add(value);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

    private void lista() {
        estudianteList = new ArrayList<>();

        estudianteList.add(new Estudiante("Leonardo Andrés", "Perez Castilla"));
        estudianteList.add(new Estudiante("Karen Marcela", "Perez Castilla"));
        estudianteList.add(new Estudiante("Donaldo", "Pérez"));
        estudianteList.add(new Estudiante("Emilia", ""));
    }
}
