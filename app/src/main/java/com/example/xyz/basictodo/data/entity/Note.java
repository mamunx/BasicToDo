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

    @ColumnInfo(name = DBConstant.CREATED_AT_COLUMN)
    private long created_at;

    public Note(String title, String description, long created_at) {
        this.title = title;
        this.description = description;
        this.created_at = created_at;
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

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}