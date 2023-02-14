package com.joanmoncho.frasescelebresretrofit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.models.Frase;

public class FragmentFrase extends Fragment {
    public interface IOnAttachListener {
        Frase getFrase();
    }

    private TextView tvFrase;
    private Frase frase;

    public FragmentFrase() {
        super(R.layout.fragment_frase);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvFrase = view.findViewById(R.id.tvNCategoria);
        if(frase != null)
            showDetail(frase);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        frase = attachListener.getFrase();
    }

    public void showDetail(Frase frase) {
        this.frase = frase;
        tvFrase.setText(frase.getTexto());
    }

}

