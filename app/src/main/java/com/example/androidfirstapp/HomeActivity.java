package com.example.androidfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

 Button homebtn;
 Button listbtn;
 CardView admissioncard, listcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        admissioncard = findViewById(R.id.Admission);
        listcard = findViewById(R.id.list);

        admissioncard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AdmissionActivity.class));
            }
        });

        listcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ListActivity.class));
            }
        });

//    homebtn = findViewById(R.id.button);
//    listbtn = findViewById(R.id.buttonlist);

//        homebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(HomeActivity.this, AdmissionActivity.class);
//                startActivity(it);
//
//            }
//        });
//        listbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, ListActivity.class));
//            }
//        });
    }
}