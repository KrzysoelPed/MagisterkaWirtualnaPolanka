package com.kp.wirtualnapolanka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class Choose_Floor extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton button_ground;
    Intent button_ground_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.choose_floor);

        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        button_ground = findViewById(R.id.ground);

        button_ground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_ground_intent = new Intent(Choose_Floor.this, Ground_floor.class);
                startActivity(button_ground_intent);
            }
        });



    }
}