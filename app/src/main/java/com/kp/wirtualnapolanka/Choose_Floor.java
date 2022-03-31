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
    ImageButton button_first_floor;
    ImageButton button_second_floor;
    Intent button_ground_intent;
    Intent button_first_floor_intent;
    Intent button_second_floor_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.choose_floor);

        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        button_ground = findViewById(R.id.ground);
        button_first_floor = findViewById(R.id.floor1);
        button_second_floor = findViewById(R.id.floor2);

        button_ground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_ground_intent = new Intent(Choose_Floor.this, Ground_floor.class);
                startActivity(button_ground_intent);
            }
        });

        button_first_floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_first_floor_intent = new Intent(Choose_Floor.this, First_floor.class);
                startActivity(button_first_floor_intent);
            }
        });

        button_second_floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button_second_floor_intent = new Intent(Choose_Floor.this, Second_floor.class);
                startActivity(button_second_floor_intent);
            }
        });





    }
}