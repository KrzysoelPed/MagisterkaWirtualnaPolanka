package com.kp.wirtualnapolanka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner;
    Button login_button;
    Button register_button;
    EditText name, surname, type, email, pass;
    FirebaseFirestore FirestoreDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.register_view);

        register_button = findViewById (R.id.go_to_register);
        name = findViewById (R.id.register_name);
        surname = findViewById (R.id.register_surname);
        email = findViewById (R.id.register_mail);
        pass = findViewById (R.id.register_password);
        toolbar = findViewById (R.id.toolbar);
        FirestoreDatabase = FirebaseFirestore.getInstance ();
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

        register_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String name_text = name.getText ().toString ();
                String surname_text = surname.getText ().toString ();
                String email_text = email.getText ().toString ();
                String pass_text = pass.getText ().toString ();

                Map<String, Object> user = new HashMap<>();
                user.put ("Name", name_text);
                user.put("Surname", surname_text);
                user.put("E-mail", email_text);
                user.put ("Pass", pass_text);

                FirestoreDatabase.collection ("Użytkownicy").add (user).addOnSuccessListener (new OnSuccessListener<DocumentReference> () {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent new_intent = new Intent (Register.this, MainActivity.class);
                        startActivity (new_intent);
                        Toast.makeText (Register.this, "Zarejestrowano pomyślnie!", Toast.LENGTH_LONG).show ();

                    }
                });

            }
        });


    }
}