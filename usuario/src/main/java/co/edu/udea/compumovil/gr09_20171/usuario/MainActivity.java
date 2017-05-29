package co.edu.udea.compumovil.gr09_20171.usuario;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
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

import co.edu.udea.compumovil.gr09_20171.usuario.Controller.NotaController;
import co.edu.udea.compumovil.gr09_20171.usuario.Models.Asistencia;

public class MainActivity extends AppCompatActivity {
    Asistencia asistencia;
    NotificationCompat.Builder mBuilder;
    int mNotificationId = 11123456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mBuilder = new NotificationCompat.Builder(this).setSmallIcon(android.R.drawable.ic_notification_clear_all)
                .setContentTitle("Asistencia");
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String estudiante = "1606110755899";
        DatabaseReference reference = database.getReference("Materias");
        final DatabaseReference asis = database.getReference("Ultima_Asistencia").child(estudiante);
        Calendar calendar = Calendar.getInstance();
        String dia = String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
        String mes = String.valueOf(calendar.get(calendar.MONTH) + 1);
        String anio = String.valueOf(calendar.get(calendar.YEAR));
        final String fecha = dia + "-" + mes + "-" + anio;
        final NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        asis.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                        Asistencia asistencia = dataSnapshot.getValue(Asistencia.class);
                        mBuilder.setContentText("Asistio a " + asistencia.getMateria());
                        Toast.makeText(getApplicationContext(), "Asistio a " + asistencia.getMateria(), Toast.LENGTH_SHORT).show();

                        mNotifyMgr.notify(mNotificationId, mBuilder.build());

                               }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        /* reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot materia : dataSnapshot.getChildren()
                        ) {
                    for (DataSnapshot grado :
                            materia.getChildren()) {
                        for (DataSnapshot grupo :
                                grado.getChildren()) {


                        }

                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
