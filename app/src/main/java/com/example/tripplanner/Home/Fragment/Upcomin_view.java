package com.example.tripplanner.Home.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.Adapter.UpcomingRecycleAdapter;
import com.example.tripplanner.Home.Activity.AddActivity;
import com.example.tripplanner.Home.Activity.Home_Activity;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Final;
import com.example.tripplanner.TripData.Trip;
import com.example.tripplanner.TripData.TripViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class Upcomin_view extends Fragment {
    //refernce fbutton and recycle view
    FloatingActionButton floatingBtnAdd;
    RecyclerView tripRecycleView;
    //refernce to addapter
    private UpcomingRecycleAdapter upcomingRecycleAdapter;
    private List tripsList = new ArrayList<Trip>();
    TripViewModel tripViewModel;

    public Upcomin_view() {
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(Final.LOG_TAG, "onViewCreatedUpcoming");
        tripRecycleView = view.findViewById(R.id.trip_recycleView);
        upcomingRecycleAdapter = new UpcomingRecycleAdapter(tripsList, getContext(), getActivity());
        floatingBtnAdd = view.findViewById(R.id.add_flout_btn);
        addTrip();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_upcomingtrips, container, false);
        tripViewModel=new ViewModelProvider(this).get(TripViewModel.class);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new addTripRoom().execute();
    }

    private void addTrip() {
        floatingBtnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("KEY", 1);
            bundle.putInt("ID", -1);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    private class addTripRoom extends AsyncTask<Void, Void, List<Trip>> {

        @Override
        protected List<Trip> doInBackground(Void...voids) {
            return tripViewModel.selectUpcomingTrip(Home_Activity.fireBaseUserId, "upcoming");
        }

        @Override
        protected void onPostExecute(List<Trip> trips) {
            super.onPostExecute(trips);
            tripsList = trips;
            upcomingRecycleAdapter = new UpcomingRecycleAdapter(tripsList, getContext(), getActivity());
            tripRecycleView.setAdapter(upcomingRecycleAdapter);
        }
    }
}
