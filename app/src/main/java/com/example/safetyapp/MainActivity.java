package com.example.safetyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    // UI Components
    private Button btnSOS;
    private TextView tvStatusText;
    private View viewStatusDot;
    private TextView tvWelcome;
    private CardView btnTipsCard, btnLocationCard;
    private LinearLayout navTips, navSettings;

    // Logic Variables
    private boolean isSOSActive = false;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationHelper.createNotificationChannel(this);

        try {
            initViews();
        } catch (Exception e) {
            Toast.makeText(this, "Error finding View ID: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }

        // 2. Load Preferences
        preferences = getSharedPreferences("SafetyAppPrefs", MODE_PRIVATE);

        // --- NEW CODE START ---
        // Get the name saved during Login/Signup. Default to "User" if not found.
        String userName = preferences.getString("user_name", " ");

        // Update the text
        if (tvWelcome != null) {
            tvWelcome.setText("Hello, " + userName);
        }
        // --- NEW CODE END ---

        // 3. Setup Click Listeners
        setupClickListeners();
    }


    private void initViews() {
        // These IDs must match your activity_main.xml exactly
        btnSOS = findViewById(R.id.btnSOS);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvStatusText = findViewById(R.id.tvStatusText);
        viewStatusDot = findViewById(R.id.viewStatusDot);
        btnLocationCard = findViewById(R.id.btnLocation);
        btnTipsCard = findViewById(R.id.btnTips);
        navTips = findViewById(R.id.nav_safety_tips);
        navSettings = findViewById(R.id.nav_settings);
    }

    private void setupClickListeners() {
        // --- SOS BUTTON ---
        btnSOS.setOnClickListener(v -> {
            if (!isSOSActive) {
                activateSOS();
            } else {
                deactivateSOS();
            }
        });

        // --- LOCATION CARD ---
        btnLocationCard.setOnClickListener(v -> {
            startActivity(new Intent(this, LocationActivity.class));
        });

        // --- TIPS CARD & BOTTOM NAV TIPS ---
        View.OnClickListener tipsListener = v -> startActivity(new Intent(this, SafetyTipsActivity.class));
        btnTipsCard.setOnClickListener(tipsListener);
        navTips.setOnClickListener(tipsListener);

        // --- BOTTOM NAV SETTINGS ---
        navSettings.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });
    }

    private void activateSOS() {
        isSOSActive = true;

        // 1. Visual Changes
        btnSOS.setText("STOP");
        NotificationHelper.sendNotification(this, "🚨 SOS ACTIVE", "Emergency contacts are being notified!");
        btnSOS.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        if (tvStatusText != null) {
            tvStatusText.setText("SOS Active");
            tvStatusText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        }
        if (viewStatusDot != null) {
            viewStatusDot.setBackgroundResource(R.drawable.rounded_green_dot); // Or red if you have a red drawable
        }

        // 2. Vibrate
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (vibrator != null) {
            // Vibrate for 500 milliseconds
            vibrator.vibrate(500);
        }

        // 3. Get Saved Emergency Number
        String number = preferences.getString("emergency_number", "911");
        if (number.isEmpty()) {
            number = "911";
        }

        // 4. Open Phone Dialer (Safe method, no permissions needed)
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + number));
        startActivity(callIntent);

        Toast.makeText(this, "🚨 SOS ACTIVATED - Opening Dialer", Toast.LENGTH_LONG).show();
    }

    private void deactivateSOS() {
        isSOSActive = false;

        // Reset Visuals
        btnSOS.setText("SOS");
        // Reset to original background drawable
        btnSOS.setBackgroundResource(R.drawable.btn_sos_background);

        if (tvStatusText != null) {
            tvStatusText.setText("Ready");
            tvStatusText.setTextColor(ContextCompat.getColor(this, R.color.black)); // Or your default green color
        }

        Toast.makeText(this, "SOS Deactivated", Toast.LENGTH_SHORT).show();
    }
}
