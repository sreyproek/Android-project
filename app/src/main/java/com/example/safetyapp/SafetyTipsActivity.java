package com.example.safetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout; // Or CardView, depending on your layout
import androidx.appcompat.app.AppCompatActivity;

public class SafetyTipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_tips);

        // --- SETUP NAVIGATION / BACK BUTTON ---
        // Assuming you have a back button or image
        View backBtn = findViewById(R.id.backButton); // Check this ID in your XML
        if (backBtn != null) {
            backBtn.setOnClickListener(v -> finish());
        }

        // --- SETUP CLICK LISTENERS FOR THE TIPS ---

        // 1. EMERGENCY CONTACTS
        // Check your XML: is the ID "cardEmergency", "btnEmergency", or something else?
        View btnEmergency = findViewById(R.id.cardEmergency);
        if (btnEmergency != null) {
            btnEmergency.setOnClickListener(v -> openDetail("Emergency Contacts"));
        }

        // 2. LOCATION SHARING
        View btnLocation = findViewById(R.id.cardLocation);
        if (btnLocation != null) {
            btnLocation.setOnClickListener(v -> openDetail("Location Sharing"));
        }

        // 3. NIGHT SAFETY
        View btnNight = findViewById(R.id.cardNightSafety);
        if (btnNight != null) {
            btnNight.setOnClickListener(v -> openDetail("Night Safety"));
        }

        // 4. SELF DEFENSE
        View btnDefense = findViewById(R.id.cardSelfDefense);
        if (btnDefense != null) {
            btnDefense.setOnClickListener(v -> openDetail("Self Defense"));
        }

        // 5. CYBER SAFETY
        View btnCyber = findViewById(R.id.cardCyberSafety);
        if (btnCyber != null) {
            btnCyber.setOnClickListener(v -> openDetail("Cyber Safety"));
        }
    }

    // Helper function to open the detail page with the correct data
    private void openDetail(String title) {
        Intent intent = new Intent(SafetyTipsActivity.this, TipDetailActivity.class);

        // THIS IS THE CRITICAL LINE THAT WAS MISSING OR WRONG
        intent.putExtra("TIP_TITLE", title);

        startActivity(intent);
    }
}
