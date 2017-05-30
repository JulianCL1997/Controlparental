package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

public class TabProfesor extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    private FragmentTabHost tabhost;
    private CursoMateria materia;
    String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        setContentView(R.layout.activity_tab_profesor);
        materia = (CursoMateria) getIntent().getSerializableExtra("materia");
        Bundle bundle = new Bundle();
        bundle.putSerializable("materia", materia);
        tabhost = (FragmentTabHost) findViewById(R.id.tabhost);
        fecha = getfecha();


        tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent); //Define el contenedor

        // Agregar elementos.
        tabhost.addTab(tabhost.newTabSpec("asistencia").setIndicator(getString(R.string.asistencia)),
                FragmentAsistencia.class, bundle); // Asistencia.

        tabhost.addTab(tabhost.newTabSpec("notas").setIndicator(getString(R.string.notas)),
                FragmentEstudiante.class, bundle); // Notas.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        enableForegroundDispatchSystem();
    }

    @Override
    protected void onPause() {
        super.onPause();

        disableForegroundDispatchSystem();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag == null) {
                //textViewInfo.setText("tag == null");
            } else {
                MifareClassic mifareClassicTag = MifareClassic.get(tag);
                new ReadMifareClassicTask(mifareClassicTag, materia).execute();
            }
        } else {
            Toast.makeText(this, "onResume() : ",
                    Toast.LENGTH_SHORT).show();
        }

    }


    private void enableForegroundDispatchSystem() {

        Intent intent = new Intent(this, TabProfesor.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        IntentFilter[] intentFilters = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
    }

    private void disableForegroundDispatchSystem() {
        nfcAdapter.disableForegroundDispatch(this);
    }


    private class ReadMifareClassicTask extends AsyncTask<Void, Void, Void> {

        /*
        MIFARE Classic tags are divided into sectors, and each sector is sub-divided into blocks.
        Block size is always 16 bytes (BLOCK_SIZE). Sector size varies.
        MIFARE Classic 1k are 1024 bytes (SIZE_1K), with 16 sectors each of 4 blocks.
        */

        MifareClassic taskTag;
        CursoMateria materia;
        final String CCTYPE="CC";
        boolean success;
        final int blocks=2;
        final int SECTOR_READ = 18;
        final int BLOCK_READ = 72;
        byte[][] buffer = new byte[2][MifareClassic.BLOCK_SIZE];

        ReadMifareClassicTask(MifareClassic tag, CursoMateria mat) {
            materia = mat;
            taskTag = tag;
            success = false;
        }

        @Override
        protected void onPreExecute() {
            //textViewBlock.setText("Reading Tag, don't remove it!");
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                taskTag.connect();
                byte[] key = {(byte) 0xa0, (byte) 0xa1, (byte) 0xa2, (byte) 0xa3, (byte) 0xa4, (byte) 0xa5, (byte) 0x78,
                        (byte) 0x77, (byte) 0x88, (byte) 0x00};
                if (taskTag.authenticateSectorWithKeyA(SECTOR_READ, key)) {
                    for(int b=0;b<blocks;b++) {
                        int index=BLOCK_READ+b;
                        buffer[b] = taskTag.readBlock(index);

                    }
                }


                success = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (taskTag != null) {
                    try {
                        taskTag.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //display block
            if (success) {
                String stringBlock = null;
                try {
                    stringBlock = new String(buffer[0], "UTF-8");
                    String type=stringBlock.substring(1,3);
                    if(type.equals(CCTYPE)) {
                        stringBlock = stringBlock.substring(5, stringBlock.length() - 1);
                    }else{
                        stringBlock = stringBlock.substring(5, stringBlock.length());
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                final String Estudiante = stringBlock;
                if (materia != null) {
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference reference = database.getReference("Materias")
                            .child(materia.getMateria()).child(materia.getGrado()).child(materia.getGrupo());
                    final DatabaseReference asis = database.getReference("Ultima_Asistencia");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(Estudiante).exists()) {
                                if (!dataSnapshot.child(Estudiante).child("Asistencias").child(fecha).exists()) {
                                    reference.child(Estudiante).child("Asistencias").child(fecha).setValue(fecha);
                                    asis.child(Estudiante).child("fecha").setValue(fecha);
                                    asis.child(Estudiante).child("materia").setValue(materia.getMateria());
                                    Toast.makeText(getApplicationContext(), "Asistencia Registrada", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Asistencia ya registrada", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "El estudiante no es de este curso", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                //stringBlock += String.format("%02X", buffer[k] & 0xff) + " ";

                // txtTagContent.setText(stringBlock);
            } else {
                Toast.makeText(getApplicationContext(), "Operacion Cancelada Intente de nuevo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getfecha() {
        Calendar calendar = Calendar.getInstance();
        String dia = String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
        String mes = String.valueOf(calendar.get(calendar.MONTH) + 1);
        String anio = String.valueOf(calendar.get(calendar.YEAR));
        return dia + "-" + mes + "-" + anio;
    }
}