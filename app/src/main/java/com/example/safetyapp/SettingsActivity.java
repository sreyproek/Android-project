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

        // Back to Home
        btnBack.setOnClickListener(v -> finish());

        // Emergency Contacts
        findViewById(R.id.cardEmergencyContacts).setOnClickListener(v -> {
            Toast.makeText(this, "Open Emergency Contacts Settings", Toast.LENGTH_SHORT).show();
        });

        // SOS Message
        findViewById(R.id.cardSOSMessage).setOnClickListener(v -> {
            Toast.makeText(this, "Customize your SOS Message", Toast.LENGTH_SHORT).show();
        });

        // Notifications
        findViewById(R.id.cardNotifications).setOnClickListener(v -> {
            Toast.makeText(this, "Notification Preferences", Toast.LENGTH_SHORT).show();
        });

        // Help & Support
        findViewById(R.id.cardHelpSupport).setOnClickListener(v -> {
            Toast.makeText(this, "Help & Support Center", Toast.LENGTH_SHORT).show();
        });

        // About
        findViewById(R.id.cardAbout).setOnClickListener(v -> {
            Toast.makeText(this, "About this Application", Toast.LENGTH_SHORT).show();
        });

        // Logout
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }

    private void setupSwitchListeners() {

        // Location Sharing Switch
        switchLocation.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Location Sharing ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Location Sharing OFF", Toast.LENGTH_SHORT).show();
            }
        });

        // Dark Mode Switch
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
                // (Optional) Add real dark mode logic later
            } else {
                Toast.makeText(this, "Dark Mode Disabled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
