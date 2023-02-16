package com.joanmoncho.frasescelebresretrofit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.models.Categoria;

public class FragmentMain extends Fragment {

    private Button btFraseDia;
    private Button btAutor;
    private Button btCategoria;

    public FragmentMain() {
        super(R.layout.fragment_principal);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btFraseDia = view.findViewById(R.id.btFraseDia);
        btAutor = view.findViewById(R.id.btAutor);
        btCategoria = view.findViewById(R.id.btCategoria);
        //if(categoria != null)
           // showDetail(categoria);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //FragmentCategoria.IOnAttachListener attachListener = (FragmentCategoria.IOnAttachListener) context;
        //categoria = attachListener.getCategoria();
    }

    public void showDetail(Categoria categoria) {
        //this.categoria = categoria;
        //tvNCategoria.setText(categoria.getNombre());
    }

}
