package com.example.safetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout btnBack;
    private SwitchCompat switchLocation, switchDarkMode;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();
        setupClickListeners();
        setupSwitchListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        switchLocation = findViewById(R.id.switchLocation);
        switchDarkMode = findViewById(R.id.switchDarkMode);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void setupClickListeners() {
        // Back button
        btnBack.setOnClickListener(v -> {
            finish(); // Go back to previous activity
        });

        // Emergency Contacts
        findViewById(R.id.cardEmergencyContacts).setOnClickListener(v -> {
            Toast.makeText(this, "Emergency Contacts Settings", Toast.LENGTH_SHORT).show();
        });

        // SOS Message
        findViewById(R.id.cardSOSMessage).setOnClickListener(v -> {
            Toast.makeText(this, "SOS Message Settings", Toast.LENGTH_SHORT).show();
        });

        // Notifications
        findViewById(R.id.cardNotifications).setOnClickListener(v -> {
            Toast.makeText(this, "Notification Settings", Toast.LENGTH_SHORT).show();
        });

        // Help & Support
        findViewById(R.id.cardHelpSupport).setOnClickListener(v -> {
            Toast.makeText(this, "Help & Support", Toast.LENGTH_SHORT).show();
        });

        // About
        findViewById(R.id.cardAbout).setOnClickListener(v -> {
            Toast.makeText(this, "About this App", Toast.LENGTH_SHORT).show();
        });

        // Log Out
        btnLogout.setOnClickListener(v -> {
            showLogoutConfirmation();
        });
    }

    private void setupSwitchListeners() {
        // Location Sharing Switch
        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SettingsActivity.this, "Location Sharing Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingsActivity.this, "Location Sharing Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Dark Mode Switch
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SettingsActivity.this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingsActivity.this, "Dark Mode Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showLogoutConfirmation() {
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        // Go back to main activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}