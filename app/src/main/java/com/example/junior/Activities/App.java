package com.example.junior.Activities;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;

import com.example.junior.Controllers.FirebaseController;

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
    public static Context instance;
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

    @Override
    public void onCreate() {
        instance = this.getApplicationContext();
        controller =new FirebaseController();
mainInfo.put("Тип документа", "");
mainInfo.put("Руководитель", "");
mainInfo.put("Выполняющий", "");
mainInfo.put("Место и год", "");
mainInfo.put("Организация", "");
super.onCreate();
    }
}
