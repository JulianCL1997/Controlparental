package co.edu.udea.compumovil.gr09_20171.usuario;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import co.edu.udea.compumovil.gr09_20171.usuario.Models.Asistencia;
import co.edu.udea.compumovil.gr09_20171.usuario.Views.LoginViews;
import co.edu.udea.compumovil.gr09_20171.usuario.Views.NotasView;

public class MainActivity extends AppCompatActivity {
    private static final long LOAD_DATABASE = 200;




    private final String filename = "Estudiante.txt";
    private String estudiante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(
                    openFileInput(filename)));

            estudiante = fin.readLine();
            fin.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }



        // Set portair orientacion
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //esconder barra de titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //iniciar siguiente actividad

                if (estudiante != null) {
                    Intent intent = new Intent(MainActivity.this, NotasView.class);
                    intent.putExtra("uid",estudiante);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                } else {
                    Intent intent = new Intent(MainActivity.this, LoginViews.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }


                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, LOAD_DATABASE);
    }










}
