package com.example.tripplanner.Home.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tripplanner.R;
import com.example.tripplanner.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Profile_view extends Fragment {
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FragmentProfileBinding pbinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        firebaseAuth=FirebaseAuth.getInstance();

        return view;

    }
    public void setprofile()
    {
        user= firebaseAuth.getCurrentUser();
        String userId=user.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference("users").child(userId);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    HashMap<String,String> hashMap=(HashMap<String, String>)snapshot.getValue();
                    String userName=hashMap.get("username");
                    String Email=hashMap.get("Email");
                //    pbinding.tvUsernameprofile.setText(userName);
                //    pbinding.tvEmailprofile.setText(Email);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

             //   Toast.makeText(Profile_view.this,"Erorr No data on to profile");


            }
        });


    }
}
