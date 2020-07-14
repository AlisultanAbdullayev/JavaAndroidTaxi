package com.alijaver.mytaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverRegLoginActivity extends AppCompatActivity {

    private TextView driverStatus, question;
    private Button signInBtn, signUpBtn;
    private EditText emailET, passwordET;

    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_reg_login);

        driverStatus =  findViewById(R.id.statusDriver);
        question = findViewById(R.id.accountCreate);
        signInBtn = findViewById(R.id.signInDriver);
        signUpBtn = findViewById(R.id.signUpDriver);
        emailET = findViewById(R.id.driverEmail);
        passwordET = findViewById(R.id.driverPassword);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        signUpBtn.setVisibility(View.INVISIBLE);
        signUpBtn.setEnabled(false);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInBtn.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                signUpBtn.setVisibility(View.VISIBLE);
                signUpBtn.setEnabled(true);
                driverStatus.setText("Driver's registration page");
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                RegisterDriver(email, password);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                SignInDriver(email,password);
            }
        });

    }

    private void SignInDriver(String email, String password) {
        loadingBar.setTitle("Driver's log-in");
        loadingBar.setMessage("Please, wait a while ...");
        loadingBar.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(DriverRegLoginActivity.this,"Entered successfully :)", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent driverIntent = new Intent(DriverRegLoginActivity.this, DriversMapActivity.class);
                    startActivity(driverIntent);
                }
                else {
                    Toast.makeText(DriverRegLoginActivity.this, "Something's gone wrong, try again :(", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent driverIntent = new Intent(DriverRegLoginActivity.this, DriversMapActivity.class);
                    startActivity(driverIntent);
                }
            }
        });
    }

    private void RegisterDriver(String email, String password) {
        loadingBar.setTitle("Driver's registration");
        loadingBar.setMessage("Please, wait a while ...");
        loadingBar.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(DriverRegLoginActivity.this,"Registration was successfully :)", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
                else {
                    Toast.makeText(DriverRegLoginActivity.this, "Something's gone wrong :(", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }
}