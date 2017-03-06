package com.direct.success.markdirect.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import io.realm.RealmObject;

public class Notifications {
    private boolean activo;
    public Notifications() {
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public static void postNotification(Context context, Class<? extends Activity> activity, String titulo, String texto, int icono, int color, int identificator, int major, int minor) {
        Intent i = new Intent(context, activity);
        i.putExtra("MAJOR", major);
        i.putExtra("MINOR", minor);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 5, i, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setSmallIcon(icono)
                .setAutoCancel(true)
                .setColor(color)
                .setContentIntent(pendingIntent)
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        SharedPreferences prefs = context.getSharedPreferences("Preferences",Context.MODE_PRIVATE);
        if(prefs.getBoolean("Notification",true))
        {
            notificationManagerCompat.notify(identificator, notification);
        }else{
            //no tengo que hacer nada, no mando notificación
        }

    }
}
