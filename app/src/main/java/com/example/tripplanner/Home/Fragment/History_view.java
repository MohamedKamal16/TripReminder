package com.example.tripplanner.Home.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.Adapter.HistoryTripAdapter;
import com.example.tripplanner.Home.Activity.Home_Activity;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Final;
import com.example.tripplanner.TripData.Trip;
import com.example.tripplanner.TripData.TripViewModel;

import java.util.ArrayList;
import java.util.List;

public class History_view extends Fragment {
    RecyclerView recyclerView;
    HistoryTripAdapter adapter;
    List tripList=new ArrayList<Trip>();
    TripViewModel  model;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
       //model.selectHistoryTrip(Home_Activity.fireBaseUserId, Final.CANCEL_TRIP_STATUS,Final.FINISHED_TRIP_STATUS,Final.MISSED_TRIP_STATUS);
       new addhistroyRoom().execute();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView=view.findViewById(R.id.recyclehistory);
        model=new ViewModelProvider(this).get(TripViewModel.class);
        adapter=new HistoryTripAdapter(tripList,getContext(),getActivity());
       LinearLayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
       recyclerView.setLayoutManager(manager);
       recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }
    private class addhistroyRoom extends AsyncTask<Void, Void, List<Trip>> {

        @Override
        protected List<Trip> doInBackground(Void...voids) {
            return model.selectHistoryTrip(Home_Activity.fireBaseUserId,Final.CANCEL_TRIP_STATUS,Final.FINISHED_TRIP_STATUS,Final.MISSED_TRIP_STATUS);
        }

        @Override
        protected void onPostExecute(List<Trip> trips) {
            super.onPostExecute(trips);
            tripList = trips;

            adapter=new HistoryTripAdapter(tripList,getContext(),getActivity());
            recyclerView.setAdapter(adapter);

        }
    }
}
