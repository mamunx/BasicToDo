package com.example.xyz.basictodo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xyz.basictodo.R;
import com.example.xyz.basictodo.utility.Constant;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        datePicker = findViewById(R.id.date_picker);

        datePicker.setMinDate(System.currentTimeMillis());

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cross_white);
        setTitle(R.string.add_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_note) {
            saveNewNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNewNote() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        long deadline = calendar.getTimeInMillis();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
            Snackbar.make(datePicker, "Enter title and description and select a date!", Snackbar.LENGTH_LONG).show();
        else {
            Intent intent = new Intent();
            intent.putExtra(Constant.BUNDLE_TITLE, title);
            intent.putExtra(Constant.BUNDLE_DESCRIPTION, description);
            intent.putExtra(Constant.BUNDLE_DEADLINE, deadline);

            setResult(RESULT_OK, intent);
            finish();
        }
    }
}