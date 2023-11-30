package com.example.junior.SharedPreferenses;

import static com.example.junior.Activities.App.NAME_SP_NAME;
import static com.example.junior.Activities.App.ORG_SP_NAME;
import static com.example.junior.Activities.App.PLACE_SP_NAME;
import static com.example.junior.Activities.App.TEACHER_SP_NAME;
import static com.example.junior.Activities.App.YEAR_SP_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;

public class DatabaseSP {
    private final SharedPreferences storage;

    public DatabaseSP(Context context) {

        storage = context.getSharedPreferences("Lgjihob", Context.MODE_PRIVATE);
        if(storage==null){

        }
    }
    public void saveName(String  name) {
        SharedPreferences.Editor editor = storage.edit();
        editor.putString(NAME_SP_NAME, name);
        editor.apply();
    }

    public String getName() {
        return storage.getString(NAME_SP_NAME, "");
    }
    public void savePlace(String  name) {
        SharedPreferences.Editor editor = storage.edit();
        editor.putString(PLACE_SP_NAME, name);
        editor.apply();
    }

    public String getPlace() {
        return storage.getString(PLACE_SP_NAME, "");
    }public void saveTeacher(String  name) {
        SharedPreferences.Editor editor = storage.edit();
        editor.putString(TEACHER_SP_NAME, name);
        editor.apply();
    }

    public String getTeacher() {
        return storage.getString(TEACHER_SP_NAME, "");
    }public void saveYear(String  name) {
        SharedPreferences.Editor editor = storage.edit();
        editor.putString(YEAR_SP_NAME, name);
        editor.apply();
    }

    public String getYear() {
        return storage.getString(YEAR_SP_NAME, "");
    }public void saveOrganization(String  name) {
        SharedPreferences.Editor editor = storage.edit();
        editor.putString(ORG_SP_NAME, name);
        editor.apply();
    }

    public String getOrganization() {
        return storage.getString(ORG_SP_NAME, "");
    }

}