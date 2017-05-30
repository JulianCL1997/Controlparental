package co.edu.udea.compumovil.gr09_20171.usuario;

/**
 * Created by julian on 30/05/17.
 */

public class Otro {

     /* mBuilder = new NotificationCompat.Builder(this).setSmallIcon(android.R.drawable.ic_notification_clear_all)
                .setContentTitle("Asistencia");
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String estudiante = "1606110755899";
        DatabaseReference reference = database.getReference("Materia");
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
        });*/



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
