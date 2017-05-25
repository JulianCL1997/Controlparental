package co.edu.udea.compumovil.gr09_20171.controlparental.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.edu.udea.compumovil.gr09_20171.controlparental.Model.Estudiante;


public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.EstudiantesViewHolder>{

    private List<Estudiante> estudiantes;
    private Context context = null;

    public EstudianteAdapter(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public EstudiantesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //cambiar el R.layout.card_view_events por el layout a mostrar
        //luego descomenta el view y el holder
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_events, parent, false);
        //EstudiantesViewHolder holder = new EstudiantesViewHolder(view);
        context = parent.getContext();
        //retorna el view
        return null;
    }

    @Override
    public void onBindViewHolder(EstudiantesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

    public static class EstudiantesViewHolder extends RecyclerView.ViewHolder {
//Definicion variables contenedor de la informacion

        public EstudiantesViewHolder(View view) {
            super(view);
            //Inicializacion de los componente del contenedor
            //variable=(tipodato)view.findViewById(R.id.valor);
        }
    }
}
