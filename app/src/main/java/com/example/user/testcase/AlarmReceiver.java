package com.example.user.testcase;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by User on 25/09/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    public AlarmReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TEST", "ALARM TRIGGERED");

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        Uri notifSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        String depart = "";
        String destination = "";
        String date = "";
        if(intent != null) {
            if(intent.getStringExtra("depart") != null) {
                depart = intent.getStringExtra("depart");
            }
            if(intent.getStringExtra("destination") != null) {
                destination = intent.getStringExtra("destination");
            }
            if(intent.getIntExtra("day", -1) != -1) {
                date = date + String.valueOf(intent.getIntExtra("day", 0));
            } else {
                date = date + "0";
            }
            if(intent.getIntExtra("month", -1) != -1) {
                date = date + "-" + String.valueOf(intent.getIntExtra("month", 0));
            } else {
                date = date + "-0";
            }
            if(intent.getIntExtra("year", -1) != -1) {
                date = date + "-" + String.valueOf(intent.getIntExtra("year", 0)) ;
            } else {
                date = date + "-0";
            }
        }
        String message = "10 seconds after booking: " + depart + "-" + destination;

        Intent activityIntent = new Intent(context, MainActivity.class);
        activityIntent.setAction("notify");
        PendingIntent pendingIntent = PendingIntent
                .getActivity(context, 2, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setContentTitle("Booking App")
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icon)
                .setContentIntent(pendingIntent);
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(message)
                .setBigContentTitle("Report")
                .setSummaryText("Reserved for " + date);
        builder.setStyle(bigText);
        builder.setSound(notifSound);

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
