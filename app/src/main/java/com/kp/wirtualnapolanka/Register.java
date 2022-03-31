package com.kp.wirtualnapolanka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Register extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner;
    Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.register_view);

        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        spinner = findViewById (R.id.register_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource (this, R.array.values_spinner_register, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource (R.layout.spinner_view);
        spinner.setAdapter (adapter);

        login_button = findViewById (R.id.login_button);
        login_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent open_login_activity = new Intent (Register.this, Login.class);
                startActivity (open_login_activity);
            }
        });

    }
}