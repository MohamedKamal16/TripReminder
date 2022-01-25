package com.example.tripplanner.Home.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.tripplanner.Home.Activity.MainLogin;
import com.example.tripplanner.R;
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

    TextView tv_username,tv_email;
    Button btnlogout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        firebaseAuth=FirebaseAuth.getInstance();
        tv_username=view.findViewById(R.id.tvUsernameprofile);
        tv_email=view.findViewById(R.id.tvEmailprofile);
        btnlogout=view.findViewById(R.id.buttonOut);
            setprofile(tv_username,tv_email);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                   startActivity(new Intent(getContext(),MainLogin.class));
                    getActivity().finish();

          }

        });

        return view;

    }
    public void setprofile(TextView tv_username, TextView tv_email)
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
                   tv_username.setText(userName);
                   tv_email.setText(Email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

             //   Toast.makeText(Profile_view.this,"Erorr No data on to profile");


            }
        });



    }
}
