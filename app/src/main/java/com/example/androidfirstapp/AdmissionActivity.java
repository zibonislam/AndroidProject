package com.example.androidfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidfirstapp.Entity.Admission;
import com.example.androidfirstapp.database.DbAndroid;

public class AdmissionActivity extends AppCompatActivity {

    EditText name,email,nid,passport,age;
    Button createbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission);

        name = findViewById(R.id.editTextTexttraineeName);
        email = findViewById(R.id.editTextTextTraineeEmail2);
        nid = findViewById(R.id.editTextTextTraineeNid);
        passport = findViewById(R.id.editTextTextTraineePassport);
        age = findViewById(R.id.editTextTextTraineeAge);
        createbtn = findViewById(R.id.buttonAdmission);




        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String traineeName = name.getText().toString();
                String traineeEmail = email.getText().toString();
                String traineeNid = nid.getText().toString();
                String traineePassport = passport.getText().toString();
                String traineeAge = age.getText().toString();

                Admission admin = new Admission();
                admin.setTraineeName(traineeName);
                admin.setEmail(traineeEmail);
                admin.setNid(traineeNid);
                admin.setPassport(traineePassport);
                admin.setAge(traineeAge);

                DbAndroid db = new DbAndroid(getApplicationContext(),"manpower",null,1);

                if (name.length()==0 || email.length()==0 || nid.length()==0 || passport.length()==0 || age.length()==0 )
                {
                    Toast.makeText(getApplicationContext(), "Please Fill the Data Fields", Toast.LENGTH_SHORT).show();}

//                startActivity(new Intent(AdmissionActivity.this,HomeActivity.class));}
                else{
                    db.addTrainee(admin);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}