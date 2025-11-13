package com.example.safetyapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnSOS, btnCall;

    // requiredPermissions
    private final String[] requiredPermissions = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    // ActivityResultLauncher handle the permission request
    private ActivityResultLauncher<String[]> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the buttons from XML
        btnSOS = findViewById(R.id.btnSOS);
        btnCall = findViewById(R.id.btnCall);

        // Register permission launcher
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> Toast.makeText(this, "Permissions updated", Toast.LENGTH_SHORT).show()
        );

        // Handle SOS button click
        btnSOS.setOnClickListener(v -> ensurePermissionsThenRun(this::performFakeSOSAction));

        // Handle Call button click
        btnCall.setOnClickListener(v -> ensurePermissionsThenRun(() -> makeEmergencyCall("117")));
    }

    // Check permissions, then run the action
    private void ensurePermissionsThenRun(Runnable action) {
        boolean allGranted = true;
        for (String permission : requiredPermissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (allGranted) {
            action.run();
        } else {
            permissionLauncher.launch(requiredPermissions);
            Toast.makeText(this, "Grant permissions and try again", Toast.LENGTH_LONG).show();
        }
    }

    // Send a test SOS SMS
    private void performFakeSOSAction() {
        try {
            String phoneNumber = "0123456789"; // 👈 Replace with your test number
            String message = "🚨 SOS! I need help. (Test message from Women Safety App)";
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "Test SOS sent!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    // Make a call to the emergency number
    private void makeEmergencyCall(String number) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        } catch (SecurityException e) {
            Toast.makeText(this, "Permission denied for CALL_PHONE", Toast.LENGTH_SHORT).show();
        }
    }
}
