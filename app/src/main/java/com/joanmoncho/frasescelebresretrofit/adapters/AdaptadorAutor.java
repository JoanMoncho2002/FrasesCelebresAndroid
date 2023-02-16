package com.joanmoncho.frasescelebresretrofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.interfaces.IAutorFrase;
import com.joanmoncho.frasescelebresretrofit.interfaces.IOnClickListener;
import com.joanmoncho.frasescelebresretrofit.models.Autor;

import java.util.List;

public class AdaptadorAutor extends RecyclerView.Adapter<AdaptadorAutor.AutorViewHolder>{
    private final List<Autor> autorLista;
    private final IAutorFrase listener;

    public AdaptadorAutor(List<Autor> autorLista, IAutorFrase listener){
        this.autorLista = autorLista;
        this.listener = listener;
    }

    @NonNull
    public AutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autor, parent, false);
        return new AutorViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AutorViewHolder holder, int position) {
        Autor autor = autorLista.get(position);
        holder.bindAutores(autor);
    }

    @Override
    public int getItemCount() {
        return autorLista.size();
    }

    public static class AutorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvNombreAutor;
        private final TextView tvProfesion;
        private final TextView tvNacimiento;
        private final  IAutorFrase listener;

        public AutorViewHolder(@NonNull View itemView,  IAutorFrase listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            tvNombreAutor = itemView.findViewById(R.id.tvNAutor);
            tvProfesion = itemView.findViewById(R.id.tvProfesion);
            tvNacimiento = itemView.findViewById(R.id.tvNacimiento);
        }

        public void bindAutores(Autor autor){
            tvNombreAutor.setText(autor.getNombre());
            tvProfesion.setText(autor.getProfesion());
            tvNacimiento.setText(autor.getNacimiento());
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onAutorFraseSeleccionada(getAdapterPosition());
            }
        }

    }

}
