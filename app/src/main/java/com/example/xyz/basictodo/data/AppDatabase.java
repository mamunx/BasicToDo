package com.example.xyz.basictodo.data;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.xyz.basictodo.data.dao.NoteDao;
import com.example.xyz.basictodo.data.entity.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized AppDatabase getInstance(Application context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DBConstant.DB_FILE)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}