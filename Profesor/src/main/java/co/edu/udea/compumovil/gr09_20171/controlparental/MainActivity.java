package co.edu.udea.compumovil.gr09_20171.controlparental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
        DatabaseReference grupo = database.getReference("Materias").child("9").child("a").child("S32GgmaLmCSlNmHiMyJ1dedKcEs1");
        DatabaseReference estudiante=database.getReference("Estudiantes");



    }
}
