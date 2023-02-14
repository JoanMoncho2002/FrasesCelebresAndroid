package com.joanmoncho.frasescelebresretrofit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.models.Autor;

public class FragmentAutor extends Fragment {
    public interface IOnAttachListener {
       Autor getAutor();
    }

    private TextView tvNombre;
    private TextView tvProfesion;
    private TextView tvNacimiento;
    private Autor autor;

    public FragmentAutor() {
        super(R.layout.fragment_autor);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvNombre = view.findViewById(R.id.tvNAutor);
        tvProfesion = view.findViewById(R.id.tvProfesion);
        tvNacimiento = view.findViewById(R.id.tvNacimiento);
        if(autor != null)
            showDetail(autor);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        autor = attachListener.getAutor();
    }

    public void showDetail(Autor autor) {
        this.autor = autor;
        tvNombre.setText(autor.getNombre());
        tvProfesion.setText(autor.getProfesion());
        tvNacimiento.setText(autor.getNacimiento());
    }
}
