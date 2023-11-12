package com.example.junior.Activities;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App extends Application {

    private static Database database;

    public static HashMap<String, String> mainInfo = new HashMap<>();

    @Override
    public void onCreate() {
mainInfo.keySet().add("Заголовок");
mainInfo.keySet().add("Тип документа");
mainInfo.keySet().add("Руководитель");
mainInfo.keySet().add("Выполняющий");
mainInfo.keySet().add("Место и год");
mainInfo.keySet().add("Организация");
super.onCreate();
    }
}
