package com.example.junior;

import android.app.Activity;
import android.app.Application;

import androidx.room.Database;

import java.util.HashMap;

public class App extends Application {

    public static final int RESULT_CODE_SINGIN = 2;
    Activity instanse;
    private static Database database;
    public static HashMap<String, String> getMainInfo() {
        return mainInfo;
    }

    public static void setMainInfo(HashMap<String, String> mainInfo) {
        App.mainInfo = mainInfo;
    }

    public static HashMap<String, String> mainInfo = new HashMap<>();

    @Override
    public void onCreate() {
mainInfo.put("Заголовок",  "");
mainInfo.put("Тип документа", "");
mainInfo.put("Руководитель", "");
mainInfo.put("Выполняющий", "");
mainInfo.put("Место и год", "");
mainInfo.put("Организация", "");
super.onCreate();
    }
}
