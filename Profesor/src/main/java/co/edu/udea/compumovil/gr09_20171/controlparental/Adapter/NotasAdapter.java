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

import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Dialogs.CambiarNota;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.CursoMateria;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Nota;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

/**
 * Created by julian on 29/05/17.
 */

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.ViewHolder> {
    private List<Nota> notaList;
    private Context context = null;

    public NotasAdapter(List<Nota> notaList) {
        this.notaList = notaList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_notas, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        context=parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.desc.setText(notaList.get(position).getDesc());
        holder.nota.setText(String.valueOf(notaList.get(position).getValor()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=((FragmentActivity)context).getSupportFragmentManager();
                CambiarNota dialogo=new CambiarNota();
                Bundle bundle = new Bundle();
                CursoMateria curso = (CursoMateria) ((FragmentActivity) context).getIntent().getSerializableExtra("materia");
                Estudiante estudiante=(Estudiante)((FragmentActivity)context).getIntent().getSerializableExtra("estudiante");
                bundle.putSerializable("materia", curso);
                bundle.putSerializable("estudiante", estudiante);
                bundle.putSerializable("nota",notaList.get(position));
                dialogo.setArguments(bundle);
                dialogo.show(fragmentManager,"Inicio");

            }
        });
    }

    @Override
    public int getItemCount() {
        return notaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView desc, nota;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view_notas);
            desc = (TextView) itemView.findViewById(R.id.procede_nota);
            nota = (TextView) itemView.findViewById(R.id.nota);
        }
    }
}
