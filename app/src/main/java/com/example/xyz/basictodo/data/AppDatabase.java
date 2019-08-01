package com.example.xyz.basictodo.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBTask(instance).execute();
        }
    };

    private static class PopulateDBTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDBTask(AppDatabase appDatabase) {
            noteDao = appDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Code", "Start Learning Kotlin", System.currentTimeMillis()));
            noteDao.insert(new Note("Shopping", "Go to BCity Today", System.currentTimeMillis()));
            noteDao.insert(new Note("Buy Ticket", "But tickets for JFP Blockbuster", System.currentTimeMillis()));
            noteDao.insert(new Note("Salah", "Don't forget to pray 5 times a day!", System.currentTimeMillis()));
            return null;
        }
    }
}