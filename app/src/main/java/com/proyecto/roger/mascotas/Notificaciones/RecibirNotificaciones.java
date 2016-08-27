package com.proyecto.roger.mascotas.Notificaciones;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.proyecto.roger.mascotas.R;
import com.proyecto.roger.mascotas.ResApi.Adapter.RestApiAdapter;
import com.proyecto.roger.mascotas.ResApi.Model.EndPoints;
import com.proyecto.roger.mascotas.ResApi.Model.UsuarioResponse;
import com.proyecto.roger.mascotas.Vista.RecyclerViewFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecibirNotificaciones extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibirnotificaciones);

        toolbar = (Toolbar) findViewById(R.id.miToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void lanzarNotificacion(View v){
        String token = FirebaseInstanceId.getInstance().getToken();
        String idUsuario = RecyclerViewFragment.idcuenta;
        enviarTokenRegistro(token,idUsuario);
    }

    private void enviarTokenRegistro(String token, String idUsuario){
        Log.d("TOKEN",token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPoints endPoints = restApiAdapter.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endPoints.registrarTokenId(token,idUsuario);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse.getToken());
                Log.d("ID_INSTAGRAM", usuarioResponse.getAnimal());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
            }
        });


    }
}
