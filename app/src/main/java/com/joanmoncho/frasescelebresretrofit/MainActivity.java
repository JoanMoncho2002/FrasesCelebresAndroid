package com.joanmoncho.frasescelebresretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.joanmoncho.frasescelebresretrofit.fragments.FragmentAutor;
import com.joanmoncho.frasescelebresretrofit.fragments.FragmentCategoria;
import com.joanmoncho.frasescelebresretrofit.fragments.FragmentFrase;
import com.joanmoncho.frasescelebresretrofit.fragments.FragmentFrasesAutores;
import com.joanmoncho.frasescelebresretrofit.interfaces.IAPIService;
import com.joanmoncho.frasescelebresretrofit.interfaces.IAutorFrase;
import com.joanmoncho.frasescelebresretrofit.rest.RestClient;
import com.joanmoncho.frasescelebresretrofit.models.Autor;
import com.joanmoncho.frasescelebresretrofit.models.Categoria;
import com.joanmoncho.frasescelebresretrofit.models.Frase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FragmentFrase.IOnAttachListener, FragmentFrasesAutores.IOnAttachListener,/* FragmentCategoria.IOnAttachListener,*/ IAutorFrase {
    private IAPIService apiService;
    private SharedPreferences prefs;
    private List<Frase> frases;
    private List<Autor> autores;
    private List<Categoria> categorias;
    private Autor autorSeleccionado;

    private Autor AutorFraseSeleccionada;
    public static final String nombres="names";
    //private TextView tvBienvenido;
    private Button btFraseDia, btAutor, btCategoria, btAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        apiService = RestClient.getInstance();
        frases = new ArrayList<>();
        autores = new ArrayList<>();
        categorias = new ArrayList<>();

        getFrases();
        getCategorias();
        getAutores();

        btFraseDia = findViewById(R.id.btFraseDia);
        btAutor = findViewById(R.id.btAutor);
        btCategoria = findViewById(R.id.btCategoria);
        btAdmin = findViewById(R.id.btAdmin);

        /*tvBienvenido = (TextView)findViewById(R.id.tvBienvenido);
        String email = getIntent().getStringExtra("names");
        tvBienvenido.setText("Bienvenido " + email);
         */
        btFraseDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.fragmentContainerView, FragmentFrase.class, null)
                        .commit();
            }
        });

        btAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.fragmentContainerView, FragmentAutor.class, null)
                        .commit();
            }
        });

        btCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.fragmentContainerView, FragmentCategoria.class, null)
                        .commit();
            }
        });

        btAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void getFrases() {
        apiService.getFrases().enqueue(new retrofit2.Callback<List<Frase>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Frase>> call, Response<List<Frase>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    frases.addAll(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Frase>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getCategorias() {
        apiService.getCategorias().enqueue(new retrofit2.Callback<List<Categoria>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    categorias.addAll(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getAutores() {
        /*apiService.getAutores().enqueue(new retrofit2.Callback<List<Autor>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Autor>> call, Response<List<Autor>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    autores.addAll(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Autor>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

         */
    }

    /*public void addFrase() {
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
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, AjustesActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public List<Frase> getFrase() {
        return frases;
    }

    /*@Override
    public List<Categoria> getCategoria() {
        return categorias;
    }

    @Override
    public List<Autor> getAutor() {
        return autores;
    }

     */

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onAutorFraseSeleccionada(int id) {
        apiService.getAutores().enqueue(new Callback<List<Autor>>() {
            @Override
            public void onResponse(@NonNull Call<List<Autor>> call, @NonNull Response<List<Autor>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    autores.clear();
                    autores.addAll(response.body());

                    AutorFraseSeleccionada = autores.get(id);

                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction()
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .replace(R.id.fragmentContainerView, FragmentAutor.class, null)
                            .commit();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Autor>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "No se han podido obtener los autores", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public Autor getAutorSeleccionado() {
        return autorSeleccionado;
    }



    /*public void addFraseValues() {
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
    }   */

}