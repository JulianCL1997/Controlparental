package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

        import android.support.v4.app.FragmentActivity;
        import android.os.Bundle;
        import android.support.v4.app.FragmentTabHost;

        import co.edu.udea.compumovil.gr09_20171.controlparental.R;

public class TabProfesor extends FragmentActivity {

    private FragmentTabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_profesor);

        tabhost = (FragmentTabHost) findViewById(R.id.tab_host);

        tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent); //Define el contenedor

        // Agregar elementos.
        tabhost.addTab(tabhost.newTabSpec("asistencia").setIndicator(getString(R.string.asistencia)),
                FragmentAsistencia.class, null); // Asistencia.

        tabhost.addTab(tabhost.newTabSpec("notas").setIndicator(getString(R.string.notas)),
                FragmentNotas.class, null); // Notas.
    }


}