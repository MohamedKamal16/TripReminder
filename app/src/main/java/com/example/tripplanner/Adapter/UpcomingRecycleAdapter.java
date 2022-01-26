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
<<<<<<< Updated upstream
=======
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Trip;



>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
        Trip tripadd= (Trip) tripList.get(position);
        holder.tvName.setText(tripadd.getTripName());
        holder.tvEndtrip.setText(tripadd.getEndPoint());
        holder.tvStarttrip.setText(tripadd.getStartPoint());
        holder.tvTime.setText(tripadd.getTime());
        holder.tvDate.setText(tripadd.getDate());
        holder.start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.editNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.btn_canceltrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.btn_updatetrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
>>>>>>> Stashed changes


    }



    @Override
    public int getItemCount() {
        return tripList.size();
    }

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sourceTxt, destinationTxt, nameTxt, timeTxt, dateTxt;
        ImageView trip_img;
        ImageButton start_btn;
<<<<<<< Updated upstream
        ImageButton editNotes;
=======
        ImageButton editNotes,btn_updatetrip,btn_canceltrip;
>>>>>>> Stashed changes


        public MyViewHolder(View itemView) {
            super(itemView);

        }

    }
}