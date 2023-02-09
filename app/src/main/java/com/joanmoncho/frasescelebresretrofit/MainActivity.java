package com.joanmoncho.frasescelebresretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.joanmoncho.frasescelebresretrofit.interfaces.IAPIService;
import com.joanmoncho.frasescelebresretrofit.rest.RestClient;
import com.joanmoncho.frasescelebresretrofit.models.Autor;
import com.joanmoncho.frasescelebresretrofit.models.Categoria;
import com.joanmoncho.frasescelebresretrofit.models.Frase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.security.auth.callback.Callback;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private IAPIService apiService;

    //Nuevo
    public static final String nombres="names";
    TextView tvBienvenido;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = RestClient.getInstance();
        // Probando obtener las frases
        getFrases();

        // Nuevo
        /*tvBienvenido = (TextView)findViewById(R.id.tvBienvenido);
        String email = getIntent().getStringExtra("names");
        tvBienvenido.setText("Bienvenido " + email);
         */

    }

    public void getFrases() {
        apiService.getFrases().enqueue(new retrofit2.Callback<List<Frase>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Frase>> call, Response<List<Frase>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    for(Frase frase: response.body()) {
                        Log.i(MainActivity.class.getSimpleName(), frase.toString());
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Frase>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addFraseValues() {
        Log.i(MainActivity.class.getSimpleName(), "Añadiendo frase ...");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        apiService.addFraseValues("Frase Values", "2021-02-09", 1, 1).enqueue(new retrofit2.Callback<Boolean>() {
            @Override
            public void onResponse(retrofit2.Call<Boolean> call, retrofit2.Response<Boolean> response) {
                if(response.isSuccessful()) {
                    if(response.body()) {
                        Log.i(MainActivity.class.getSimpleName(), "Frase añadida correctamente");
                    } else {
                        Log.i(MainActivity.class.getSimpleName(), "Error al añadir la frase");

                        Log.i(MainActivity.class.getSimpleName(), response.raw().toString());
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Boolean> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addFrase() {
        Log.i(MainActivity.class.getSimpleName(), "Añadiendo frase ...");
        Autor autor = new Autor(1, "Autor 1", 10, null, "Fontanero");
        Categoria categoria = new Categoria(1, "Categoria 1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Frase frase = new Frase("Otra frase", sdf.format(new Date()), autor, categoria);
        apiService.addFrase(frase).enqueue(new retrofit2.Callback<Boolean>() {
            @Override
            public void onResponse(retrofit2.Call<Boolean> call, retrofit2.Response<Boolean> response) {
                if(response.isSuccessful()) {
                    if(response.body()) {
                        Log.i(MainActivity.class.getSimpleName(), "Frase añadida correctamente");
                    } else {
                        Log.i(MainActivity.class.getSimpleName(), "Error al añadir la frase");

                        Log.i(MainActivity.class.getSimpleName(), response.raw().toString());
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Boolean> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}