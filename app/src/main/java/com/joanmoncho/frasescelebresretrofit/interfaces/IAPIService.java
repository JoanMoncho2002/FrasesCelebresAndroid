package com.joanmoncho.frasescelebresretrofit.interfaces;

import com.joanmoncho.frasescelebresretrofit.models.Frase;
import com.joanmoncho.frasescelebresretrofit.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IAPIService {
    @GET("frase/all")
    Call<List<Frase>> getFrases();

    @GET("usuario/all")
    Call<List<Usuario>> getUsuarios();

    @POST("frase/add")
    Call<Boolean> addFrase(@Body Frase frase);
    @POST("usuario/login")
    Call<Boolean> loginUser(@Body Usuario usuario);

    @POST("frase/addValues")
    @FormUrlEncoded
    Call<Boolean> addFraseValues(@Field("texto") String texto,
                                 @Field("fechaProgramada") String fechaProgramada,
                                 @Field("idAutor") int idAutor,
                                 @Field("idCategoria")int idCategoria);

}
