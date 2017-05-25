package co.edu.udea.compumovil.gr09_20171.controlparental.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;
import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Materia;

/**
 * Created by julian on 24/05/17.
 */

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.MateriasViewHolder> {
    private List<Materia> materias;
    private Context context = null;

    public MateriaAdapter(List<Materia> materias) {
        this.materias = materias;
    }

    @Override
    public MateriaAdapter.MateriasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //cambiar el R.layout.card_view_events por el layout a mostrar
        //luego descomenta el view y el holder
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_events, parent, false);
        //EstudiantesViewHolder holder = new EstudiantesViewHolder(view);
        context = parent.getContext();
        //retorna el view
        return null;
    }

    @Override
    public void onBindViewHolder(MateriaAdapter.MateriasViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    public static class MateriasViewHolder extends RecyclerView.ViewHolder {
//Definicion variables contenedor de la informacion

        public MateriasViewHolder(View view) {
            super(view);
            //Inicializacion de los componente del contenedor
            //variable=(tipodato)view.findViewById(R.id.valor);
        }
    }
}
