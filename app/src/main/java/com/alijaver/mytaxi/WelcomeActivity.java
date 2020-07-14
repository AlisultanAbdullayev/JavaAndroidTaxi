package com.alijaver.mytaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button driverBtn;
    private Button customerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        driverBtn = findViewById(R.id.driverBtn);
        customerBtn = findViewById(R.id.customerBtn);

        driverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent driverIntent = new Intent(WelcomeActivity.this, DriverRegLoginActivity.class);
                startActivity(driverIntent);
            }
        });

        customerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerIntent = new Intent(WelcomeActivity.this, CustomerRegLoginActivity.class);
                startActivity(customerIntent);
            }
        });

    }
}