package com.example.safetyapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    // UI
    private Button btnSOS;
    private LinearLayout btnCall, btnLocation;

    // State
    private boolean isSOSActive = false;

    // Permissions
    private final String[] permissions = {
            Manifest.permission.SEND_SMS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    // Emergency data
    private final String emergencyNumber = "117";          // Police
    private final String emergencyContactSMS = "012345678"; // Your emergency contact

    ActivityResultLauncher<String[]> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI
        btnSOS = findViewById(R.id.btnSOS);
        btnCall = findViewById(R.id.btnCall);
        btnLocation = findViewById(R.id.btnLocation);

        // Permission launcher
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> Toast.makeText(this, "Permissions Granted", Toast.LENGTH_SHORT).show()
        );

        setupClickListeners();
        setupBottomNavigation();
    }

    // ============================================================
    // CLICK HANDLERS
    // ============================================================
    private void setupClickListeners() {

        // SOS Button - Activate real SOS
        btnSOS.setOnClickListener(v -> {
            if (isSOSActive) {
                deactivateSOS();
            } else {
                checkPermissionsAndRun(() -> activateSOS());
            }
        });

        // INSTANT CALL
        btnCall.setOnClickListener(v ->
                checkPermissionsAndRun(() -> startEmergencyCall())
        );

        // LIVE LOCATION SHARE
        btnLocation.setOnClickListener(v ->
                checkPermissionsAndRun(() -> sendLiveLocation())
        );
    }

    // ============================================================
    // NAVIGATION
    // ============================================================
    private void setupBottomNavigation() {

        LinearLayout navTips = findViewById(R.id.nav_safety_tips);
        LinearLayout navSettings = findViewById(R.id.nav_settings);

        if (navTips != null) {
            navTips.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, SafetyTipsActivity.class))
            );
        }

        if (navSettings != null) {
            navSettings.setOnClickListener(v ->
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class))
            );
        }
    }

    // ============================================================
    // PERMISSIONS
    // ============================================================
    private void checkPermissionsAndRun(Runnable action) {
        boolean allowed = true;

        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED)
                allowed = false;
        }

        if (allowed) {
            action.run();
        } else {
            permissionLauncher.launch(permissions);
        }
    }

    // ============================================================
    // SOS FUNCTIONALITY
    // ============================================================
    private void activateSOS() {
        isSOSActive = true;
        btnSOS.setText("ACTIVE");
        btnSOS.setBackgroundColor(0xFF475569); // dark gray

        vibratePhone();
        sendSOSMessage();
        startEmergencyCall();

        Toast.makeText(this, "🚨 SOS Activated!", Toast.LENGTH_LONG).show();
    }

    private void deactivateSOS() {
        isSOSActive = false;
        btnSOS.setText("SOS");
        btnSOS.setBackgroundColor(0xFFEC4899); // Pink

        Toast.makeText(this, "SOS Deactivated", Toast.LENGTH_SHORT).show();
    }

    private void vibratePhone() {
        try {
            Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            if (v != null) v.vibrate(400);
        } catch (Exception ignored) {}
    }

    private void sendSOSMessage() {
        String location = getLocationText();

        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(
                    emergencyContactSMS,
                    null,
                    "🚨 SOS! I need help!\n" + location,
                    null,
                    null
            );
            Toast.makeText(this, "📩 SOS SMS Sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "❌ SMS Failed", Toast.LENGTH_LONG).show();
        }
    }

    private void startEmergencyCall() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + emergencyNumber));
            startActivity(callIntent);
        } catch (Exception e) {
            Toast.makeText(this, "❌ Call failed", Toast.LENGTH_SHORT).show();
        }
    }

    // ============================================================
    // LIVE LOCATION
    // ============================================================
    private void sendLiveLocation() {
        String location = getLocationText();

        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(
                    emergencyContactSMS,
                    null,
                    "📍 My Live Location:\n" + location,
                    null,
                    null
            );
            Toast.makeText(this, "📩 Location Sent!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "❌ Failed to send location", Toast.LENGTH_LONG).show();
        }
    }

    // ============================================================
    // LOCATION HANDLER
    // ============================================================
    @SuppressLint("MissingPermission")
    private String getLocationText() {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = null;

        try {
            if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            if (location == null) {
                location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        } catch (Exception ignored) {}

        if (location != null) {
            return "https://maps.google.com/?q=" +
                    location.getLatitude() + "," + location.getLongitude();
        }

        return "⚠ GPS Off / Location unavailable";
    }
}
