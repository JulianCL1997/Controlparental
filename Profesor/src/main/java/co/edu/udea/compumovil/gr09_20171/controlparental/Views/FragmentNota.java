package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Adapter.NotasAdapter;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Nota;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

public class FragmentNota extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Nota> notaList;
    private NotasAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recycler, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        iniciar();
        adapter = new NotasAdapter(notaList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void iniciar() {
        notaList = new ArrayList<>(3);

        notaList.add(new Nota("Taller", 4.4));
        notaList.add(new Nota("Laboratorios", 4.7));
        notaList.add(new Nota("Quices", 4.5));
    }
}