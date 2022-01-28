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
            fragmentManager.beginTransaction().add(R.id.switchFragment, fragment, "fragment").commit();
        } else {
            if (savedInstanceState == null) {
                // AddTrip
                fragmentManager = getSupportFragmentManager();
                fragment = new FragmentAddTrip();
                fragmentManager.beginTransaction().add(R.id.switchFragment, fragment, "fragment").commit();
            }
            if (savedInstanceState != null) {
                //not clear
                fragment = fragmentManager.findFragmentByTag("fragment");
                fragment = getSupportFragmentManager().getFragment(savedInstanceState, "myFragmentName");
            }
        }

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "myFragmentName", fragment);
        Log.i(FragmentAddTrip.TAG, "onSaveInstanceState:Ac ");
    }


}