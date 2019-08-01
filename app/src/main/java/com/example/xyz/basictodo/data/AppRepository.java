package com.example.xyz.basictodo.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.xyz.basictodo.data.dao.NoteDao;
import com.example.xyz.basictodo.data.entity.Note;

import java.util.List;

public class AppRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public AppRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        noteDao.insert(note);
    }

    public void update(Note note) {
        noteDao.update(note);
    }

    public void delete(Note note) {
        noteDao.delete(note);
    }

    public void deleteAll() {
        noteDao.deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}