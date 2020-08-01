package com.example.paceshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Daftar_1 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etUsername, etEmail, etPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_1);

        mAuth = FirebaseAuth.getInstance();

        etUsername =findViewById(R.id.et_Username);
        etEmail =findViewById(R.id.et_Email);
        etPassword =findViewById(R.id.et_Password);
        progressBar = findViewById(R.id.progressBar);


        TextView btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goLogin = new Intent(Daftar_1.this, Login_page7Activity.class);
                startActivity(goLogin);
                finish();
            }
        });

        LinearLayout fabRegister = findViewById(R.id.fab_register);
        fabRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etUsername.getText().toString();

                if (email.equals("")){
                    Toast.makeText(Daftar_1.this, "Silahkan isi email Anda",
                            Toast.LENGTH_SHORT).show();
                }else if (password.equals("")) {
                    Toast.makeText(Daftar_1.this, "Silahkan isi password Anda",
                            Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Daftar_1.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        Toast.makeText(Daftar_1.this, "Anda Berhasil Terdafatar", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Shop.class));

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Daftar_1.this, "Anda Belum Terdafatar", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                    // ...
                                }
                            });
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}