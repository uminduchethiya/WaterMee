package com.example.waterme_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity {

    FirebaseAuth auth;
    CardView cardHome;
    CardView cardCloudSync;
    CardView cardAlert;
    CardView cardLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth = FirebaseAuth.getInstance();
        cardHome = findViewById(R.id.cardHome);
        cardCloudSync = findViewById(R.id.cardCloudSync);
        cardAlert = findViewById(R.id.cardAlert);
        cardLogout = findViewById(R.id.cardLogout);

        cardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ShutDown.class);
                startActivity(intent);
                finish();
            }
        });

        cardCloudSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CloudSync.class);
                startActivity(intent);
                finish();
            }
        });


        cardAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Alert.class);
                startActivity(intent);
                finish();
            }
        });

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}