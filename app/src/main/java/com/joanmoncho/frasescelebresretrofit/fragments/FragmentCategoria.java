package com.joanmoncho.frasescelebresretrofit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.models.Categoria;

public class FragmentCategoria extends Fragment {
    public interface IOnAttachListener {
        Categoria getCategoria();
    }

    private TextView tvNCategoria;
    private Categoria categoria;

    public FragmentCategoria() {
        super(R.layout.fragment_categoria);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvNCategoria = view.findViewById(R.id.tvNCategoria);
        if(categoria != null)
            showDetail(categoria);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        categoria = attachListener.getCategoria();
    }

    public void showDetail(Categoria categoria) {
        this.categoria = categoria;
        tvNCategoria.setText(categoria.getNombre());
    }

}
