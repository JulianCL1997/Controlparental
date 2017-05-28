package co.edu.udea.compumovil.gr09_20171.controlparental.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Dialogs.ConfirmarAsistencia;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.AsistenciaEstudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

/**
 * Created by landres.perez on 25/05/17.
 */

public class AsistenciaAdapter extends RecyclerView.Adapter<AsistenciaAdapter.ViewHolder> {

    private List<AsistenciaEstudiante> estudianteList;
    private Context context = null;
    private int pos;

    public AsistenciaAdapter(List<AsistenciaEstudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @Override
    public AsistenciaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_nota_asis, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AsistenciaAdapter.ViewHolder holder, final int position) {
        /*
        * Se editan los campos de los TextViews
        * */

        holder.apellidos.setText(estudianteList.get(position).getApellido());
        holder.nombre.setText(estudianteList.get(position).getNombre());
        if(estudianteList.get(position).isAsistencia()){
            holder.cardView.setBackgroundColor(holder.color2);
        }else{
            holder.cardView.setBackgroundColor(holder.color1);
        }

        /*
        * Eventos de cliqueo en el cardView.
        * */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!estudianteList.get(position).isAsistencia()) {
                    FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                    ConfirmarAsistencia dialogo = new ConfirmarAsistencia();
                    Bundle bundle = new Bundle();
                    CursoMateria curso = (CursoMateria) ((FragmentActivity) context).getIntent().getSerializableExtra("materia");
                    bundle.putSerializable("materia", curso);
                    bundle.putSerializable("estudiante", estudianteList.get(position));
                    dialogo.setArguments(bundle);
                    dialogo.show(fragmentManager, "tagAlerta");
                }
                Toast.makeText(context, "Apellidos: " + estudianteList.get(position).getApellido(),
                      Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        // Aquí permite conocer el tamaño de la lista.
        return estudianteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nombre, apellidos;
        private int color1, color2;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view_nota_asis);
            nombre = (TextView) itemView.findViewById(R.id.nombre_estudiante);
            apellidos = (TextView) itemView.findViewById(R.id.apellido_estudiante);
            color1 = itemView.getResources().getColor(R.color.negative_assistence);
            color2 = itemView.getResources().getColor(R.color.positive_assistence);



        }



    }
}
