package com.example.waterme_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShutDown extends AppCompatActivity {
    private Switch switch1;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shut_down);
        button = findViewById(R.id.backShutDown);
        switch1 = findViewById(R.id.switch1);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
                finish();
            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("ShutdownStatus");

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseDatabase.getInstance().getReference("ShutdownStatus").child("Switch01").setValue(isChecked);
            }
        });
    }
}