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

    
     /*
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Trip trip= (Trip) tripList.get(position);
        holder.nameTxt.setText(trip.getTripName());
        holder.timeTxt.setText(trip.getTime());
        holder.dateTxt.setText(trip.getDate());
        holder.sourceTxt.setText(trip.getStartPoint());
        holder.destinationTxt.setText(trip.getEndPoint());
        holder.dateTxt.setText(trip.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTrip((Trip) tripList.get(position));
                notifyDataSetChanged();
                holder.itemView.setVisibility(View.INVISIBLE);
            }
        });
//        holder.trip_img.setImageResource(trip.getTripImg());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                customTwoButtonsDialog(((Trip) tripList.get(position)),position-1);
                tripList.remove((Trip) tripList.get(position));
                return false;
            }
        });

        holder.start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMap(((Trip) tripList.get(position)).getEndPointLat(),((Trip) tripList.get(position)).getEndPointLong());
                initBubble(((Trip) tripList.get(position)).getId(),((Trip) tripList.get(position)).getUserID());
                unregisterAlarm((Trip) tripList.get(position));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HomeActivity.database.tripDAO().updateTripStatus(HomeActivity.fireBaseUseerId,((Trip) tripList.get(position)).getId(),"finished");
                    }
                }).start();
            }
        });
        //edit notes
        holder.editNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNotes((Trip) tripList.get(position));
            }
        });
    }*/

    @Override
    public int getItemCount() {
        return 0;
    }
 /*  @Override
    public int getItemCount() {
        return tripList.size();
    }*/



 /*@NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_upcoming_item, parent, false);
        return new MyViewHolder(itemView);
    }*/

   /* public void initMap(double latitude, double longtitude){
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=&destination="+latitude+","+longtitude+"&travelmode=driving");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);
    }*/

   /* public void updateTrip(Trip trip){
        Intent intent = new Intent(context,AddTripActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("KEY",2);
        bundle.putInt("ID",trip.getId());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }*/

 /*   public void editNotes(Trip trip){
        Intent intent = new Intent(context,AddTripActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("KEY",3);
        bundle.putInt("ID",trip.getId());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }*/

/*
    public void unregisterAlarm(Trip trip) {
        Intent notifyIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (context,trip.getId(), notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(notifyPendingIntent);
        }
    }

    public void initBubble(int tripId, String tripUserId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
            askPermission();
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Intent intent = new Intent(context, FloatingViewService.class);
            intent.putExtra(Constants.TRIP_ID,tripId);
            context.startService(intent);
            activity.finish();
        } else if (Settings.canDrawOverlays(context)) {
            Intent intent = new Intent(context, FloatingViewService.class);
            intent.putExtra(Constants.TRIP_ID,tripId);
            context.startService(intent);
            activity.finish();
        } else {
            askPermission();
            Toast.makeText(context, "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
        }
    }

    private void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + context.getPackageName()));
        activity.startActivityForResult(intent, 2084);
    }

    public void customTwoButtonsDialog(Trip trip , int position){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context,R.style.AlertDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_permission_dialog,(ConstraintLayout) activity.findViewById(R.id.dialogLayoutContainer));
        builder.setView(view);
        ((TextView)view.findViewById(R.id.textTitle)).setText(Constants.APP_NAME);
        ((TextView)view.findViewById(R.id.textMessage)).setText("Do you want to delete this trip ?");
        ((Button)view.findViewById(R.id.btnCancel)).setText(Constants.PER_DIALOG_CANCEL);
        ((Button)view.findViewById(R.id.btnOk)).setText(Constants.PER_DIALOG_CONFIRM);
        ((ImageView)view.findViewById(R.id.imgTitle)).setImageResource(R.drawable.ic_baseline_hourglass_bottom_24);

        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        view.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HomeActivity.database.tripDAO().updateTripStatus(HomeActivity.fireBaseUseerId,trip.getId(),"cancelled");
                        unregisterAlarm(trip);
                        // tripList.remove(trip);
                    }
                }).start();
                //  notifyItemRemoved(position);
                notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });

        if(alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }*/






    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sourceTxt, destinationTxt, nameTxt, timeTxt, dateTxt;
        ImageView trip_img;
        ImageButton start_btn;
        ImageButton editNotes;

        public MyViewHolder(View itemView) {
            super(itemView);
          /*  sourceTxt=itemView.findViewById(R.id.source_txt);
            destinationTxt=itemView.findViewById(R.id.destination_txt);
            nameTxt=itemView.findViewById(R.id.name_txt);
            timeTxt=itemView.findViewById(R.id.time_txt);
            dateTxt=itemView.findViewById(R.id.date_txt);
            trip_img=itemView.findViewById(R.id.trip_img);
            start_btn= itemView.findViewById(R.id.start_btn);
            editNotes=itemView.findViewById(R.id.btn_editNote);*/
        }

    }
}