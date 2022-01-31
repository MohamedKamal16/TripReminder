package com.example.tripplanner.Home.Activity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Final;
import com.example.tripplanner.TripData.Trip;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

 public class WorkManagerRepository {
     public static final String tripId = "TripId";

//TODO COLLECT ALL TRIPS
     public static void setWorkers(List<Trip> tripList) {
         WorkManager.getInstance().cancelAllWork();

         for (Trip trip : tripList) {
             Data.Builder builder = new Data.Builder();
             builder.putInt(tripId, trip.getId());
          //   System.out.println("new Date().getTime() - trip.getStartDate().getTime()");
             WorkRequest oneTimeWorkRequest = new OneTimeWorkRequest
                     .Builder(AlarmWorker.class)
                     .setInitialDelay(trip.getCalendar() - new Date().getTime(), TimeUnit.MILLISECONDS
                     )
                     .setInputData(builder.build())
                     .build();

             WorkManager.getInstance().enqueue(oneTimeWorkRequest);

         }
     }

 public static class AlarmWorker extends Worker {
         WorkerParameters workerParams;
         Context context;
         public AlarmWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
                 super(context, workerParams);
                 this.context = context;
                 this.workerParams = workerParams;
             }

             @NonNull
             @Override
             public Result doWork() {
                 Intent intent = new Intent(context, MainNotification.class);
                 String trip = getInputData().getString(tripId);
                 System.out.println("trip");
                 System.out.println(trip);
                 intent.putExtra(tripId, trip) .addFlags(FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(intent);
                 return Result.success();

             }

         /*   public static void oneOfRequest() {
                //  long delayedTime=

                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(AlarmWorker.class)
                        .setInitialDelay(5, TimeUnit.SECONDS)
                        .setConstraints(setConstrain())
                        .build();
                WorkManager.getInstance().enqueue(oneTimeWorkRequest);
            }

            // When Notification Show
            public static Constraints setConstrain() {
                Constraints constraints = new Constraints.Builder().build();
                return constraints;
            }

            public void showNotification() {
                Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                        0, intent, PendingIntent.FLAG_ONE_SHOT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1")
                        .setSmallIcon(android.R.drawable.stat_notify_sync)
                        .setContentTitle("Notification")
                        .setContentText("your trip  is pending")
                        .setOngoing(true).setAutoCancel(true).setContentIntent(pendingIntent);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                notificationManagerCompat.notify(1, builder.build());

            }*/

         }
     }

/*
rivate void setAlarm(long diff){
        workManager=WorkManager.getInstance(getContext());
        OneTimeWorkRequest.Builder builder = new OneTimeWorkRequest.Builder(AlarmWorker.class);
        builder.setInitialDelay(diff, TimeUnit.SECONDS);
        builder.addTag(work_Tag);
        workRequest= builder
        .build();
        workManager.enqueue(workRequest);



        }*/
