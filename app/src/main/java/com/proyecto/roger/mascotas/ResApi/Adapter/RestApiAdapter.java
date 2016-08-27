package com.proyecto.roger.mascotas.ResApi.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyecto.roger.mascotas.Deserializador.IdUsuarioDeserializador;
import com.proyecto.roger.mascotas.Deserializador.MascotaDeserializador;
import com.proyecto.roger.mascotas.ResApi.ConstantesRestApi;
import com.proyecto.roger.mascotas.ResApi.EndPointsApi;
import com.proyecto.roger.mascotas.ResApi.Model.EndPoints;
import com.proyecto.roger.mascotas.ResApi.Model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Joan on 28/06/2016.
 */
public class RestApiAdapter {
    public EndPointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsApi.class);
    }

    public EndPointsApi conectarConInstagram(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndPointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorIdUsuario(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new IdUsuarioDeserializador());
        return gsonBuilder.create();
    }

    public EndPoints establecerConexionRestApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_HEROKU)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndPoints.class);
    }

}
