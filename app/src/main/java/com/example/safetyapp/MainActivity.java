package com.example.safetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnSOS;
    private LinearLayout btnCall, btnLocation;
    private boolean isSOSActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        btnSOS = findViewById(R.id.btnSOS);
        btnCall = findViewById(R.id.btnCall);
        btnLocation = findViewById(R.id.btnLocation);

        // Set up click listeners
        setupClickListeners();

        // Setup bottom navigation
        setupBottomNavigation();
    }

    private void setupClickListeners() {
        // SOS Button - Only changes UI and shows toast
        btnSOS.setOnClickListener(v -> {
            if (!isSOSActive) {
                activateSOS();
            } else {
                deactivateSOS();
            }
        });

        // Instant Call Card - Only shows toast
        btnCall.setOnClickListener(v -> {
            Toast.makeText(this, "Instant Call: Would contact emergency services", Toast.LENGTH_SHORT).show();
        });

        // Live Location Card - Only shows toast
        btnLocation.setOnClickListener(v -> {
            Toast.makeText(this, "Live Location: Sharing your location with contacts", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupBottomNavigation() {
        // Safety Tips navigation
        LinearLayout safetyTipsNav = findViewById(R.id.nav_safety_tips);
        if (safetyTipsNav != null) {
            safetyTipsNav.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SafetyTipsActivity.class);
                startActivity(intent);
            });
        }

        // Settings navigation
        LinearLayout settingsNav = findViewById(R.id.nav_settings);
        if (settingsNav != null) {
            settingsNav.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            });
        }
    }

    private void activateSOS() {
        isSOSActive = true;
        btnSOS.setText("ACTIVE");
        btnSOS.setBackgroundColor(0xFF475569); // Dark gray

        Toast.makeText(this, "SOS Activated! Emergency alert sent.", Toast.LENGTH_LONG).show();
    }

    private void deactivateSOS() {
        isSOSActive = false;
        btnSOS.setText("SOS");
        btnSOS.setBackgroundColor(0xFFEC4899); // Pink
        Toast.makeText(this, "SOS Deactivated", Toast.LENGTH_SHORT).show();
    }
}