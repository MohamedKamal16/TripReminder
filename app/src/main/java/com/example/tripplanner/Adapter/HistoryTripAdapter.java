package com.example.tripplanner.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tripplanner.Home.Activity.Home_Activity;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Final;
import com.example.tripplanner.TripData.Trip;
import java.util.List;

public class HistoryTripAdapter extends RecyclerView.Adapter<HistoryTripAdapter.HistoryViewHolder> {
    List tripList;
    Context context;
    Activity activity;

    public HistoryTripAdapter(List tripList, Context context, Activity activity) {
        this.tripList = tripList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_trips_card,parent,false);
        return new HistoryTripAdapter.HistoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Trip trip=(Trip) tripList.get(position);
        holder.tvNametrip.setText(trip.getTripName());
        holder.tvdate.setText(trip.getDate());
        holder.tvstatus.setText(trip.getTripStatus());
        holder.tvstartpoint.setText(trip.getStartPoint());
        holder.tvendpoint.setText(trip.getEndPoint());
        holder.tvtime.setText(trip.getTime());

        holder.btndelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }


    public class HistoryViewHolder extends RecyclerView.ViewHolder {
    TextView tvstartpoint,tvendpoint,tvstatus,tvdate,tvtime,tvNametrip;
    ImageButton btndelet;


    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tvdate=itemView.findViewById(R.id.tv_date_history);
        tvtime=itemView.findViewById(R.id.tv_time_histroy);
        tvstartpoint=itemView.findViewById(R.id.tv_startpoint_histroy);
        tvendpoint=itemView.findViewById(R.id.tv_endpointTrip_history);
        tvstatus=itemView.findViewById(R.id.tv_status_histroy);
        tvNametrip=itemView.findViewById(R.id.tv_nametrip_histroy);
        btndelet=itemView.findViewById(R.id.btn_delethitory);
    }
    }

    
    public void deleteWarnDialog(Trip trip , int position){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.delete_dialog,(ConstraintLayout) activity.findViewById(R.id.dialogLayoutContainer));
        builder.setView(view);
        ((TextView)view.findViewById(R.id.textTitle)).setText(Final.APP_NAME);
        ((TextView)view.findViewById(R.id.textMessage)).setText("Do you want to delete this trip ?");
        ((Button)view.findViewById(R.id.btnCancel)).setText(Final.DIALOG_CANCEL);
        ((Button)view.findViewById(R.id.btnOk)).setText(Final.DIALOG_OK);

        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnCancel).setOnClickListener(v -> alertDialog.dismiss());


        view.findViewById(R.id.btnOk).setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Home_Activity.database.tripDAO().deleteById(Home_Activity.fireBaseUserId,trip.getId());
                    int finishesTripNum=Home_Activity.database.tripDAO().getCountTripType(Home_Activity.fireBaseUserId,"finished");
                    Message message=new Message();
                    message.arg1  = finishesTripNum;
                //    handler.sendMessage(message);

                }
            }).start();
            notifyDataSetChanged();
            alertDialog.dismiss();
        });

        alertDialog.show();
    }
}
