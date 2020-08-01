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


public class Login_page7Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText etUsername, etPassword;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page7);

        mAuth = FirebaseAuth.getInstance();

        etUsername = findViewById(R.id.et_Username);
        etPassword = findViewById(R.id.et_Password);
        progressBar = findViewById(R.id.progressBar);

        TextView btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRegister = new Intent(Login_page7Activity.this, Daftar_1.class);
                startActivity(goRegister);
                finish();
            }
        });

        LinearLayout btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.equals("")){
                    Toast.makeText(Login_page7Activity.this, "Silahkan input username", Toast.LENGTH_SHORT).show();

                }else if (password.equals("")){
                    Toast.makeText(Login_page7Activity.this, "Silahkan input password", Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(Login_page7Activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                         FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Login_page7Activity.this, "Authentication succses.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Shop.class));
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login_page7Activity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
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
