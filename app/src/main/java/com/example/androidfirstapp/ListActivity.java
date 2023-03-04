package com.example.androidfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.androidfirstapp.database.DbAndroid;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList list;
    SimpleAdapter sa;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DbAndroid db = new DbAndroid(getApplicationContext(),"manpower",null,1);
        list = db.getTrainees();

        btn = findViewById(R.id.buttonlist);
        System.out.println(list.size());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, HomeActivity.class));
            }
        });

        sa = new SimpleAdapter(this,list,R.layout.data_list, new String[]{"id","userName","password"},
                new int[]{R.id.line_id, R.id.line_name,R.id.line_password});

        ListView lv = findViewById(R.id.list_view);
        lv.setAdapter(sa);
    }
}