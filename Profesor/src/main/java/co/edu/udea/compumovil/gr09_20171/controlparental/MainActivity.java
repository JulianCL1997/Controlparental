package co.edu.udea.compumovil.gr09_20171.controlparental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import co.edu.udea.compumovil.gr09_20171.controlparental.Controller.CursoMateriaController;
import co.edu.udea.compumovil.gr09_20171.controlparental.Controller.EstudianteController;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;


public class MainActivity extends AppCompatActivity {
    List<CursoMateria> cursos = new ArrayList<>();
    List<Estudiante> estudents = new ArrayList<>();
    List<String> existe=new ArrayList<String>();
    @Override
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

    }
}
