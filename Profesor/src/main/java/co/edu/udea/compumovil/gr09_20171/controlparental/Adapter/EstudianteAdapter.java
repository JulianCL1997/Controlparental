package co.edu.udea.compumovil.gr09_20171.controlparental.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;
import co.edu.udea.compumovil.gr09_20171.controlparental.Views.CursoMateriaView;
import co.edu.udea.compumovil.gr09_20171.controlparental.Views.FragmentNota;
import co.edu.udea.compumovil.gr09_20171.controlparental.Views.LoginView;

/**
 * Created by landres.perez on 25/05/17.
 */

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder> {

    private List<Estudiante> estudianteList;
    private Context context = null;

    public EstudianteAdapter(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @Override
    public EstudianteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_nota_asis, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final EstudianteAdapter.ViewHolder holder, final int position) {
             /*
        * Se editan los campos de los TextViews
        * */
        holder.apellidos.setText(estudianteList.get(position).getApellido());
        holder.nombre.setText(estudianteList.get(position).getNombre());

        /*
        * Eventos de cliqueo en el cardView.
        * */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*FragmentManager fragmentManager=((FragmentActivity)context).getSupportFragmentManager();
                Fragment notas=new FragmentNota();
                Bundle bundle=new Bundle();
                CursoMateria curso =(CursoMateria)((FragmentActivity)context).getIntent().getSerializableExtra("materia");
                bundle.putSerializable("materia",curso);
                bundle.putSerializable("estudiante",estudianteList.get(position));
                notas.setArguments(bundle);
                fragmentManager.beginTransaction().replace(android.R.id.tabcontent,notas).commit();
*/

                Intent intent = new Intent(context, FragmentNota.class);
                CursoMateria curso = (CursoMateria) ((FragmentActivity) context).getIntent().getSerializableExtra("materia");
                intent.putExtra("materia",curso);
                intent.putExtra("estudiante",estudianteList.get(position));
                context.startActivity(intent);
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


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view_nota_asis);
            nombre = (TextView) itemView.findViewById(R.id.nombre_estudiante);
            apellidos = (TextView) itemView.findViewById(R.id.apellido_estudiante);
        }
    }
}
