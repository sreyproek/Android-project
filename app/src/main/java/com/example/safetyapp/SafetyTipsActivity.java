package com.example.safetyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SafetyTipsActivity extends AppCompatActivity {

    private EditText etSearch;
    private LinearLayout btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_tips);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        etSearch = findViewById(R.id.etSearch);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setupClickListeners() {
        // Back button
        btnBack.setOnClickListener(v -> {
            finish(); // Go back to main activity
        });

        // Best practice cards
        findViewById(R.id.cardSituationalAwareness).setOnClickListener(v -> {
            Toast.makeText(this, "Situational Awareness Tips", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.cardDigitalSafety).setOnClickListener(v -> {
            Toast.makeText(this, "Digital Safety Best Practices", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.cardSafeTravel).setOnClickListener(v -> {
            Toast.makeText(this, "Safe Travel Checklist", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.cardLocalResources).setOnClickListener(v -> {
            Toast.makeText(this, "Local Safety Resources", Toast.LENGTH_SHORT).show();
        });
    }
}