package com.example.tripplanner.Home.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tripplanner.Adapter.HistoryTripAdapter;
import com.example.tripplanner.Home.Activity.Home_Activity;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Trip;
import java.util.ArrayList;
import java.util.List;

public class History_view extends Fragment {
    RecyclerView recyclerView;
    HistoryTripAdapter adapter;
    int finishedTripsNum;
    List tripList=new ArrayList<Trip>();



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new addhistroyRoom().execute();
        recyclerView=view.findViewById(R.id.recyclehistory);
        adapter=new HistoryTripAdapter(tripList,getContext(),getActivity());
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

  
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_history, container, false);
    }
  
  
  
  
    private class addhistroyRoom extends AsyncTask<Void, Void, List<Trip>> {

        @Override
        protected List<Trip> doInBackground(Void...voids) {
            finishedTripsNum=Home_Activity.database.tripDAO().getCountTripType(Home_Activity.fireBaseUserId,"finished");
            return Home_Activity.database.tripDAO().selectHistoryTrip(Home_Activity.fireBaseUserId,"Cancel","Finished","Missed");

        }

        @Override
        protected void onPostExecute(List<Trip> trips) {
            super.onPostExecute(trips);
            tripList = trips;
            adapter=new HistoryTripAdapter(tripList,getContext(),getActivity());
            LinearLayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
            recyclerView.setLayoutManager(manager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);

        }

        }

    }
}
