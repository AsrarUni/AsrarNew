package com.example.mersad.asrar.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.example.mersad.asrar.R;

/**
 * Created by hamed-pc on 1/20/2019.
 */
public class MyNotificationPublisher extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
    Intent repeating_intent = new Intent(context ,Weekly_Class_Tab_Activity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent =PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
            .setContentIntent(pendingIntent)
            .setContentTitle(context.getString(R.string.insert_user_pass))
            .setContentText(context.getString(R.string.pull_down))
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.login_backgraund)
            .setLargeIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_student)).getBitmap())
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        notificationManager.notify(100,builder.build());

}
}
