package com.proyecto.roger.mascotas.ResApi.Model;

import com.proyecto.roger.mascotas.ResApi.ConstantesRestApi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Roger on 20/07/2016.
 */
public interface EndPoints {

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenId(@Field("token") String token, @Field("idusuario") String idusuario);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_LIKE)
    Call<MascotaResponse> registrarLike(@Field("token") String token, @Field("idusuario") String usuario, @Field("likes") String likes, @Field("idfoto") String idfoto);

    @GET(ConstantesRestApi.KEY_TOQUE)
    Call<UsuarioResponse> toqueAnimal(@Path("id") String id, @Path("idusuario") String idusuario, @Path("idenvio") String idenvio);

}
