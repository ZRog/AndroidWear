package com.proyecto.roger.mascotas.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.proyecto.roger.mascotas.Pojo.Mascota;
import com.proyecto.roger.mascotas.R;
import com.proyecto.roger.mascotas.ResApi.Adapter.RestApiAdapter;
import com.proyecto.roger.mascotas.ResApi.ConstantesRestApi;
import com.proyecto.roger.mascotas.ResApi.EndPointsApi;
import com.proyecto.roger.mascotas.ResApi.Model.EndPoints;
import com.proyecto.roger.mascotas.ResApi.Model.MascotaResponse;
import com.proyecto.roger.mascotas.ResApi.Model.UsuarioResponse;
import com.proyecto.roger.mascotas.Vista.RecyclerViewFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//      RECIBE UN ARRAY DE MASCOTAS Y LO TRATA PARA QUE LO MUESTRE EL RECYCLERVIEW

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.activity = activity;
        this.mascotas = mascotas;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.barney)
                .into(holder.imgFoto);
            holder.tvNombreCV.setText(mascota.getNombreCompleto());
            holder.tvTelefonoCV.setText(String.valueOf(mascota.getLikes()));
            holder.imgFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RestApiAdapter restApiAdapter = new RestApiAdapter();
                    EndPointsApi endPointsApi = restApiAdapter.conectarConInstagram();
                    Call<UsuarioResponse> usuarioResponseCall =  endPointsApi.darLike(mascota.getIdMedia(), ConstantesRestApi.ACCESS_TOKEN);
                    usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                        @Override
                        public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {

                        }

                        @Override
                        public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                        }
                    });

                    EndPoints endPoints = restApiAdapter.establecerConexionRestApi();
                    String token = FirebaseInstanceId.getInstance().getToken();
                    mascota.setLikes(mascota.getLikes()+1);
                    String temporal = String.valueOf(mascota.getLikes());
                    Call<MascotaResponse> mascotaResponseCall = endPoints.registrarLike(token,mascota.getId(),temporal,mascota.getIdMedia());
                    mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
                        @Override
                        public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {

                        }

                        @Override
                        public void onFailure(Call<MascotaResponse> call, Throwable t) {

                        }
                    });

                    UsuarioResponse usuarioResponse = new UsuarioResponse("-KPWxmNe29OWFKuh37aF","123",mascota.getId());
                    RestApiAdapter restApiAdapter1 = new RestApiAdapter();
                    EndPoints endPoints1 = restApiAdapter1.establecerConexionRestApi();
                    Call<UsuarioResponse> usuarioResponseCall1 = endPoints1.toqueAnimal(usuarioResponse.getId(),usuarioResponse.getAnimal(), RecyclerViewFragment.cuenta);
                    usuarioResponseCall1.enqueue(new Callback<UsuarioResponse>() {
                        @Override
                        public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                            UsuarioResponse usuarioResponse2 = response.body();
                            Log.d("CONEXION:", "SI");
                        }

                        @Override
                        public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                            Log.d("CONEXION:" , "NO");
                        }
                    });

                }
            });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvTelefonoCV;
        private CircularImageView imgCircular;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV = (TextView) itemView.findViewById(R.id.tvTelefonoCV);
            imgCircular = (CircularImageView) itemView.findViewById(R.id.imagenCircular);
        }
    }
}
