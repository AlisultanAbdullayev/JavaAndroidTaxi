package com.alijaver.mytaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerRegLoginActivity extends AppCompatActivity {

    private TextView customerStatus, question;
    private Button signInBtn, signUpBtn;
    private EditText emailET, passwordET;

    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reg_login);

        customerStatus = findViewById(R.id.statusCustomer);
        question = findViewById(R.id.accountCreateCustomer);
        signInBtn = findViewById(R.id.signInCustomer);
        signUpBtn = findViewById(R.id.signUpCustomer);
        emailET = findViewById(R.id.customerEmail);
        passwordET = findViewById(R.id.customerPassword);

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
                customerStatus.setText("Customer's registration page");
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                RegisterCustomer(email, password);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                SignInCustomer(email, password);
            }
        });
    }


    private void SignInCustomer(String email, String password) {
        loadingBar.setTitle("Customer's log-in");
        loadingBar.setMessage("Please, wait a while ...");
        loadingBar.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CustomerRegLoginActivity.this,"Entered successfully :)", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
                else {
                    Toast.makeText(CustomerRegLoginActivity.this, "Something's gone wrong, try again :(", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }


        private void RegisterCustomer (String email, String password){
            loadingBar.setTitle("Customer's registration");
            loadingBar.setMessage("Please, wait a while ...");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(CustomerRegLoginActivity.this, "Registration was successfully :)", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    } else {
                        Toast.makeText(CustomerRegLoginActivity.this, "Something's gone wrong :(", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
    }
}