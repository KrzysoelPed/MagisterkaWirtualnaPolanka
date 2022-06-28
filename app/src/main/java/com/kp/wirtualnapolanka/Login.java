package com.kp.wirtualnapolanka;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    Toolbar toolbar;
    Button register_button;
    Button login_button;
    Button doorButton;
    EditText mEmailLogin;
    EditText mPassLogin;
    Boolean czy_logowac = false;
    FirebaseAuth mAuth;
    Boolean zalogowany = false;
    FirebaseDatabase db;
    DatabaseReference pomieszczeniedBref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.login_view);
        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        register_button = findViewById (R.id.go_to_register);
        login_button = findViewById (R.id.login_button);
        mEmailLogin = findViewById (R.id.login_mail);
        mPassLogin = findViewById (R.id.login_password);
        doorButton = findViewById (R.id.door_button);

        mAuth = FirebaseAuth.getInstance ();
        register_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent open_register_activity = new Intent (Login.this, Register.class);
                startActivity (open_register_activity);
                finish();
            }
        });

        login_button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String login_mail = mEmailLogin.getText ().toString ();
                String login_pass = mPassLogin.getText ().toString ();
                Log.i("TEST", "KRZYSZTOF - WSZEDLEM");
                startService (new Intent (getApplication (),BackgroundService.class));

                if(login_mail.isEmpty ())
                {
                    mEmailLogin.setError ("Brak adresu e-mail");
                    mEmailLogin.requestFocus();
                    czy_logowac = false;;
                }
                else
                {
                    czy_logowac = true;
                }
                if(login_pass.isEmpty ())
                {
                    mPassLogin.setError ("Brak hasła użytkownika");
                    mPassLogin.requestFocus();
                    czy_logowac = false;
                }
                else
                {
                    czy_logowac= true;
                }

                if(czy_logowac)
                {
                    Log.i("TEST", "KRZYSZTOF - AUTH");
                    mAuth.signInWithEmailAndPassword (login_mail, login_pass).addOnSuccessListener (new OnSuccessListener<AuthResult> () {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            zalogowany = true;
                            db = FirebaseDatabase.getInstance ();
                            pomieszczeniedBref = db.getReference ("Pomieszczenie");
                            //Log.i("TEST", "KRZYSZTOF - WSZEDLEM DO LOGOWANIA");
                            Intent new_intent = new Intent (Login.this, MainActivity.class);
                            new_intent.putExtra ("czy_zalogowany",zalogowany);
                            finish();
                            startActivity (new_intent);
                            Toast.makeText (Login.this, "Zalogowano pomyślnie!", Toast.LENGTH_LONG).show ();
                        }

                    });
                }

            }
        });

    }



}