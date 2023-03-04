package com.example.androidfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfirstapp.database.DbAndroid;

public class MainActivity extends AppCompatActivity {
    EditText user,password;
    Button loginbtn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.editTextTextLoginUsername);
        password = findViewById(R.id.editTextTextLoginPassword);
        loginbtn = findViewById(R.id.buttonLogin);
        tv =findViewById(R.id.textView2Login);


        DbAndroid db = new DbAndroid(getApplicationContext(),"manpower",null,1);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = user.getText().toString();
                String userPassword = password.getText().toString();
                if (userName.length()==0 || userPassword.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill all the data field", Toast.LENGTH_SHORT).show();
                } else{
                    if (db.login(userName,userPassword)==1){
                        Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("userN", userName);
                        editor.apply();
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Wrong Password and username ", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, RegistratonActivity.class);
//
//                it.putExtra("username",user.getText().toString());
//                it.putExtra("password",password.getText().toString());
                startActivity(it);
            }
        });
    }
}