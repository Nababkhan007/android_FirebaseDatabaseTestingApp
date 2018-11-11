package com.nabab.example.driverinformation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddInformationActivity extends AppCompatActivity {
    private EditText firstNameEt, lastNameEt, addressEt, emailEt, mobileNumberEt;
    private Button addInformationBtn, showInformationBtn;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_information);

        initialization();
        onClick();

    }

    private void loadInformationDatabase(String firstName, String lastName, String address, String email, String mobileNumber) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userInfoDatabase = databaseReference.child("Driver");
        DriverInformation driverInformation = new DriverInformation(firstName, lastName, address, email, mobileNumber);

        userInfoDatabase.push().setValue(driverInformation).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddInformationActivity.this, "Successfully Database Created", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddInformationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onClick() {
        addInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEt.getText().toString();
                String lastName = lastNameEt.getText().toString();
                String address = addressEt.getText().toString();
                String email = emailEt.getText().toString();
                String mobileNumber = mobileNumberEt.getText().toString();

                if (firstName.equals("") || lastName.equals("") || address.equals("") || email.equals("") || mobileNumber.equals("")){
                    Toast.makeText(AddInformationActivity.this, "Please fill up all above fields!", Toast.LENGTH_SHORT).show();
                }else{
                    loadInformationDatabase(firstName, lastName, address, email, mobileNumber);
                    startActivity(new Intent(AddInformationActivity.this, AddInformationActivity.class));
                }
            }
        });

        showInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddInformationActivity.this, ViewDriverInformationActivity.class));
            }
        });
    }

    private void initialization() {
        firstNameEt = findViewById(R.id.firstNameEtId);
        lastNameEt = findViewById(R.id.lastNameEtId);
        addressEt = findViewById(R.id.addressEtId);
        emailEt = findViewById(R.id.emailEtId);
        mobileNumberEt = findViewById(R.id.mobileNumberEtId);
        addInformationBtn = findViewById(R.id.addInformationBtnId);
        showInformationBtn = findViewById(R.id.showInformationBtnId);
    }
}
