package co.edu.udea.compumovil.gr09_20171.controlparental.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;
import co.edu.udea.compumovil.gr09_20171.controlparental.Views.TabProfesor;

/**
 * Created by landres.perez on 25/05/17.
 */

public class CursoMateriaAdapter extends RecyclerView.Adapter<CursoMateriaAdapter.ViewHolder> {

    private List<CursoMateria> materiaList;
    private Context context = null;
    private boolean var = true;

    public CursoMateriaAdapter(List<CursoMateria> materiaList) {
        this.materiaList = materiaList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_cursos, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
                /*
        * Se editan los campos de los TextViews
        * */
        holder.materia.setText(materiaList.get(position).getMateria());
        holder.grado.setText(materiaList.get(position).getGrado());
        holder.grupo.setText(materiaList.get(position).getGrupo());

        /*
        * Eventos de cliqueo en el cardView.
        * */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (context, TabProfesor.class);
                intent.putExtra("materia",materiaList.get(position) );
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
        return materiaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private int colorCardView1, colorCardView2;

        CardView cardView;
        TextView materia, grado, grupo;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view_cursos);
            materia = (TextView) itemView.findViewById(R.id.materia);
            grado = (TextView) itemView.findViewById(R.id.grado);
            grupo = (TextView) itemView.findViewById(R.id.grupo);

            changeColorCardViews(itemView);
        }

        private void changeColorCardViews(View itemView) {

            colorCardView1 = itemView.getResources().getColor(R.color.carview_color1);
            colorCardView2 = itemView.getResources().getColor(R.color.cardview_color2);

            if (var) {
                cardView.setCardBackgroundColor(colorCardView2);
            } else {
                cardView.setCardBackgroundColor(colorCardView1);
            }

            var = !var;
        }
    }
}
