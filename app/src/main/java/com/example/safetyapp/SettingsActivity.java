package com.example.safetyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private EditText etEmergencyNumber;
    private Button btnSave, btnLogout;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize Views
        etEmergencyNumber = findViewById(R.id.etEmergencyNumber);
        btnSave = findViewById(R.id.btnSave);
        btnLogout = findViewById(R.id.btnLogout);

        preferences = getSharedPreferences("SafetyAppPrefs", MODE_PRIVATE);

        // Load existing number
        String currentNumber = preferences.getString("emergency_number", "");
        etEmergencyNumber.setText(currentNumber);

        // Save Button Logic
        btnSave.setOnClickListener(v -> {
            String number = etEmergencyNumber.getText().toString().trim();
            if (TextUtils.isEmpty(number)) {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
            } else {
                preferences.edit().putString("emergency_number", number).apply();
                Toast.makeText(this, "Emergency Number Saved!", Toast.LENGTH_SHORT).show();
                finish(); // Go back to main screen
            }
        });

        // Logout Button Logic
        btnLogout.setOnClickListener(v -> {
            // Clear all data
            preferences.edit().clear().apply();

            // Return to Login Screen
            Intent intent = new Intent(this, LoginActivity.class);
            // Clear the back stack so user can't press back to return to the app
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
