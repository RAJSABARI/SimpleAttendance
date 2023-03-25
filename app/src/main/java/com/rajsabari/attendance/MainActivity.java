package com.rajsabari.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button present,absent;
private DatabaseReference mdatabasereference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView name=findViewById(R.id.name);
       present=findViewById(R.id.present);
       absent=findViewById(R.id.absent);
        mdatabasereference= FirebaseDatabase.getInstance().getReference();
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mname=name.getText().toString();
                mdatabasereference.child("PRESENT").child(mname).setValue("Present");
                Toast.makeText(MainActivity.this, mname+"Present", Toast.LENGTH_SHORT).show();

            }
        });
        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mname=name.getText().toString();
                mdatabasereference.child("ABSENT").child(mname).setValue("Absent");
                Toast.makeText(MainActivity.this, mname+"Absent", Toast.LENGTH_SHORT).show();

            }
        });
    }
}