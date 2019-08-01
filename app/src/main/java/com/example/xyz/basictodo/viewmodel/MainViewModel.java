package com.example.xyz.basictodo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.xyz.basictodo.data.AppRepository;
import com.example.xyz.basictodo.data.entity.Note;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private AppRepository appRepository;
    private LiveData<List<Note>> allNotes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        allNotes = appRepository.getAllNotes();
    }

    public void insert(Note note){
        appRepository.insert(note);
    }

    public void update(Note note){
        appRepository.update(note);
    }

    public void delete(Note note){
        appRepository.delete(note);
    }

    public void deleteAllNotes(){
        appRepository.deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}