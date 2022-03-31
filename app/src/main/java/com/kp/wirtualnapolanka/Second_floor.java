package com.kp.wirtualnapolanka;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Second_floor extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_floor);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
