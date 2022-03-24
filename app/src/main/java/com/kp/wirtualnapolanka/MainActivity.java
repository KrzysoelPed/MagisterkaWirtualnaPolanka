package com.kp.wirtualnapolanka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton choose_floor_button;
    ImageButton login_panel_button;
    ImageButton register_panel_button;
    Intent choose_floor_view;
    Intent login_panel;
    Intent register_panel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);

        choose_floor_button = findViewById (R.id.wybor_pietra);
        login_panel_button = findViewById (R.id.panel_logowania);
        register_panel_button = findViewById (R.id.panel_rejestracji);


        choose_floor_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                choose_floor_view = new Intent (MainActivity.this, Choose_Floor.class );
                startActivity (choose_floor_view);
            }
        });

        login_panel_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                login_panel = new Intent (MainActivity.this, Login.class);
                startActivity (login_panel);
            }
        });

        register_panel_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                register_panel = new Intent (MainActivity.this, Register.class);
                startActivity (register_panel);
            }
        });
    }

}