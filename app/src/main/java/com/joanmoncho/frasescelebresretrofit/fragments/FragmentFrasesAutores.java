package com.joanmoncho.frasescelebresretrofit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joanmoncho.frasescelebresretrofit.R;
import com.joanmoncho.frasescelebresretrofit.adapters.AdaptadorFrasesAutor;
import com.joanmoncho.frasescelebresretrofit.interfaces.IAPIService;
import com.joanmoncho.frasescelebresretrofit.models.Autor;
import com.joanmoncho.frasescelebresretrofit.models.Frase;
import com.joanmoncho.frasescelebresretrofit.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFrasesAutores extends Fragment {
    public interface IOnAttachListener {
        Autor getAutorSeleccionado();
    }

    private Autor autor;

    public FragmentFrasesAutores() {
        super(R.layout.fragment_listado_autor);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvLista = view.findViewById(R.id.rvAutor);
        IAPIService apiService = RestClient.getInstance();
        List<Frase> frases = new ArrayList<>();
        List<Frase> frasesAutor = new ArrayList<>();

        apiService.getFrases().enqueue(new Callback<List<Frase>>() {
            @Override
            public void onResponse(@NonNull Call<List<Frase>> call, @NonNull Response<List<Frase>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    frases.addAll(response.body());

                    for (Frase frase : frases) {
                        if (frase.getAutorId() == autor.getId()) {
                            frasesAutor.add(frase);
                        }
                    }

                    AdaptadorFrasesAutor adaptadorFrases = new AdaptadorFrasesAutor(frasesAutor, autor);
                    rvLista.setHasFixedSize(true);
                    rvLista.setAdapter(adaptadorFrases);
                    rvLista.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Frase>> call, @NonNull Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "No se han podido obtener las frases", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        autor = attachListener.getAutorSeleccionado();
    }
}
