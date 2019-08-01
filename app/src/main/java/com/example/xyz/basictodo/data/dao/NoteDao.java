package com.example.xyz.basictodo.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.xyz.basictodo.data.DBConstant;
import com.example.xyz.basictodo.data.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM " + DBConstant.NOTE_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + DBConstant.NOTE_TABLE +
            " ORDER BY " + DBConstant.CREATED_AT_COLUMN + " DESC")
    LiveData<List<Note>> getAllNotes();
}