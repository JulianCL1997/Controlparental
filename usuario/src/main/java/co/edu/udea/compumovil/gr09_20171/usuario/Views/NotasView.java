package co.edu.udea.compumovil.gr09_20171.usuario.Views;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.edu.udea.compumovil.gr09_20171.usuario.Adapter.ExpandableListAdapter;
import co.edu.udea.compumovil.gr09_20171.usuario.Models.Materia;
import co.edu.udea.compumovil.gr09_20171.usuario.Models.Nota;
import co.edu.udea.compumovil.gr09_20171.usuario.R;
import co.edu.udea.compumovil.gr09_20171.usuario.ServiceAsistencia;

/**
 * Created by leonardo on 29/05/17.
 */

public class NotasView extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    private TextView estudianteTittle;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private List<Materia> cursos;
    private String estudianteuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listHash = new HashMap<>();
        estudianteuid=getIntent().getStringExtra("uid");
        listDataHeader = new ArrayList<>();
        cursos = new ArrayList<>();
        setContentView(R.layout.list_expandable);
        estudianteTittle = (TextView) findViewById(R.id.nombre);
        listView = (ExpandableListView) findViewById(R.id.lvExp);
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

    private  void generar(){
        for(int i=0;i<cursos.size();i++){
            listDataHeader.add(cursos.get(i).getMateria());
            List<String> p=new ArrayList<>();
            for(int c=0;c<cursos.get(i).getNotas().size();c++){
                String valor=cursos.get(i).getNotas().get(c).getDesc()+"   "+
                        cursos.get(i).getNotas().get(c).getValor();
                p.add(valor);
            }
            listHash.put(listDataHeader.get(i),p);
        }
        listAdapter.notifyDataSetChanged();
    }


}
