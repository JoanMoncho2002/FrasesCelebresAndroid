package com.joanmoncho.frasescelebresretrofit.rest;

import com.joanmoncho.frasescelebresretrofit.interfaces.IAPIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static IAPIService instance;
    private static final int PORT = 8090;
    private static final String IPCASA = "http://192.168.0.100:";
    private static final String IPINSTI = "http://192.168.20.63:";
    private static final String IPGERMAN = "http://???:";
    private static final String BASE_URL = IPINSTI + PORT;

    /* Lo hacemos privado para evitar que se puedan crear instancias de esta forma */
    private RestClient() {

    }

    public static synchronized IAPIService getInstance() {
        if(instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(IAPIService.class);
        }
        return instance;
    }
}
