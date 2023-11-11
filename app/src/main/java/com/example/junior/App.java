package com.example.junior;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;

public class App extends Application {

    private static Database database;
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
