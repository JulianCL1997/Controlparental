package co.edu.udea.compumovil.gr09_20171.controlparental;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

import co.edu.udea.compumovil.gr09_20171.controlparental.Views.CursoMateriaView;
import co.edu.udea.compumovil.gr09_20171.controlparental.Views.LoginView;
import co.edu.udea.compumovil.gr09_20171.controlparental.Views.TabProfesor;


public class MainActivity extends AppCompatActivity {
    private static final long LOAD_DATABASE = 100;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };


        // Set portair orientacion
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //esconder barra de titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);



        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //iniciar siguiente actividad
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(MainActivity.this, CursoMateriaView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                }else{
                    Intent intent = new Intent(MainActivity.this, LoginView.class);
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
