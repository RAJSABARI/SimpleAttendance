package com.rajsabari.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

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
import com.rajsabari.Realm.ValueRealmModel;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    TextView value;
    Button minus, plus;

    long totalValue = 0;
    Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRef();
        initClick();
        getValueFromDatabase();
        getValueFromLocalDb();
    }

    void initRef() {
        value = findViewById(R.id.value);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
    }

    void initClick() {
        minus.setOnClickListener(view -> {
            setValueToFirebase(totalValue - 1);
        });

        plus.setOnClickListener(view -> {
            setValueToFirebase(totalValue + 1);
        });
    }

    void setValueToFirebase(long value) {
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Value");
        firebaseDatabase.child("Value").setValue(String.valueOf(value));
    }

    void getValueFromDatabase(){
        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Value");
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ValueModel viewModel = snapshot.getValue(ValueModel.class);

                ValueRealmModel valueRealmModel =  new ValueRealmModel();
                valueRealmModel.setValue(viewModel.getValue());
                realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(valueRealmModel));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    void getValueFromLocalDb(){

        RealmResults<ValueRealmModel> valueRealmModel = realm.where(ValueRealmModel.class).findAll();

        if(valueRealmModel.size() != 0){
            totalValue = Long.parseLong(valueRealmModel.get(0).getValue());
            setValueToTextView();
        }

        valueRealmModel.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<ValueRealmModel>>() {
            @Override
            public void onChange(RealmResults<ValueRealmModel> valueRealmModels, OrderedCollectionChangeSet changeSet) {
                totalValue = Long.parseLong(valueRealmModel.get(0).getValue());
                setValueToTextView();
            }
        });

    }

    void setValueToTextView(){
        value.setText(String.valueOf(totalValue));
    }
}