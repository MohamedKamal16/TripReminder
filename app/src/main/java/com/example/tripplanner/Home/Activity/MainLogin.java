package com.example.tripplanner.Home.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tripplanner.R;
import com.example.tripplanner.databinding.ActivityMainLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainLogin extends AppCompatActivity {
    ActivityMainLoginBinding binding;
    FirebaseAuth auth;
    AlertDialog.Builder resetalert;
    LayoutInflater inflater;
    String resetPasswoed;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        resetalert= new AlertDialog.Builder(MainLogin.this);
        inflater=this.getLayoutInflater();



        binding.tvLoginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainLogin.this, MainRegister.class));
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkcrededentails();
            }
        });

        binding.tvLoginForgitpasss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View view1=inflater.inflate(R.layout.resetpassword,null);
              resetalert.setTitle("Reset Forget Password?")
                      .setMessage("Enter your Email to get password reset link.")
                      .setPositiveButton("Reset", (dialogInterface, i) -> {

                          EditText email=view.findViewById(R.id.ed_resetpassword_login);
                          if(email.getText().toString().isEmpty())
                          {
                              email.setError("Email field");
                              email.requestFocus();
                              return;
                          }

                          auth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {

                          auth.sendPasswordResetEmail(Email).addOnSuccessListener(new OnSuccessListener<Void>() {
                              @Override
                              public void onSuccess(Void unused) {
                                  Toast.makeText(getApplicationContext(),"Reset Email send Check your Email",Toast.LENGTH_LONG).show();
                              }
                          }).addOnFailureListener(new OnFailureListener() {
                              @Override
                              public void onFailure(@NonNull Exception e) {
                                  Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();


                              }
                          });




                      }).setNegativeButton("Cancel",null)
                     .setView(view1)
                      .create()
                      .show();

            }
        });

    }

    private void checkcrededentails() {

        String email = binding.edLoginEmail.getText().toString();
        String password = binding.edLoginPassword.getText().toString();

        if (email.isEmpty() || !email.contains("@")) {
            showErorr(binding.edLoginEmail, "your Email is not valid");
        } else if (password.isEmpty() || password.length() < 7) {
            showErorr(binding.edLoginPassword, "your password is not valid");
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    startActivity(new Intent(getApplicationContext(), Home_Activity.class));
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

        }

    }



    private void showErorr(EditText text, String s) {
        text.setError(s);
        text.requestFocus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Home_Activity.class));
            finish();
        }
        }
    }
