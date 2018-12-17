package com.smartsurveillance.kakashi.smartsurveillance;


import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class TimeService extends IntentService{

    private static final int uniqueID=12334;



    public Firebase myRef;


    public TimeService(){
        super("Timer Service");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
        Log.v("k","k");
        myRef = new Firebase(  "https://iotlab-c4bd1.firebaseio.com/SmartSurv/pir");

    }

    public void Notif()
    {
        Intent intent = new Intent(this, Stream.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,"1")
                .setSmallIcon(R.drawable.googleg_standard_color_18)
                .setContentTitle("Intruder")
                .setContentText("Click here to stream live and check on intruder's live activity")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setTicker("Intruder Detected")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(uniqueID, mBuilder.build());

    }

    public void Notif2()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,"1")
                .setSmallIcon(R.drawable.googleg_standard_color_18)
                .setContentTitle("Intruder")
                .setContentText("Capturing Footage")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setTicker("Intruder Detected")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(uniqueID, mBuilder.build());

    }

    @Override
    protected void onHandleIntent(Intent intent){

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String val = dataSnapshot.getValue(String.class);
                        if (val.equals("2")) {
                            Notif();
                        }
                        else if(val.equals("1")) {
                            Notif2();
                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

    }

}
