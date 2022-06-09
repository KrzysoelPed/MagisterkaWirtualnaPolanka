package com.kp.wirtualnapolanka;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner;
    Button login_button;
    Button register_button;
    EditText name, surname, type, email, pass;
    FirebaseFirestore FirestoreDatabase;
    FirebaseAuth mAuth;
    Boolean czy_dodać = false;
    private static final String TAG = "Register";
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
        mAuth = FirebaseAuth.getInstance ();
        setSupportActionBar (toolbar);
        String emailTeacherPattern = "@put.poznan.pl";
        String emailStudentPattern = "@student.put.poznan.pl";
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
                String type_of_user = spinner.getSelectedItem ().toString ();
                String email_text = email.getText ().toString ();
                String pass_text = pass.getText ().toString ();

                if(name_text.isEmpty ())
                {
                    name.setError ("Brak imienia użytkownika");
                    name.requestFocus ();
                    czy_dodać = false;
                }
                else
                {
                    czy_dodać = true;
                }

                if (surname_text.isEmpty ())
                {
                    surname.setError ("Brak nazwiska użytkownika");
                    surname.requestFocus ();
                    czy_dodać = false;
                }
                else
                {
                    czy_dodać = true;
                }
                if(pass_text.isEmpty ())
                {
                    pass.setError ("Brak hasła użytkownika");
                    pass.requestFocus ();
                    czy_dodać = false;
                }
                else
                {
                    czy_dodać = true;
                }

                if(type_of_user.equals("Student"))
                {
                    if(email_text.isEmpty ())
                    {
                        email.setError ("Brak adresu e-mail");
                        email.requestFocus ();
                        czy_dodać = false;
                    }
                    if(!email.getText ().toString ().trim ().contains (emailStudentPattern))
                        {
                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder (Register.this, R.style.AlertDialogTheme);
                            dlgAlert.setMessage("Niepoprawny adres e-mail. Sprawdź czy adres w domenie student.put.poznan.pl");
                            dlgAlert.setTitle("Błędne dane rejestracyjne");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                            czy_dodać = false;
                        }
                    }

                if(type_of_user.equals("Prowadzący"))
                {
                    if(email_text.isEmpty ())
                    {
                        email.setError ("Brak adresu e-mail");
                        email.requestFocus ();
                        czy_dodać = false;
                    }
                    if(!email.getText ().toString ().trim ().contains (emailTeacherPattern))
                    {
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder (Register.this, R.style.AlertDialogTheme);
                        dlgAlert.setMessage("Niepoprawny adres e-mail. Sprawdź czy adres w domenie put.poznan.pl");
                        dlgAlert.setTitle("Błędne dane rejestracyjne");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                        czy_dodać = false;
                    }
                }


                Map<String, Object> user = new HashMap<>();
                user.put ("Name", name_text);
                user.put("Surname", surname_text);
                user.put("E-mail", email_text);
                user.put ("Pass", pass_text);
                user.put("Type",type_of_user);


                if(czy_dodać == true) {
                    FirestoreDatabase.collection ("Użytkownicy").add (user).addOnSuccessListener (new OnSuccessListener<DocumentReference> () {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            mAuth.createUserWithEmailAndPassword (email_text, pass_text);
                            Log.i("TEST REGISTER", "DODANO NOWEGO AUTH");
                            Intent new_intent = new Intent (Register.this, MainActivity.class);
                            startActivity (new_intent);
                            Toast.makeText (Register.this, "Zarejestrowano pomyslnie!", Toast.LENGTH_LONG).show ();
                        }
                    });
                }
            }
        });


    }
}