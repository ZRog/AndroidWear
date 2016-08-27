package com.proyecto.roger.mascotas.Vista;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.proyecto.roger.mascotas.Adapter.MascotaAdaptador;
import com.proyecto.roger.mascotas.Pojo.Mascota;
import com.proyecto.roger.mascotas.Presentador.IRecyclerViewFragmentPresenter;
import com.proyecto.roger.mascotas.Presentador.RecyclerViewFragmentPresenter;
import com.proyecto.roger.mascotas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

        // FRAGMENT 1 DEL VIEWPAGER. ENCARGADO DE PASARLE AL MASCOTAADAPTADOR UN ARRAY DE MASCOTAS PARA QUE LE AYUDE A MOSTRARLO EN EL RECYCLERVIEW

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private IRecyclerViewFragmentPresenter presenter;
    public static boolean carga = false;
    public static String cuenta = "barneyrog";
    public static String idcuenta = "3446513169";
    private CircularImageView circularImageView;
    public static String imagen = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);



        circularImageView = (CircularImageView)v.findViewById(R.id.imagenCircular);
// Set Border
        circularImageView.setBorderColor(getResources().getColor(R.color.colorPrimary));
        circularImageView.setBorderWidth(10);
// Add Shadow with default param
        circularImageView.addShadow();
// or with custom param
        circularImageView.setShadowRadius(15);
        circularImageView.setShadowColor(Color.BLACK);

        if(!carga) {
            presenter = new RecyclerViewFragmentPresenter(this, getContext());
        }
            else {
            presenter = new RecyclerViewFragmentPresenter(this, getContext(), cuenta);

            Picasso.with(getActivity())
                    .load(imagen)
                    .placeholder(R.drawable.barney)
                    .into(circularImageView);
        }
        return v;
    }


    @Override
    public void generarLinearLayoutVertical() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rvMascotas.setLayoutManager(gridLayoutManager);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotas.setAdapter(adaptador);
    }

}
