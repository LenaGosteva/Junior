package com.example.junior.Activities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Database;

import com.example.junior.Controllers.FirebaseController;
import com.example.junior.SharedPreferenses.DatabaseSP;

import java.util.HashMap;

public class App extends Application {
    public static FirebaseController getController() {
        return controller;
    }

    public static void setController(FirebaseController controller) {
        App.controller = controller;
    }

    private static FirebaseController controller;
    public static final int RESULT_CODE_SINGIN = 2;
    public static final String NAME_SP_NAME = "uri sp name";
    public static final String PLACE_SP_NAME = "ui sp name";
    public static final String TEACHER_SP_NAME = "ur sp name";
    public static final String YEAR_SP_NAME = "i sp name";
    public static final String ORG_SP_NAME = "i i;sp name";
    public static DatabaseSP sharedPreferences;
    public static Context instance;
    private App inst = this;
    private static Database database;
    public static HashMap<String, String> getMainInfo() {
        return mainInfo;
    }

    public static void setMainInfo(HashMap<String, String> mainInfo) {
        App.mainInfo = mainInfo;
    }

    public static HashMap<String, String> mainInfo = new HashMap<>();

    public static Context getInstance() {
        return instance;
    }

    public static void setInstance(Context instance) {
        App.instance = instance;
    }

    public static DatabaseSP getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(DatabaseSP sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static Database getDatabase() {
        return database;
    }

    public static void setDatabase(Database database) {
        App.database = database;
    }

    @Override
    public void onCreate() {
        instance = this.getApplicationContext();
        sharedPreferences = new DatabaseSP(this) {
        };

        mainInfo.put("Тип документа", "");
mainInfo.put("Руководитель", "");
mainInfo.put("Выполняющий", "");
mainInfo.put("Место и год", "");
mainInfo.put("Организация", "");
super.onCreate();
    }
}
