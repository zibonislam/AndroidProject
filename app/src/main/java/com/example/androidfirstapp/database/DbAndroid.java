package com.example.androidfirstapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidfirstapp.Entity.Admission;

import java.util.ArrayList;
import java.util.HashMap;

public class DbAndroid extends SQLiteOpenHelper {
    public DbAndroid(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbAndroid(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
//
//    public DbAndroid(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
//        super(context, name, version, openParams);
//    }
    private static final String TABLE_NAME ="user";
    private static final String ID_COL ="id";
    private static final String DESCRIPTION ="description";
    private static final String USER_NAME ="userName";
    private static final String PASSWORD ="password";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT," + "userName TEXT," +
                "description TEXT," +
                "password TEXT)";
        String query2 = "CREATE TABLE trainee (id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT," +
                "email TEXT," +
                "nid TEXT," + "passport Text,"+"age TEXT)" ;

//        String query = "CREATE TABLE " + TABLE_NAME + "("
//                +ID_COL + "INTEGER PRIMARY KEY AUTOINCREMENT,"
//                +USER_NAME+"TEXT,"
//                + DESCRIPTION+"TEXT,"
//                +PASSWORD+"TEXT)";

        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addNewUser(String userName, String description, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(DESCRIPTION, description);
        values.put(PASSWORD, password);

        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public int login(String userName, String Password){
        String[] arr = new String[2];
        arr[0] = userName;
        arr[1] = Password;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE userName=? and password=?",arr);
        if (c.moveToFirst()){
            return 1;
        }
        return 0;
    }
    public void addTrainee(Admission admission){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", admission.getTraineeName());
        values.put("email", admission.getEmail());
        values.put("nid", admission.getNid());
        values.put("passport", admission.getPassport());
        values.put("age", admission.getAge());

        sqLiteDatabase.insert("trainee",null, values);
        sqLiteDatabase.close();
    }
    public ArrayList<HashMap<String, String>> getTrainees(){
        HashMap<String, String> trainee;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM user",null);
        ArrayList<HashMap<String, String>> traineeList = new ArrayList<>(c.getCount());

        if (c.moveToFirst()){
            do {
                trainee = new HashMap<>();
                trainee.put("id", c.getString(0));
                trainee.put("description", c.getString(1));
                trainee.put("userName", c.getString(2));
                trainee.put("password", c.getString(3));
                traineeList.add(trainee);

            }
            while (c.moveToNext());
        }
        sqLiteDatabase.close();
        return  traineeList;
    }
}
