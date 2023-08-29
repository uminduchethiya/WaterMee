package com.example.waterme_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CloudSync extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText valueEditText;
    private TextView outputTextView;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_sync);
        button = findViewById(R.id.backCloud);

        // mDatabase = FirebaseDatabase.getInstance().getReference();
        valueEditText = findViewById(R.id.valueEditTextS1);
        Button updateButton = findViewById(R.id.updateButtonS2);
        outputTextView = findViewById(R.id.outputTextViewS2);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("SoilMoisterUserStatus");
        reference.child("userStatus").setValue(0);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueStr = valueEditText.getText().toString();
                int userStatus = Integer.parseInt(valueStr);
                reference.child("userStatus").setValue(userStatus);


                reference.child("userStatus").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int value = dataSnapshot.getValue(int.class);
                        if (value !=0) {
                            outputTextView.setText("Sucessfully updated");
                        }
                        else {
                            outputTextView.setText("Not updated");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle errors
                        Toast.makeText(CloudSync.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

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