package com.joanmoncho.frasescelebresretrofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.interfaces.IOnClickListener;
import com.joanmoncho.frasescelebresretrofit.models.Autor;
import com.joanmoncho.frasescelebresretrofit.models.Categoria;

import java.util.List;

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.CategoriaViewHolder>{
    private final List<Categoria> categoriaLista;
    private IOnClickListener listener;

    public AdaptadorCategoria(List<Categoria> categoriaLista, IOnClickListener listener){
        this.categoriaLista = categoriaLista;
        this.listener=listener;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        return new CategoriaViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = categoriaLista.get(position);
        holder.bindCategoria(categoria);
    }

    @Override
    public int getItemCount() {
        return categoriaLista.size();
    }

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvNCategoria;
        private final  IOnClickListener listener;

        public CategoriaViewHolder(@NonNull View itemView,  IOnClickListener listener) {
            super(itemView);
            this.listener = listener;
            tvNCategoria = itemView.findViewById(R.id.tvNCategoria);
        }

        public void bindCategoria(Categoria categoria){
            tvNCategoria.setText(categoria.getNombre());
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onClick(getAdapterPosition());
            }
        }

    }

}

