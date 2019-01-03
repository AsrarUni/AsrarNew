package com.example.mersad.asrar.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.mersad.asrar.Activities.MainActivity;
import com.example.mersad.asrar.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaceMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent intent = new Intent( this , MainActivity.class ) ;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , intent , PendingIntent.FLAG_ONE_SHOT) ;
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this) ;
        notificationBuilder.setContentTitle( "FCM NOTIFICATION" ) ;
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody()) ;
        notificationBuilder.setAutoCancel(true) ;
        notificationBuilder.setSmallIcon(R.drawable.logo_asrar) ;
        notificationBuilder.setContentIntent(pendingIntent) ;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE) ;
        notificationManager.notify( 0 , notificationBuilder.build() ) ;

    }



    public static StringBuilder _finaltext = new StringBuilder();
    int UserID;


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        // Get updated InstanceID token.
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

        //TODO: Send Token to Server
    }

//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//
//        Log.d("Message", "Message received ["+ remoteMessage +"]");
//        try{
//            DBUser db = new DBUser(this);
//            db.Open();
//            UserID = db.GetUserID();
//            db.Close();
//        }catch (Exception ex){
//            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
//        }
//
//        int messageUserID = Integer.parseInt(remoteMessage.getData().get("tag"));
//        if(UserID != messageUserID) return;
//
//        //Create Notification
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        String fromuser = remoteMessage.getData().get("fromuser");
//        String furl = "http://www.kasbokarideal.ir/login/ShowConversation.php?ID=" + fromuser;
//        //String furl = "http://www.kasbokarideal.ir/login/SmsShow.php";
//        intent.putExtra("URL", furl);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1,
//                intent, PendingIntent.FLAG_ONE_SHOT);
//
//        _finaltext.append(remoteMessage.getData().get("title"));
//        _finaltext.append(" : ");
//        String strtr = (remoteMessage.getData().get("text").length() <= 20
//                ? remoteMessage.getData().get("text")
//                : remoteMessage.getData().get("text").substring(0, 20) + " ...");
//        _finaltext.append(strtr);
//        _finaltext.append("\n");
//
//        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new
//                NotificationCompat.Builder(this)
//                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                .setSmallIcon(R.drawable.logo_asrar)
//                //.setContentTitle("Message")
//                //.setStyle(new NotificationCompat.BigTextStyle().bigText(_finaltext.toString()))
//                .setContentText(_finaltext.toString())
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent);
//
//        NotificationCompat.InboxStyle style =
//                new NotificationCompat.InboxStyle(notificationBuilder);
//        String[] lines = _finaltext.toString().split("\\r?\\n");
//        int linecount = 0;
//        for (String ss: lines) {
//            style.addLine(ss);
//            linecount++;
//        }
//        style.setBigContentTitle(linecount + " پیام جدید");
//
//        NotificationManager notificationManager = (NotificationManager)
//                getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(1, notificationBuilder.build());
//    }


}
