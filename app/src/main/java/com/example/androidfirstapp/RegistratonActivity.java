package com.example.androidfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidfirstapp.database.DbAndroid;

public class RegistratonActivity extends AppCompatActivity {

//    TextView utv;
//    TextView ptv;
   EditText UserName,Password, ConfirmPassword,Description;
   Button Register;
   TextView Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraton);

UserName = findViewById(R.id.editTextTextUsername);
Password = findViewById(R.id.editTextTextPassword);
ConfirmPassword = findViewById(R.id.editTextTextConfirmPassword);
Description = findViewById(R.id.editTextTextDescription);
Register = findViewById(R.id.buttonRegistration);
Tv = findViewById(R.id.textViewRegistration);
Tv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
                Intent it = new Intent(RegistratonActivity.this, MainActivity.class);
        startActivity(it);
    }
});

Register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String userName = UserName.getText().toString();
        String password = Password.getText().toString();
        String confirmPassword = ConfirmPassword.getText().toString();
        String description = Description.getText().toString();

//        Intent it = new Intent(RegistratonActivity.this, MainActivity.class);
//        startActivity(it);

        System.out.println(userName);
        DbAndroid db = new DbAndroid(getApplicationContext(),"manpower",null,1);
        if (userName.length()==0 || password.length()==0 || confirmPassword.length()==0 || description.length()==0)
        {
            Toast.makeText(getApplicationContext(), "Please Fill the Data Fields", Toast.LENGTH_SHORT).show();}
        else{
            if (password.compareTo(confirmPassword)==0){
                if (password.length()>8){
                    db.addNewUser(userName,description,password);
                    Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistratonActivity.this,MainActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
                }


            }
            else{
                Toast.makeText(getApplicationContext(), "Password is not matched", Toast.LENGTH_SHORT).show();
            }
        }
    }
});



//       utv = findViewById(R.id.textViewUser);
//       ptv = findViewById(R.id.textViewPassword);
//
//        Intent it = getIntent();
//        String userName = it.getStringExtra("username");
//        String password = it.getStringExtra("password");
//
//        utv.setText(userName);
//        ptv.setText(password);
    }
}