package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Adapter.AsistenciaAdapter;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

public class FragmentAsistencia extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Estudiante> estudianteList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Declaramos e inicializamos.
        View view = inflater.inflate(R.layout.activity_recycler, container, false);
        AsistenciaAdapter adapter;

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this.getContext());    // Mirar si tira error.
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


        // ---  Borrar

        lista();

        // --- Borrar


        adapter = new AsistenciaAdapter(estudianteList);
        recyclerView.setAdapter(adapter);

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