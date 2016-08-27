package com.proyecto.roger.mascotas.ResApi;

import com.proyecto.roger.mascotas.ResApi.Model.MascotaResponse;
import com.proyecto.roger.mascotas.ResApi.Model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Joan on 28/06/2016.
 */
public interface EndPointsApi {
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET("users/search")
    Call<MascotaResponse> getUsuario(
            @Query("q") String nombreUsuario,
            @Query("access_token") String token
    );

    @GET(ConstantesRestApi.ID_GET_RECENT_MEDIA + "{user-id}" + ConstantesRestApi.ID_GET_RECENT_MEDIA2 + ConstantesRestApi.ACCESS_TOKEN)
    Call<MascotaResponse> getRecentMediaPorId(
            @Path("user-id") String id
    );

    @POST("media/{media-id}/likes")
    Call<UsuarioResponse> darLike(
            @Path("media-id") String mediaId,
            @Query("access_token") String token
    );

    //https://api.instagram.com/v1/media/1278852335072807680_3446513169/likes?access_token=3446513169.e5b8785.9233e34ac33647beb951bf206d670977
    @FormUrlEncoded
    @POST("users/{user-id}/relationship?")
    Call<UsuarioResponse> Follow(
            @Path("user-id") String userId,
            @Query("access_token") String token,
            @Field("action") String action
    );

    //https://api.instagram.com/v1/users/{user-id}/relationship?access_token=ACCESS-TOKEN
}
