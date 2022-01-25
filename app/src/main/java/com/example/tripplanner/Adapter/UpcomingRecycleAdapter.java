package com.example.tripplanner.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


//Recycleview Adapter<MyViewHolder>
public class UpcomingRecycleAdapter extends RecyclerView.Adapter<UpcomingRecycleAdapter.MyViewHolder> {
    List tripList;
    Context context;
    Activity activity;

    public UpcomingRecycleAdapter(List tripList, Context context, Activity activity) {
        this.tripList = tripList;
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public UpcomingRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingRecycleAdapter.MyViewHolder holder, int position) {


    }



    @Override
    public int getItemCount() {
        return tripList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sourceTxt, destinationTxt, nameTxt, timeTxt, dateTxt;
        ImageView trip_img;
        ImageButton start_btn;
        ImageButton editNotes;


        public MyViewHolder(View itemView) {
            super(itemView);

        }

    }
}