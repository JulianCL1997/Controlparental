package co.edu.udea.compumovil.gr09_20171.controlparental.Dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DialogTitle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Nota;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

/**
 * Created by leonardo on 28/05/17.
 */

public class CambiarNota extends DialogFragment {
    Estudiante estudiante;
    CursoMateria cursoMateria;
    Nota nota;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        estudiante = (Estudiante) getArguments().getSerializable("estudiante");
        cursoMateria = (CursoMateria) getArguments().getSerializable("materia");
        nota = (Nota) getArguments().getSerializable("nota");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference("Materias").child(cursoMateria.getMateria())
                .child(cursoMateria.getGrado()).child(cursoMateria.getGrupo()).child(estudiante.getUid())
                .child("Notas").child(nota.getDesc());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_notas, null);
        final DialogTitle tex = (DialogTitle) view.findViewById(R.id.modificar_notas_title);
        final EditText notav=(EditText)view.findViewById(R.id.nota);
        notav.setText(nota.getValor());
        tex.setText(nota.getDesc());
        builder.setTitle("Subir nota").setView(view)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (!notav.getText().toString().isEmpty() && !notav.getText().toString().equals(nota.getValor())) {

                            reference.setValue(notav.getText().toString());
                            Toast.makeText(getActivity(),getString(R.string.Nota_Actualizada),Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(),"Cancelado",Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        return builder.create();
    }
}
