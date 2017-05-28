package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

public class TabProfesor extends AppCompatActivity {

    private FragmentTabHost tabhost;
    private CursoMateria materia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_profesor);
        materia = (CursoMateria) getIntent().getSerializableExtra("materia");
        Bundle bundle = new Bundle();
        bundle.putSerializable("materia", materia);
        tabhost = (FragmentTabHost) findViewById(R.id.tabhost);

        tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent); //Define el contenedor

        // Agregar elementos.
        tabhost.addTab(tabhost.newTabSpec("asistencia").setIndicator(getString(R.string.asistencia)),
                FragmentAsistencia.class, bundle); // Asistencia.

        tabhost.addTab(tabhost.newTabSpec("notas").setIndicator(getString(R.string.notas)),
                FragmentNotas.class, bundle); // Notas.

    }
}