package com.stepprototype.viewdevicebattery;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<DeviceInfo> listItems;
    private MyListAdapter adapter;
    private ListView mListView;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("UserDeviceBattery");

        mListView = (ListView) findViewById(R.id.mylistview);

        databaseListener();
    }

    private void databaseListener() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listItems = new ArrayList<DeviceInfo>();

                for (DataSnapshot deviceSnapshot: dataSnapshot.getChildren()) {
                    DeviceInfo deviceInfo = deviceSnapshot.getValue(DeviceInfo.class);

                    listItems.add(deviceInfo);
                }

                adapter = new MyListAdapter(getApplicationContext(), R.layout.adapter_view_layout, listItems);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
