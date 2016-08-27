package com.proyecto.roger.mascotas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.proyecto.roger.mascotas.ResApi.Adapter.RestApiAdapter;
import com.proyecto.roger.mascotas.ResApi.ConstantesRestApi;
import com.proyecto.roger.mascotas.ResApi.EndPointsApi;
import com.proyecto.roger.mascotas.ResApi.Model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roger on 26/08/2016.
 */
public class Follow extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String ACTION_KEY = "DAR_FOLLOW";
        String accion = intent.getAction();
      //  if(ACTION_KEY.equals(accion)){
            darToque();
       // }
    }

    private void darToque(){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoints = restApiAdapter.conectarConInstagram();

        Call<UsuarioResponse> usuarioResponseCall = endPoints.Follow("2464381224", ConstantesRestApi.ACCESS_TOKEN,"follow");
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                Log.d("CONEXION:", "YES");
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.d("CONEXION:", "NAIN");
            }
        });
    }
}
