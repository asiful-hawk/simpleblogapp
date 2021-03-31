package com.example.simpleblogapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.simpleblogapp.Entity.Converter;
import com.example.simpleblogapp.Entity.SimpleBlog;

@Database(entities = {SimpleBlog.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract BlogDao blogDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Blog")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
