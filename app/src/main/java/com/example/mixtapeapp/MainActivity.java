package com.example.mixtapeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout main;
    NavigationView nv;
    Button logOut;
    FirebaseUser user;
    FirebaseAuth auth;
    ImageButton menubtn, profilebtn, homebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        logOut = findViewById(R.id.logOut);
        menubtn = findViewById(R.id.menubtn);
        main = findViewById(R.id.main);
        nv = findViewById(R.id.nv);

        NavigationView navigationView = findViewById(R.id.nv);
        navigationView.setNavigationItemSelectedListener(this);

        homebtn = findViewById(R.id.homebtn);
        profilebtn = findViewById(R.id.profilebtn);

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), SignUp.class);
            startActivity(intent);
            finish();
        }

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle home button click
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, profile.class));
            }
        });

        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (main.isDrawerOpen(GravityCompat.START)) {
                    main.closeDrawer(GravityCompat.START);
                } else {
                    main.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (main.isDrawerOpen(GravityCompat.START)) {
            main.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.profile1) {
            Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, profile.class);
            startActivity(intent);

        }

        main.closeDrawer(GravityCompat.START);
        return true;
    }
}
