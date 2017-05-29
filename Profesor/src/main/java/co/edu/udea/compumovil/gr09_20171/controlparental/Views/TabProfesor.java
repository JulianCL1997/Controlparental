package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
                FragmentEstudiante.class, bundle); // Notas.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_tabhost, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.regresar == item.getItemId()) {

            Toast.makeText(this, "Regresar", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }
}