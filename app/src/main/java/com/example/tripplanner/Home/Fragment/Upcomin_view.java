package com.example.tripplanner.Home.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.Adapter.UpcomingRecycleAdapter;
import com.example.tripplanner.Home.Activity.AddActivity;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Trip;
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

    public Upcomin_view(){};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tripRecycleView=view.findViewById(R.id.trip_recycleView);
        upcomingRecycleAdapter = new UpcomingRecycleAdapter( tripsList,getContext(),getActivity());
        floatingBtnAdd = view.findViewById(R.id.add_flout_btn);

        addTrip();
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upcomingtrips,container,false);
    }

    private void addTrip() {
        floatingBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent to move from fragment to activity
                Intent intent = new Intent(getContext(), AddActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("KEY", 1);
                bundle.putInt("ID", -1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });    }


}
