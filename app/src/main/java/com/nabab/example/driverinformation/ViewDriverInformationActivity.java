package com.nabab.example.driverinformation;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewDriverInformationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<DriverInformation> driverInformationList;
    private DriverAdapter driverAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_driver_information);

        initialization();
        retrieveInformationFromDatabase();
    }

    private void retrieveInformationFromDatabase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userInfo = databaseReference.child("Driver");
        userInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    DriverInformation driverInformation = data.getValue(DriverInformation.class);

                    driverInformationList.add(driverInformation);
                    driverAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initialization() {
        recyclerView = findViewById(R.id.recyclerViewId);
        driverInformationList = new ArrayList<>();
        driverAdapter = new DriverAdapter(this, driverInformationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(driverAdapter);


    }
}
