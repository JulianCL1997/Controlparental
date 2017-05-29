package co.edu.udea.compumovil.gr09_20171.controlparental.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Nota;
import co.edu.udea.compumovil.gr09_20171.controlparental.R;

/**
 * Created by leonardo on 28/05/17.
 */

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.ViewHolder> {

    private List<Nota> notaList;
    private Context context = null;

    public NotasAdapter(List<Nota> notaList) {
        this.notaList = notaList;
    }

    @Override
    public NotasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_notas, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotasAdapter.ViewHolder holder, int position) {
        holder.desc.setText(notaList.get(position).getDesc());
        holder.nota.setText((int) notaList.get(position).getValor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
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
