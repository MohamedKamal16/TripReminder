package com.example.tripplanner.Home.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripplanner.R;
import com.example.tripplanner.databinding.ActivityMainRegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainRegister extends AppCompatActivity {
 ActivityMainRegisterBinding binding;
 FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();

        binding.tvRegisterAlreadyaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainRegister.this,MainLogin.class));
            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkcrededentails();
            }
        });
    }
    private void checkcrededentails()
    {
        String username=binding.edRegisterUsername.getText().toString();
        String email=binding.edRegisterEmail.getText().toString();
        String password=binding.edRegisterpassword.getText().toString();
        String conPassword=binding.edRegisterConfirmpassword.getText().toString();

        if(username.isEmpty() || username.length()<7)
        {
            showErorr(binding.edRegisterUsername,"your username is not valid");
        }else if(email.isEmpty() || !email.contains("@"))
        {
            showErorr(binding.edRegisterEmail,"your Email is not valid");
        }else if(password.isEmpty() || password.length()<7)
        {
            showErorr(binding.edRegisterpassword,"your password is not valid");
        }
        else if (conPassword.isEmpty() ||!conPassword.equals(password))
        {
            showErorr(binding.edRegisterConfirmpassword,"password not match");
        }
        else
        {
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    //on succes
                   startActivity(new Intent(getApplicationContext(),Home.class));
                   finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                  //onfalier
                  Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }

    }

  private void showErorr(EditText text,String s)
  {
      text.setError(s);
      text.requestFocus();
  }
}