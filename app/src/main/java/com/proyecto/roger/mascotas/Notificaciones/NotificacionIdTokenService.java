package com.proyecto.roger.mascotas.Notificaciones;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Roger on 20/07/2016.
 */
public class NotificacionIdTokenService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
    }

    private static final String TAG = "FIREBASE_TOKEN";

}
