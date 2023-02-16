package com.joanmoncho.frasescelebresretrofit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.fragments.FragmentFrase;
import com.joanmoncho.frasescelebresretrofit.models.Frase;

import java.util.List;

public class AdaptadorFrases  extends RecyclerView.Adapter<AdaptadorFrases.FraseViewHolder> {
    private List<Frase> frases;

    public AdaptadorFrases(List<Frase> frases) {
        this.frases=frases;
    }

    @NonNull
    @Override
    public FraseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frase, parent, false);
        return new FraseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FraseViewHolder fraseViewHolder, int position) {
        Frase frase = frases.get(position);
        fraseViewHolder.bindFrase(frase);
    }

    @Override
    public int getItemCount() {
        return frases.size();
    }

    public static class FraseViewHolder extends RecyclerView.ViewHolder{
        private final TextView frase;

        public FraseViewHolder(@NonNull View itemView) {
            super(itemView);
            frase = itemView.findViewById(R.id.tvFrase);
        }

        public void bindFrase(Frase frases ) {
            frase.setText(frases.getTexto());
        }
    }
}
