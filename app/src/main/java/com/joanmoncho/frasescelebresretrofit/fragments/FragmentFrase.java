package com.joanmoncho.frasescelebresretrofit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.adapters.AdaptadorFrases;
import com.joanmoncho.frasescelebresretrofit.models.Frase;

import java.util.List;

public class FragmentFrase extends Fragment {
    public interface IOnAttachListener {
        List<Frase> getFrase();
    }

    private List<Frase> frase;

    public FragmentFrase() {
        super(R.layout.fragment_frase);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdaptadorFrases adaptadorFrases = new AdaptadorFrases(frase);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        frase = attachListener.getFrase();
    }
}

