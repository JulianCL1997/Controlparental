package co.edu.udea.compumovil.gr09_20171.usuario.Views;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.usuario.Adapter.ExpandableListAdapter;
import co.edu.udea.compumovil.gr09_20171.usuario.MainActivity;
import co.edu.udea.compumovil.gr09_20171.usuario.Models.Asistencia;
import co.edu.udea.compumovil.gr09_20171.usuario.Models.Materia;
import co.edu.udea.compumovil.gr09_20171.usuario.Models.Nota;
import co.edu.udea.compumovil.gr09_20171.usuario.R;

/**
 * Created by leonardo on 29/05/17.
 */

public class NotasView extends AppCompatActivity {
    private final String filename = "Estudiante.txt";

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private TextView estudianteTittle;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private List<Materia> cursos;
    private String estudianteuid;
    private NotificationCompat.Builder mBuilder;
    private final int mNotificationId = 12345678;
    private FileOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBuilder = new NotificationCompat.Builder(this).setSmallIcon(android.R.drawable.ic_notification_clear_all)
                .setContentTitle("Asistencia");
        listHash = new HashMap<>();
        estudianteuid = getIntent().getStringExtra("uid");
        listDataHeader = new ArrayList<>();
        cursos = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        setContentView(R.layout.list_expandable);
        estudianteTittle = (TextView) findViewById(R.id.nombre);
        listView = (ExpandableListView) findViewById(R.id.lvExp);
        final DatabaseReference asis = database.getReference("Ultima_Asistencia").child(estudianteuid);
        //initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Materias");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cursos.removeAll(cursos);

                for (DataSnapshot materias : dataSnapshot.getChildren()

                        ) {
                    for (DataSnapshot grado : materias.getChildren()
                            ) {
                        for (DataSnapshot grupo : grado.getChildren()
                                ) {

                            for (DataSnapshot estudiante :
                                    grupo.getChildren()) {


                                if (estudianteuid.equals(estudiante.getKey())) {
                                    List<Nota> n = new ArrayList<Nota>();
                                    Materia value = new Materia(
                                            materias.getKey(),
                                            grado.getKey(),
                                            grupo.getKey());
                                    for (DataSnapshot nota :
                                            estudiante.child("Notas").getChildren()) {
                                        String des = nota.getKey();
                                        String valor = nota.getValue(String.class);
                                        Nota valor2 = new Nota(des, valor);
                                        n.add(valor2);

                                    }
                                    value.setNotas(n);
                                    cursos.add(value);

                                }
                            }
                        }

                    }
                }
                generar();
                Log.d("sintag", "Final");
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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

        DatabaseReference est = database.getReference("Estudiantes").child(estudianteuid);
        est.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                estudianteTittle.setText(dataSnapshot.child("nombre").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.cerrar_sesion, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.logout == item.getItemId()) {

            Toast.makeText(this, "Cerrar sesión", Toast.LENGTH_SHORT).show();
            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write("".getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(NotasView.this, LoginViews.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        estudianteTittle.setText("Leonardo");
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("EDMTDev");
        listDataHeader.add("Android");
        listDataHeader.add("Xamarin");
        listDataHeader.add("UWP");

        List<String> edmtDev = new ArrayList<>();
        edmtDev.add("This is Expandable ListView");

        List<String> androidStudio = new ArrayList<>();
        androidStudio.add("Expandable ListView");
        androidStudio.add("Google Map");
        androidStudio.add("Chat Application");
        androidStudio.add("Firebase ");

        List<String> xamarin = new ArrayList<>();
        xamarin.add("Xamarin Expandable ListView");
        xamarin.add("Xamarin Google Map");
        xamarin.add("Xamarin Chat Application");
        xamarin.add("Xamarin Firebase ");

        List<String> uwp = new ArrayList<>();
        uwp.add("UWP Expandable ListView");
        uwp.add("UWP Google Map");
        uwp.add("UWP Chat Application");
        uwp.add("UWP Firebase ");

        listHash.put(listDataHeader.get(0), edmtDev);
        listHash.put(listDataHeader.get(1), androidStudio);
        listHash.put(listDataHeader.get(2), xamarin);
        listHash.put(listDataHeader.get(3), uwp);
    }

    private void generar() {
        listDataHeader.removeAll(listDataHeader);
        for (int i = 0; i < cursos.size(); i++) {
            listDataHeader.add(cursos.get(i).getMateria());
            List<String> p = new ArrayList<>();
            for (int c = 0; c < cursos.get(i).getNotas().size(); c++) {
                String valor = cursos.get(i).getNotas().get(c).getDesc() + "   " +
                        cursos.get(i).getNotas().get(c).getValor();
                p.add(valor);
            }
            listHash.put(listDataHeader.get(i), p);
        }
        listAdapter.notifyDataSetChanged();
    }
}
