package com.example.tripplanner.Home.Activity;



import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import android.net.Uri;
import android.os.Bundle;

import com.example.tripplanner.Adapter.ViewerPageAdapter;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.TripDatabase;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity {
    /*Add[ViewPager2-TabLayout]
     * to make home shape*/
    public ViewPager2 viewPager;
    public static TripDatabase database;
    public static String fireBaseUserId;
    public static String fireBaseUserName;
    public static String fireBaseEmail;
    public static Uri fireBaseUserPhotoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireBaseUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        fireBaseUserName=FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        fireBaseEmail=FirebaseAuth.getInstance().getCurrentUser().getEmail();
        fireBaseUserPhotoUri=FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
        //initalize DB
        database = Room.databaseBuilder(this, TripDatabase.class, "tripDB").build();
        setContentView(R.layout.activity_home);
        initComponent();

    }





/*
    public void initAlarm(Trip trip) {
        Intent notifyIntent = new Intent(this, //TODO AlarmReceiver.class
                 );
        notifyIntent.putExtra(Final.TRIP_NAME, trip.getTripName());
        notifyIntent.putExtra(Final.TRIP_ID, trip.getId());
        notifyIntent.putExtra(Final.TRIP_USER_ID, trip.getUserID());
        notifyIntent.putExtra(Final.TRIP_LATITUDE, trip.getEndPointLatitude());
        notifyIntent.putExtra(Final.TRIP_LONGITUDE, trip.getEndPointLongitude());

        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, trip.getId(), notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, trip.getCalendar(), notifyPendingIntent);
    }
*/

/*
    private class registerAlarm extends AsyncTask<Void, Void, List<Trip>> {

        @Override
        protected List<Trip> doInBackground(Void... voids) {
            return Home.database.tripDAO().selectUpcomingTrip(Home.fireBaseUseerId, "upcoming");
        }

        @Override
        protected void onPostExecute(List<Trip> trips) {
            super.onPostExecute(trips);
            for (int i = 0; i < trips.size(); i++) {
                if (trips.get(i).getTripStatus().equals("upcoming")) {
                    if (Calendar.getInstance().getTimeInMillis() > trips.get(i).getCalendar()) {
                        int finalI = i;
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Home.database.tripDAO().updateTripStatus(Home.fireBaseUseerId,trips.get(finalI).getId(), Final.MISSED_TRIP_STATUS);
                            }
                        }).start();
                    } else {
                        initAlarm(trips.get(i));
                    }
                }
            }
            viewPager.getAdapter().notifyDataSetChanged();
        }
    }
*/



    private void initComponent() {
        TabLayout tabLayout = findViewById(R.id.main_home_tabLayout);
        viewPager = findViewById(R.id.main_home_pager);
        //initializing viewPager by my view adabter
        ViewerPageAdapter adapter = new ViewerPageAdapter(this);
        viewPager.setAdapter(adapter);
        //link tab with viewpager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            //give name in case of position
            switch (position) {
                case 0:
                    tab.setText("UpComing");
                    break;
                case 1:
                    tab.setText("History");
                    break;
                default:
                    tab.setText("profile");
                    break;
            }
        }).attach();
    }
}

