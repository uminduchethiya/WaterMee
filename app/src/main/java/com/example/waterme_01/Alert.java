package com.example.waterme_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Alert extends AppCompatActivity {
    private DatabaseReference mDatabase;

    private TextView outputSoil;
    private TextView outputTemperature;
    private TextView outputAlert;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        outputSoil = findViewById(R.id.outputMoisterLevel);
        outputTemperature = findViewById(R.id.outputTemperature);
        outputAlert = findViewById(R.id.outputAlert);
        button = findViewById(R.id.backAlert);
        mDatabase.child("soilStatus").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String outputMessage = dataSnapshot.getValue(String.class);
                if (outputMessage != null) {
                    outputSoil.setText(outputMessage);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Alert.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mDatabase.child("Temperature").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String outputMessage = dataSnapshot.getValue(String.class);
                if (outputMessage != null) {
                    outputTemperature.setText(outputMessage);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Alert.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mDatabase.child("Humidity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String outputMessage = dataSnapshot.getValue(String.class);
                if (outputMessage != null) {
                    outputAlert.setText(outputMessage);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Alert.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}