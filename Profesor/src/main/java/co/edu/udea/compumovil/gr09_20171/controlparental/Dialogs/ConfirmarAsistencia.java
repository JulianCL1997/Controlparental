package co.edu.udea.compumovil.gr09_20171.controlparental.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.AsistenciaEstudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

/**
 * Created by julian on 27/05/17.
 */

public class ConfirmarAsistencia extends DialogFragment {
    private String Apellido;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Bundle de enviados a la clase
        final CursoMateria materia = (CursoMateria) getArguments().getSerializable("materia");
        final AsistenciaEstudiante estudiante = (AsistenciaEstudiante) getArguments().getSerializable("estudiante");

        //se crea el alertdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //agregamos mensaje del dialogo
        builder.setMessage(getString(R.string.ConfirmaAsistencia)
                + estudiante.getNombre() + " " + estudiante.getApellido() + "?")

                //agregamos titulo
                .setTitle("Confirmar asistencia")

                //se pude dar nombre al boton
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //funcion para agregar asistencia
                        //toma la fecha del dispositivo
                        Calendar calendar = Calendar.getInstance();
                        String dia = String.valueOf(calendar.get(calendar.DAY_OF_MONTH));
                        String mes = String.valueOf(calendar.get(calendar.MONTH) + 1);
                        String anio = String.valueOf(calendar.get(calendar.YEAR));
                        String fecha = dia + "-" + mes + "-" + anio;

                        //metodos de firebase
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("Materias");
                        reference.child(materia.getMateria())
                                .child(materia.getGrado())
                                .child(materia.getGrupo())
                                .child(estudiante.getUid())
                                .child("Asistencias")
                                .child(fecha).child("Fecha").setValue(fecha);
                        Toast.makeText(getActivity(), R.string.Asistencia_Confirmada, Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }
}
