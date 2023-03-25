package com.rajsabari.attendance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewAttendance extends AppCompatActivity {
    ListView present,absent;
ArrayList<String>arr1=new ArrayList<>();
ArrayList<String>arr2=new ArrayList<>();
DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        present=findViewById(R.id.pre);
        absent=findViewById(R.id.abs);
        ArrayAdapter arrayAdapter1=new ArrayAdapter(ViewAttendance.this, android.R.layout.simple_list_item_1,arr1);
        ArrayAdapter arrayAdapter2=new ArrayAdapter(ViewAttendance.this, android.R.layout.simple_list_item_2,arr2);
        ref= FirebaseDatabase.getInstance().getReference();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name=snapshot.getKey();
                String status=snapshot.getValue().toString();
                if (status.equals())
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })

    }
}