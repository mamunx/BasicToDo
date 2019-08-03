package com.example.xyz.basictodo.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.xyz.basictodo.data.DBConstant;

@Entity(tableName = DBConstant.NOTE_TABLE)
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstant.ID_COLUMN)
    private int id;

    @ColumnInfo(name = DBConstant.TITLE_COLUMN)
    private String title;

    @ColumnInfo(name = DBConstant.DESCRIPTION_COLUMN)
    private String description;

    @ColumnInfo(name = DBConstant.DEADLINE_COLUMN)
    private long deadline;

    public Note(String title, String description, long deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }
}