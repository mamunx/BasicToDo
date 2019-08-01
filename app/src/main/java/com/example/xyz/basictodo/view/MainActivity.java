package com.example.xyz.basictodo.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import com.example.xyz.basictodo.R;
import com.example.xyz.basictodo.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getAllNotes().observe(this, notes -> {
            //todo show data in view
        });
    }
}