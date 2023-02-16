package com.joanmoncho.frasescelebresretrofit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmoncho.frasescelebresretrofit.MainActivity;
import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.adapters.AdaptadorAutor;
import com.joanmoncho.frasescelebresretrofit.adapters.AdaptadorFrases;
import com.joanmoncho.frasescelebresretrofit.interfaces.IAPIService;
import com.joanmoncho.frasescelebresretrofit.interfaces.IAutorFrase;
import com.joanmoncho.frasescelebresretrofit.interfaces.IOnClickListener;
import com.joanmoncho.frasescelebresretrofit.models.Autor;
import com.joanmoncho.frasescelebresretrofit.models.Frase;
import com.joanmoncho.frasescelebresretrofit.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class FragmentAutor extends Fragment {
    private IAutorFrase listener;
    /*public interface IOnAttachListener {
        List<Frase> getFrase();
        Autor autor();
    }
     */

    private List<Autor> autores;
    private List<Frase> frases;

    public FragmentAutor() {
        super(R.layout.fragment_listado_autor);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IAPIService apiService = RestClient.getInstance();
        RecyclerView rvAutor = view.findViewById(R.id.rvAutor);
        List<Autor> autores = new ArrayList<>();

        apiService.getAutores().enqueue(new retrofit2.Callback<List<Autor>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Autor>> call, Response<List<Autor>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    autores.addAll(response.body());

                    AdaptadorAutor adaptadorAutor = new AdaptadorAutor(autores, listener);
                    rvAutor.setHasFixedSize(true);
                    rvAutor.setAdapter(adaptadorAutor);
                    rvAutor.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Autor>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "No se pueden obtener los autores", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (IAutorFrase) context;
    }
}
