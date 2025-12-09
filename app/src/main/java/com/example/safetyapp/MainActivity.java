package com.example.safetyapp;

import android.content.SharedPreferences;
import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnSOS;
    private LinearLayout btnTipsCard, btnLocationCard;
    private LinearLayout navTips, navSettings; // REMOVED: navAbout
    private boolean isSOSActive = false;
    private SharedPreferences preferences;
    private String emergencyNumber = "112"; // Default value

    private final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private ActivityResultLauncher<String[]> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        btnSOS = findViewById(R.id.btnSOS);
        btnTipsCard = findViewById(R.id.btnTips);
        btnLocationCard = findViewById(R.id.btnLocation);
        navTips = findViewById(R.id.nav_safety_tips);
        navSettings = findViewById(R.id.nav_settings);
        // REMOVED: navAbout = findViewById(R.id.nav_about);

        // Initialize SharedPreferences
        preferences = getSharedPreferences("SafetyAppPrefs", MODE_PRIVATE);

        // Load saved emergency number
        emergencyNumber = preferences.getString("emergency_number", "112");

        // Setup permission launcher
        setupPermissionLauncher();

        // Setup click listeners
        setupClickListeners();

        // Check permissions on start
        checkPermissions();
    }

    private void setupPermissionLauncher() {
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                permissions -> {
                    boolean allGranted = true;
                    for (Boolean isGranted : permissions.values()) {
                        if (!isGranted) {
                            allGranted = false;
                            break;
                        }
                    }

                    if (allGranted) {
                        Toast.makeText(this, "Permissions granted!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this,
                                "Some permissions denied. Emergency features may not work properly.",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void setupClickListeners() {
        // SOS Button
        btnSOS.setOnClickListener(v -> {
            if (!isSOSActive) {
                if (hasAllPermissions()) {
                    activateSOS();
                } else {
                    requestPermissions();
                    Toast.makeText(this, "Please grant permissions first", Toast.LENGTH_SHORT).show();
                }
            } else {
                deactivateSOS();
            }
        });

        // Safety Tips Card
        btnTipsCard.setOnClickListener(v ->
                startActivity(new Intent(this, SafetyTipsActivity.class))
        );

        // Location Card
        btnLocationCard.setOnClickListener(v -> {
            if (hasAllPermissions()) {
                String location = getLocationText();
                Toast.makeText(this, "Location: " + location, Toast.LENGTH_LONG).show();
            } else {
                requestPermissions();
                Toast.makeText(this, "Need location permission", Toast.LENGTH_SHORT).show();
            }
        });

        // Bottom Navigation
        navTips.setOnClickListener(v ->
                startActivity(new Intent(this, SafetyTipsActivity.class))
        );

        navSettings.setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class))
        );

        // REMOVED: About navigation click listener
    }

    // ... keep all other methods exactly as they were ...
    private boolean hasAllPermissions() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void checkPermissions() {
        if (!hasAllPermissions()) {
            requestPermissions();
        }
    }

    private void requestPermissions() {
        permissionLauncher.launch(REQUIRED_PERMISSIONS);
    }

    private void activateSOS() {
        isSOSActive = true;
        btnSOS.setText("CANCEL");

        // Create blinking animation
        ValueAnimator animator = ValueAnimator.ofFloat(0.6f, 1.0f);
        animator.setDuration(500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            btnSOS.setAlpha(value);
        });
        animator.start();

        // Store animator to stop later
        btnSOS.setTag(animator);

        // Call emergency and show location
        callEmergencyNumber();
        String location = getLocationText();

        Toast.makeText(this,
                "🚨 SOS ACTIVATED!\nEmergency services contacted.\n" + location,
                Toast.LENGTH_LONG).show();
    }

    private void deactivateSOS() {
        isSOSActive = false;
        btnSOS.setText("SOS");
        btnSOS.setAlpha(1.0f);

        // Stop animation
        Object animator = btnSOS.getTag();
        if (animator instanceof ValueAnimator) {
            ((ValueAnimator) animator).cancel();
        }

        Toast.makeText(this, "SOS deactivated", Toast.LENGTH_SHORT).show();
    }

    private void callEmergencyNumber() {
        try {
            // Get current number from preferences
            String currentNumber = preferences.getString("emergency_number", "112");

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + currentNumber));

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                startActivity(callIntent);
            } else {
                Toast.makeText(this, "Phone permission required", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Failed to make call: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getLocationText() {
        try {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            if (locationManager == null) {
                return "Location service not available";
            }

            // Check permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return "Location permission required";
            }

            Location location = null;

            // Try to get last known location from GPS
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

            // If GPS not available, try network
            if (location == null && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                return String.format("📍 Location:\nhttps://maps.google.com/?q=%.6f,%.6f",
                        latitude, longitude);
            } else {
                return "Location not available. Enable GPS.";
            }

        } catch (SecurityException e) {
            return "Location permission denied";
        } catch (Exception e) {
            return "Error getting location";
        }
    }
}