package com.kp.wirtualnapolanka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Login extends AppCompatActivity {

    Toolbar toolbar;
    Button register_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.login_view);

        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        register_button = findViewById (R.id.go_to_register);
        register_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent open_register_activity = new Intent (Login.this, Register.class);
                startActivity (open_register_activity);
            }
        });

    }
}