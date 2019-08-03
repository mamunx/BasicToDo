package com.example.xyz.basictodo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xyz.basictodo.R;
import com.example.xyz.basictodo.data.entity.Note;
import com.example.xyz.basictodo.utility.Constant;
import com.example.xyz.basictodo.view.adapter.NoteAdapter;
import com.example.xyz.basictodo.viewmodel.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    private MainViewModel mainViewModel;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.rvNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivityForResult(intent, Constant.ADD_NOTE_CODE);
        });

        NoteAdapter noteAdapter = new NoteAdapter(this);
        recyclerView.setAdapter(noteAdapter);

        mainViewModel.getAllNotes().observe(this, noteAdapter::setNoteList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mainViewModel.delete(noteAdapter.getNoteAtPos(viewHolder.getAdapterPosition()));
                Snackbar.make(fabAdd, "Note deleted", Snackbar.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.ADD_NOTE_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                String title = data.getExtras().getString(Constant.BUNDLE_TITLE, "");
                String description = data.getExtras().getString(Constant.BUNDLE_DESCRIPTION, "");
                long deadline = data.getExtras().getLong(Constant.BUNDLE_DEADLINE, System.currentTimeMillis());

                Note note = new Note(title, description, deadline);
                mainViewModel.insert(note);

                Snackbar.make(fabAdd, "Note saved!", Snackbar.LENGTH_LONG).show();
            }
        } else
            Snackbar.make(fabAdd, "Note was not saved!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all_notes) {
            mainViewModel.deleteAllNotes();
            Snackbar.make(fabAdd, "All notes deleted!", Snackbar.LENGTH_LONG).show();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}