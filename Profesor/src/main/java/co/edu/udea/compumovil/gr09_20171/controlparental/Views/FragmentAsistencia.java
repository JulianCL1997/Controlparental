package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
import java.util.Calendar;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Adapter.AsistenciaAdapter;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.AsistenciaEstudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

public class FragmentAsistencia extends Fragment {

    private RecyclerView recyclerView;
    private CardView cardView;
    private LinearLayoutManager linearLayoutManager;
    private List<AsistenciaEstudiante> estudianteList;
    private List<String> estudiantes;
    private AsistenciaAdapter adapter;
    private CursoMateria materia;
    private String fecha;

    //referencias base de datos
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference RefMat;
    private Query RefEst;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inicio referencias
        materia = (CursoMateria) getArguments().getSerializable("materia");
        RefMat = database.getReference("Materias").child(materia.getMateria()).
                child(materia.getGrado()).child(materia.getGrupo());
        RefEst = database.getReference().child("Estudiantes").orderByChild("apellido");

        //Inicio arreglos
        estudianteList = new ArrayList<>();
        estudiantes = new ArrayList<>();
        //fecha del celular
        Calendar calendar = Calendar.getInstance();
        String dia = String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
        String mes = String.valueOf(calendar.get(calendar.MONTH) + 1);
        String anio = String.valueOf(calendar.get(calendar.YEAR));
        fecha = dia + "-" + mes + "-" + anio;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Declaramos e inicializamos.
        View view = inflater.inflate(R.layout.activity_recycler, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this.getContext());    // Mirar si tira error.
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        //Declaramos adaptador e iniciamos recycler
        adapter = new AsistenciaAdapter(estudianteList);
        recyclerView.setAdapter(adapter);
        //iniciamos busqueda de los estudiantes del grupo
        lista(view);
        //listaTest();
        return view;
    }

    private void listaTest() {
        estudianteList = new ArrayList<>();
        estudianteList.add(new AsistenciaEstudiante("Leonardo Andrés", "Perez Castilla", true));
        estudianteList.add(new AsistenciaEstudiante("Karen Marcela", "Perez Castilla", false));
        estudianteList.add(new AsistenciaEstudiante("Donaldo", "Pérez", false));
        estudianteList.add(new AsistenciaEstudiante("Emilia", "", true));
    }

    private void lista(final View view) {
        RefMat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                estudiantes.removeAll(estudiantes);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (!"profesor".equals(snapshot.getKey()) && !"grupo".equals(snapshot.getKey()))
                        estudiantes.add(snapshot.getKey());
                }
                //filtramos estudiantes
                RefEst.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        estudianteList.removeAll(estudianteList);
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()
                                ) {
                            if (estudiantes.contains(snapshot.getKey())) {
                                final AsistenciaEstudiante value = snapshot.getValue(AsistenciaEstudiante.class);
                                //se confirma estado de la asistencia
                                RefMat.child(snapshot.getKey()).child("Asistencias").
                                        addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                AsistenciaEstudiante value2 = value;
                                                value2.setAsistencia(dataSnapshot.child(fecha).exists());
                                                coloringCardsAssitence(view, dataSnapshot.child(fecha).exists());
                                                estudianteList.add(value2);
                                                adapter.notifyDataSetChanged();
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                            }
                        }
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

    }

    private void coloringCardsAssitence (View view, boolean exists) {
        // Colorear las tarjetas cuando detecte asistencia.

        int possitive = view.getResources().getColor(R.color.positive_assistence);

        cardView = (CardView) view.findViewById(R.id.card_view_nota_asis);

        if (exists) {cardView.setBackgroundColor(possitive);}
    }
}