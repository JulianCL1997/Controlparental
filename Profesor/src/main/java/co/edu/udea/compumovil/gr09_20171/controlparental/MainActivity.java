package co.edu.udea.compumovil.gr09_20171.controlparental;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;


public class MainActivity extends AppCompatActivity {
    List<CursoMateria> cursos = new ArrayList<>();
    List<Estudiante> estudents = new ArrayList<>();
    List<String> existe = new ArrayList<String>();

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Materias");

        Calendar calendar = Calendar.getInstance();
        String dia = String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
        String mes = String.valueOf(calendar.get(calendar.MONTH) + 1);
        String anio = String.valueOf(calendar.get(calendar.YEAR));
        String fecha = dia + "-" + mes + "-" + anio;

        //Registro si asistio
        reference.child("Historia").child("9")
                .child("a").child("97032709625").child("Asistencias").child(fecha).child("Fecha").setValue(fecha);
    }*/
    TextView boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        boton = (TextView) findViewById(R.id.soyunboton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
