package com.joanmoncho.frasescelebresretrofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.interfaces.IAutorFrase;
import com.joanmoncho.frasescelebresretrofit.models.Autor;

import java.util.List;

public class AdaptadorAutor extends RecyclerView.Adapter<AdaptadorAutor.AutorViewHolder>{
    private final List<Autor> autorLista;

    public AdaptadorAutor(List<Autor> autorLista){
        this.autorLista = autorLista;
    }

    @NonNull
    @Override
    public AutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autor, parent, false);

        return new AutorViewHolder(itemView);
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

    public static class AutorViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNombreAutor;
        private final TextView tvProfesion;

        public AutorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreAutor = itemView.findViewById(R.id.tvNAutor);
            tvProfesion = itemView.findViewById(R.id.tvProfesion);
        }

        public void bindAutores(Autor autor){
            tvNombreAutor.setText(autor.getNombre());
            tvProfesion.setText(autor.getProfesion());
        }
    }

}
