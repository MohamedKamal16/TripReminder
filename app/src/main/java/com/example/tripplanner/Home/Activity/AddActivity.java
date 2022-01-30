package com.example.tripplanner.Home.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.tripplanner.Home.Fragment.AddNoteFragment;
import com.example.tripplanner.Home.Fragment.FragmentAddTrip;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Final;

public class AddActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    Fragment fragment;
    public static int ID;
    public static int key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //get key and id from Bundle
        Bundle bundle = getIntent().getExtras();
        key = bundle.getInt("KEY");
        ID=bundle.getInt("ID");

// condition to show note and add on switch fragment use key as flag
        if (key == 3) {
            //Add note
            fragmentManager = getSupportFragmentManager();
            fragment = new AddNoteFragment();
            fragmentManager.beginTransaction().add(R.id.switchFragment, fragment, Final.FRAGMENT_TAG).commit();
        } else {
            if (savedInstanceState == null) {
                // AddTrip
                fragmentManager = getSupportFragmentManager();
                fragment = new FragmentAddTrip();
                fragmentManager.beginTransaction().add(R.id.switchFragment, fragment, Final.FRAGMENT_TAG).commit();
            }
            if (savedInstanceState != null) {
                //not clear
                fragment = fragmentManager.findFragmentByTag(Final.FRAGMENT_TAG);
                fragment = getSupportFragmentManager().getFragment(savedInstanceState, "myFragmentName");
            }
        }

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "myFragmentName", fragment);
    }


/*
    private void errorWarningForNotGivingDrawOverAppsPermissions(){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this,R.style.AlertDialogTheme);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog_warning,(ConstraintLayout) findViewById(R.id.dialogLayoutContainer));
        builder.setView(view);
        ((TextView)view.findViewById(R.id.textTitle)).setText(Constants.APP_NAME);
        ((TextView)view.findViewById(R.id.textMessage)).setText("Unfortunately the display over other apps permission" +
                " is not granted so the application might not behave properly \nTo enable this permission kindly restart the application");
        ((Button)view.findViewById(R.id.btnOk)).setText(Constants.PER_DIALOG_OK);
        ((ImageView)view.findViewById(R.id.imgTitle)).setImageResource(R.drawable.ic_baseline_warning_24);

        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();


        view.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });

        if(alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }*/


}