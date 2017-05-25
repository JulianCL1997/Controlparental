package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import co.edu.udea.compumovil.gr09_20171.controlparental.R;

public class FragmentAsistencia extends Fragment {

    private String [] estudiantes;
    private boolean asisChecked;
    private boolean justiChecked;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Declaraciones.
        //View view = inflater.inflate(R.layout.asistencia, container, false);

        return null;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private String[] llenarEstudiantes (int estudiantes) { // Además recibiría la base de datos
        String [] listado = new String[estudiantes];
        String estudiante = "Estudiante"; // Aquí iría el nombre de cada chicuelo.

        for (int i = 1; i <= estudiantes; i++) {
            listado[i - 1] = estudiante + (Integer) i;  // Aquí tenemos que quitar el i.
        }

        return listado;
    }

    public String[] getEstudiantes() {
        return estudiantes;
    }

    public boolean isAsisChecked() {
        return asisChecked;
    }

    public boolean isJustiChecked() {
        return justiChecked;
    }
}