package com.example.tripplanner.Home.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.tripplanner.Home.Fragment.History_view;
import com.example.tripplanner.Home.Fragment.Profile;
import com.example.tripplanner.Home.Fragment.Upcomin_view;
import com.example.tripplanner.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class Home extends AppCompatActivity {
/*Add[ViewPager-TabLayout-Upcomin_view-Profile-History_view]
* to make home shape*/

    public ViewPager2 viewPager;
    //To connet table layout with fragment
    private TabLayout tabLayout;
    private Upcomin_view upcomin_view;
    private Profile profile_view;
    private History_view history_view;
    private List<Fragment> fragments;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       initComponent();

        //inflating views
        viewPager = findViewById(R.id.main_home_pager);
        tabLayout = findViewById(R.id.main_home_tabLayout);

        //initializing fragments
        upcomin_view = new Upcomin_view();
        history_view = new History_view();
        profile_view = new Profile();




    }

  private void initComponent() {
        tabLayout=findViewById(R.id.main_home_tabLayout);
        viewPager=findViewById(R.id.main_home_pager);



      //  getSupportActionBar().setElevation(0);





    }


}

