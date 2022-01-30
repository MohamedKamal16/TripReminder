package com.example.tripplanner.Home.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Room;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Final;
import com.example.tripplanner.TripData.TripDatabase;
import com.google.firebase.auth.FirebaseAuth;

public class MainNotification extends AppCompatActivity {

    private static final String CHANNEL_ID = "1";
    NotificationManager notificationManager;
    Notification.Builder builder;
    NotificationManager manager;
    MediaPlayer mp;
    SharedPreferences.Editor sharedEditor;
    SharedPreferences sharedPreferences;
    String tripName;
    int tripId;
    String tripUserId;
    double tripLong;
    double tripLat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notification);


        SharedPreferences sharedPreferences = getSharedPreferences("tripInfo", MODE_PRIVATE);

        sharedEditor= sharedPreferences.edit();

        if(getIntent().hasExtra("TRIP_ID")){
            sharedEditor.putString("TRIP_NAME",getIntent().getExtras().getString("TRIP_NAME"));
            sharedEditor.putInt("TRIP_ID",getIntent().getExtras().getInt("TRIP_ID"));
            sharedEditor.putString("TRIP_USER_ID",getIntent().getExtras().getString("TRIP_USER_ID"));
            sharedEditor.putString("TRIP_LONGITUDE",getIntent().getExtras().getDouble("TRIP_LONGITUDE")+"");
            sharedEditor.putString("TRIP_LATITUDE",getIntent().getExtras().getDouble("TRIP_LATITUDE")+"");
            sharedEditor.commit();
        }

        tripName = sharedPreferences.getString("TRIP_NAME","none");
        tripId = sharedPreferences.getInt("TRIP_ID",-1);
        tripUserId = sharedPreferences.getString("TRIP_USER_ID","none");
        tripLat = Double.parseDouble(sharedPreferences.getString("TRIP_LATITUDE","0"));
        tripLong = Double.parseDouble(sharedPreferences.getString("TRIP_LONGITUDE","0"));

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       /* mp = MediaPlayer.create(MainNotification.this, R.raw.sound);
        mp.setLooping(true);*/
        customDialog(this);
    }

    public void addNotuification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);


            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            builder=new Notification.Builder(this, CHANNEL_ID);
        }
        else{
            builder=new Notification.Builder(this);
        }
        Intent intent = new Intent(this, MainNotification.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat  notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(1, builder.build());


    }
    public void initMap(){


    }
    public void finishTrip(){
        new Thread(new Runnable() {
            @Override
            public void run() {
               Home_Activity.database.tripDAO().updateTripStatus(Home_Activity.fireBaseUserId,tripId,"finished");

            }
        }).start();
    }

    public void cancelTrip(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Home_Activity.database.tripDAO().updateTripStatus(Home_Activity.fireBaseUserId,tripId,"Cancel");
            }
        }).start();
    }

    /*
    public void initBubble(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            askPermission();
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Intent intent = new Intent(this, FloatingViewService.class);
            intent.putExtra("TRIP_ID",tripId);
            startService(intent);
            finish();
        } else if (Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(this, FloatingViewService.class);
            intent.putExtra("tripid",tripid);
            startService(intent);
            finish();
        } else {
            askPermission();
            Toast.makeText(this, "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
        }
    }*/

    private void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));

        startActivityForResult(intent, 2084);
    }

    public void customDialog(Context context){
        mp.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_alarm, findViewById(R.id.dialogLayoutContainer));
        builder.setView(view);
        ((TextView)view.findViewById(R.id.textTitle)).setText("TRIPplanner");
        ((TextView)view.findViewById(R.id.textMessage)).setText("Your Trip "+tripName+" is now");
        ((Button)view.findViewById(R.id.btnSnooze)).setText("snooze");
        ((Button)view.findViewById(R.id.btnCancel)).setText("cancel");
        ((Button)view.findViewById(R.id.btnStart)).setText("start");

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnSnooze).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendNotification(context);

                addNotuification();
                finish();
                mp.stop();
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancelTrip();
                finish();
                mp.stop();
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initMap();
                //initBubble();
                finishTrip();
                finish();
                mp.stop();
                alertDialog.dismiss();
            }
        });

        if(alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}