package com.example.tripplanner.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tripplanner.Home.Activity.AddActivity;
import com.example.tripplanner.Home.Activity.Home_Activity;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Final;
import com.example.tripplanner.TripData.Trip;
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
                initMap(((Trip) tripList.get(position)).getEndPointLatitude(),((Trip) tripList.get(position)).getEndPointLongitude());

               new Thread(() -> Home_Activity.database.tripDAO().updateTripStatus(Home_Activity.fireBaseUserId,((Trip) tripList.get(position)).getId(),Final.FINISHED_TRIP_STATUS)).start();

            }
        });

        holder.editNotes.setOnClickListener(v -> editNotes((Trip) tripList.get(position)));

        holder.btn_canceltrip.setOnClickListener(v -> {
            deleteWarnDialog( ((Trip) tripList.get(position)),(position-1));
            tripList.remove(tripList.get(position));
        });

        holder.btn_updatetrip.setOnClickListener(v -> {
            updateTrip((Trip) tripList.get(position));
            notifyDataSetChanged();
            holder.itemView.setVisibility(View.INVISIBLE);
        });

    }



    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public void initMap(double latitude, double longtitude){
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=&destination="+latitude+","+longtitude+"&travelmode=driving");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);
    }

    public void updateTrip(Trip trip){
        Intent intent = new Intent(context,AddActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("KEY",2);
        bundle.putInt("ID",trip.getId());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public void editNotes(Trip trip){
        Intent intent = new Intent(context, AddActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("KEY",3);
        bundle.putInt("ID",trip.getId());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }



    public void deleteWarnDialog(Trip trip , int position){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.delete_dialog, activity.findViewById(R.id.dialogLayoutContainer));
        builder.setView(view);
        ((TextView)view.findViewById(R.id.textTitle)).setText(Final.APP_NAME);
        ((TextView)view.findViewById(R.id.textMessage)).setText("Do you want to delete this trip ?");
        ((Button)view.findViewById(R.id.btnOk)).setText("Cancel");
        ((Button)view.findViewById(R.id.btnCancel)).setText("Ok");
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnOk).setOnClickListener(v -> alertDialog.dismiss());

        view.findViewById(R.id.btnCancel).setOnClickListener(v -> {
            new Thread(() -> {
                Home_Activity.database.tripDAO().updateTripStatus(Home_Activity.fireBaseUserId,trip.getId(),Final.CANCEL_TRIP_STATUS);
            //    unregisterAlarm(trip);

            }).start();
            //TODO CLOSE ALARM

            notifyDataSetChanged();
            alertDialog.dismiss();
        });

        if(alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
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