package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Si se piensa agregar un nuevo botón. Hay que crear un nuevo layout, dado que ese uso para meterlos todos :v.
        setContentView(R.layout.activity_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.card_view_cursos);
        linearLayoutManager = new LinearLayoutManager(this);    // Mirar si tira error.
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

        iniciar();
    }

    private void iniciar() {
        cursoMateriasList = new ArrayList<>();

        cursoMateriasList.add(new CursoMateria("Sociales", "10", "a"));
        cursoMateriasList.add(new CursoMateria("Sociales", "10", "b"));
        cursoMateriasList.add(new CursoMateria("Sociales", "10", "c"));
        cursoMateriasList.add(new CursoMateria("Sociales", "10", "d"));

        cursoMateriasList.add(new CursoMateria("Biologia", "10", "a"));
        cursoMateriasList.add(new CursoMateria("Biologia", "10", "b"));
        cursoMateriasList.add(new CursoMateria("Biologia", "10", "c"));
        cursoMateriasList.add(new CursoMateria("Biologia", "10", "d"));

        adapter = new CursoMateriaAdapter(cursoMateriasList);
    }
}
