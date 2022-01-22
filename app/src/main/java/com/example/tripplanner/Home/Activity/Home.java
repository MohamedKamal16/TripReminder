package com.example.tripplanner.Home.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;

import com.example.tripplanner.Adapter.ViewerPageAdapter;
import com.example.tripplanner.Home.Fragment.History_view;
import com.example.tripplanner.Home.Fragment.Profile_view;
import com.example.tripplanner.Home.Fragment.Upcomin_view;
import com.example.tripplanner.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class Home extends AppCompatActivity {
    /*Add[ViewPager2-TabLayout]
     * to make home shape*/
    public ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initComponent();

    }

    private void initComponent() {
        tabLayout = findViewById(R.id.main_home_tabLayout);
        viewPager = findViewById(R.id.main_home_pager);

        //initializing viewPager by my view adabter
        ViewerPageAdapter adapter = new ViewerPageAdapter(this);
        viewPager.setAdapter(adapter);

        //link tab with viewpager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            //give name in case of position
            switch (position) {
                case 0:
                    tab.setText("UpComing");
                    break;
                case 1:
                    tab.setText("History");
                    break;
                default:
                    tab.setText("profile");
                    break;
            }
        }).attach();
    }


}

