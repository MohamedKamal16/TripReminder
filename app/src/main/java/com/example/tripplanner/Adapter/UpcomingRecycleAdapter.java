package com.example.tripplanner.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Trip;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Trip;
import com.example.tripplanner.TripData.Tripadd;
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.trip_upcoming_card,parent,false);


        return new  MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UpcomingRecycleAdapter.MyViewHolder holder, int position) {

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

    }



    @Override
    public int getItemCount() {
        return tripList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvStarttrip, tvEndtrip, tvName, tvTime, tvDate;
        ImageView trip_img;
        ImageButton start_btn;
        ImageButton editNotes,btn_updatetrip,btn_canceltrip;



        public MyViewHolder(View itemView) {
            super(itemView);

                tvName=itemView.findViewById(R.id.tv_nametrip);
                tvStarttrip=itemView.findViewById(R.id.tv_starttrip);
                tvEndtrip=itemView.findViewById(R.id.tv_endtrip);
            tvDate=itemView.findViewById(R.id.tv_datetrip);
            tvTime=itemView.findViewById(R.id.tv_timetrip);
            trip_img=itemView.findViewById(R.id.trip_img);
            start_btn=itemView.findViewById(R.id.btn_starttrip);
            editNotes=itemView.findViewById(R.id.btn_editNote);
            btn_updatetrip=itemView.findViewById(R.id.btn_addtrip_update);
            btn_canceltrip=itemView.findViewById(R.id.btn_addtrip_cancel);


        }

    }
}