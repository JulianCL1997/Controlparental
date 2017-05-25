package co.edu.udea.compumovil.gr09_20171.controlparental;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import co.edu.udea.compumovil.gr09_20171.controlparental.Controller.ControllerMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.Controller.EstudianteController;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Materia;


public class MainActivity extends AppCompatActivity {
    List<Materia> materias = new ArrayList<>();
    List<Estudiante> estudents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread hiloa = new Thread(new ControllerMateria(), "Hilo a");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Materias");

        final ControllerMateria materia = new ControllerMateria();
        final EstudianteController est = new EstudianteController();

       /* String key = myRef.push().getKey();
        myRef.child("Esp01").child("-Kkss-u-cmrJrnjS6sg9")
                .child("9-2").child("1037655130").child("ASISTENCIAS").child(key).child("asistencia").setValue("SI");
        Calendar calendar = Calendar.getInstance();
        String dia = String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
        String mes = String.valueOf(calendar.get(calendar.MONTH) + 1);
        String anio = String.valueOf(calendar.get(calendar.YEAR));
        String fecha = dia + "/" + mes + "/" + anio;
        myRef.child("Esp01").child("-Kkss-u-cmrJrnjS6sg9")
                .child("9-2").child("1037655130").child("ASISTENCIAS").child(key).child("fecha").setValue(fecha);
*/
  //Ejemplo de subida de nota
        /*myRef.child("Esp01").child("-Kkss-u-cmrJrnjS6sg9")
                .child("9-2").child("1037655130").ASISTENCIASchild("NOTAS").child("NOTA_3").setValue(3);
*/
        materia.RegistrarAsistencia("Esp01","-Kkss-u-cmrJrnjS6sg9","9-2","358254312",true);
        //Ejemplo ejecucion multiples metodos asincronos usando temporizadores
        /*
         List<String> lis=new ArrayList<>();
        lis.add("1037655130");
        lis.add("97032709625");

        TimerTask task1=new TimerTask() {
            @Override
            public void run() {
                materia.EstudiantesGrupo("Esp01","-Kkss-u-cmrJrnjS6sg9","9-2");
            }
        };
        TimerTask task2=new TimerTask() {
            @Override
            public void run() {
                est.EstudiantesGrupo(materia.getEstudiantesGrupo());
            }
        };
        TimerTask task3=new TimerTask() {
            @Override
            public void run() {
          est.getEstudiantes();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task1, 5000);
        timer.schedule(task2,20000);
      timer.schedule(task3,35000);
*/
    }
}
