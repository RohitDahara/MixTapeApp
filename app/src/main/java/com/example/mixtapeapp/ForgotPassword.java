package com.example.mixtapeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassword extends AppCompatActivity {

    EditText etxte;
    Button btnfp;
    Button btnback;
    private FirebaseAuth mAuth;
    String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etxte = findViewById(R.id.etxte);
        btnback = findViewById(R.id.btnback);
        btnfp = findViewById(R.id.btnfp);
        mAuth = FirebaseAuth.getInstance();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword.this, SignUp.class));
            }
        });

        btnfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etxte.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ForgotPassword.this, "enter valid email ", Toast.LENGTH_LONG).show();
                } else {
                    ResetPassword();
                }
            }
        });


    }

    public void ResetPassword() {
        mAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ForgotPassword.this, "Email has been sent to your account ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPassword.this, SignUp.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPassword.this, "error " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }
}