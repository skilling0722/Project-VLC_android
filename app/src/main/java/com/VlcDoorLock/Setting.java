package com.VlcDoorLock;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class Setting extends AppCompatActivity {
    static Boolean noti_onoff = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Switch sw = findViewById(R.id.notification_set_btn);
    }
    /*고정알림 버튼클릭 시 액션*/
    public void notification_act(View v){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle("VLC Door Lock")
        .setContentText("Open").setWhen(0).setOngoing(true);


        Intent resultIntent = new Intent(this, Open_door.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Open_door.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        /*fixed notification setting*/
        if(noti_onoff == false) {
            mNotificationManager.notify(0, mBuilder.build());
            Setting.noti_onoff = true;
        }
        else{
            mNotificationManager.cancel(0);
            Setting.noti_onoff = false;
        }
    }

}

