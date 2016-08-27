package com.proyecto.roger.mascotas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.proyecto.roger.mascotas.Pojo.Mascota;
import com.proyecto.roger.mascotas.ResApi.Adapter.RestApiAdapter;
import com.proyecto.roger.mascotas.ResApi.ConstantesRestApi;
import com.proyecto.roger.mascotas.ResApi.EndPointsApi;
import com.proyecto.roger.mascotas.ResApi.Model.MascotaResponse;
import com.proyecto.roger.mascotas.ResApi.Model.UsuarioResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DarFollow extends AppCompatActivity {

    public static String cuenta = "";
    public static String idCuenta = "";
    private ArrayList<Mascota> mascotas;
    private ImageView imagen,imagen2,imagen3,imagen4,imagen5,imagen6;
    private TextView cuentaFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_follow);

        cuentaFollow = (TextView) findViewById(R.id.cuentaFollow);
        cuentaFollow.setText(cuenta);
        cuentaFollow.setHint(cuenta);

        imagen = (ImageView) findViewById(R.id.fotoReciente);
        imagen2 = (ImageView) findViewById(R.id.fotoReciente2);
        imagen3 = (ImageView) findViewById(R.id.fotoReciente3);
        imagen4 = (ImageView) findViewById(R.id.fotoReciente4);
        imagen5 = (ImageView) findViewById(R.id.fotoReciente5);
        imagen6 = (ImageView) findViewById(R.id.fotoReciente6);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonIdUsuario = restApiAdapter.construyeGsonDeserializadorIdUsuario();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonIdUsuario);
        Call<MascotaResponse> mascotaResponseCall = endPointsApi.getUsuario(cuenta,ConstantesRestApi.ACCESS_TOKEN);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                idCuenta = mascotas.get(0).getId();




                RestApiAdapter restApiAdapter1 = new RestApiAdapter();
                Gson gsonmediaRecent1 = restApiAdapter1.construyeGsonDeserializadorMediaRecent();
                EndPointsApi endPointsApi1 = restApiAdapter1.establecerConexionRestApiInstagram(gsonmediaRecent1);

                Call<MascotaResponse> mascotaResponseCall1 = endPointsApi1.getRecentMediaPorId(idCuenta);
                mascotaResponseCall1.enqueue(new Callback<MascotaResponse>() {
                    @Override
                    public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                        MascotaResponse mascotaResponse1 = response.body();
                        ArrayList<Mascota> mascotas2 = new ArrayList<Mascota>();
                        mascotas2 = mascotaResponse1.getMascotas();

                        final Mascota mascota = mascotas2.get(0);
                        Picasso.with(DarFollow.this)
                                .load(mascota.getUrlFoto())
                                .placeholder(R.drawable.barney)
                                .into(imagen);

                        final Mascota mascota1 = mascotas2.get(1);
                        Picasso.with(DarFollow.this)
                                .load(mascota1.getUrlFoto())
                                .placeholder(R.drawable.barney)
                                .into(imagen2);

                        final Mascota mascota2 = mascotas2.get(2);
                        Picasso.with(DarFollow.this)
                                .load(mascota2.getUrlFoto())
                                .placeholder(R.drawable.barney)
                                .into(imagen3);

                        final Mascota mascota3 = mascotas2.get(3);
                        Picasso.with(DarFollow.this)
                                .load(mascota3.getUrlFoto())
                                .placeholder(R.drawable.barney)
                                .into(imagen4);

                        final Mascota mascota4 = mascotas2.get(4);
                        Picasso.with(DarFollow.this)
                                .load(mascota4.getUrlFoto())
                                .placeholder(R.drawable.barney)
                                .into(imagen5);

                        final Mascota mascota5 = mascotas2.get(5);
                        Picasso.with(DarFollow.this)
                                .load(mascota5.getUrlFoto())
                                .placeholder(R.drawable.barney)
                                .into(imagen6);



                    }

                    @Override
                    public void onFailure(Call<MascotaResponse> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

            }
        });

    }

    public void darFollow(View v){
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

            }
        });
    }


    public void darUnfollow(View v){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPoints = restApiAdapter.conectarConInstagram();

        Call<UsuarioResponse> usuarioResponseCall = endPoints.Follow("2464381224", ConstantesRestApi.ACCESS_TOKEN,"unfollow");
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                Log.d("CONEXION:", "YEA");
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
}
