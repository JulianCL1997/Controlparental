package co.edu.udea.compumovil.gr09_20171.usuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import co.edu.udea.compumovil.gr09_20171.usuario.Controller.NotaController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NotaController nota=new NotaController();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
nota.getNotas();
            }
        };
        Timer timer=new Timer();
        timer.schedule(task,5000);
        nota.ObtenerNotasEstudiante("Esp01","-Kkss-u-cmrJrnjS6sg9","9-1","97032709625");
    }
}
