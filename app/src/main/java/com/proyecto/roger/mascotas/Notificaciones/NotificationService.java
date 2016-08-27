package com.proyecto.roger.mascotas.Notificaciones;


import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.Gravity;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.proyecto.roger.mascotas.DarFollow;
import com.proyecto.roger.mascotas.MainActivity;
import com.proyecto.roger.mascotas.R;

import java.util.ArrayList;

/**
 * Created by Roger on 19/07/2016.
 */
public class NotificationService extends FirebaseMessagingService {

    public static final String TAG = "FIREBASE";
    public static final int NOTIFICATION_ID = 001;
    public static ArrayList<NotificationCompat.Action> acciones;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        acciones  = new ArrayList<>();

        Log.d(TAG,"From: " + remoteMessage.getFrom());
        Log.d(TAG,"Notification Message Body: " + remoteMessage.getNotification().getBody());

        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);

        Intent i2 = new Intent(this, DarFollow.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this,0,i2,PendingIntent.FLAG_ONE_SHOT);

        /*Intent i3 = new Intent(this, DarFollow.class);
        PendingIntent pendingIntent3 = PendingIntent.getActivity(this,0,i3,PendingIntent.FLAG_ONE_SHOT);*/

        Intent i3 = new Intent();
        i3.setAction("DAR_FOLLOW");
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this,0,i3,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_full_perfil, "Tu Perfil", pendingIntent).build();
        NotificationCompat.Action action2 = new NotificationCompat.Action.Builder(R.mipmap.ic_full_perfil, "Ver usuario", pendingIntent2).build();
        NotificationCompat.Action action3 = new NotificationCompat.Action.Builder(R.mipmap.ic_full_perfil, "Follow", pendingIntent1).build();

        acciones.add(action);
        acciones.add(action2);
        acciones.add(action3);

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.fondowear))
                .setGravity(Gravity.CENTER_VERTICAL);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_casa)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addActions(acciones));

        //.addAction(R.mipmap.ic_full_perfil, "Tu Perfil",pendingIntent);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID,notificacion.build());

        String emisario = remoteMessage.getNotification().getBody();
        String[] emisarioSplit = emisario.split("te");
        emisarioSplit[0] = emisarioSplit[0].trim();
        DarFollow.cuenta = emisarioSplit[0];
    }
}
