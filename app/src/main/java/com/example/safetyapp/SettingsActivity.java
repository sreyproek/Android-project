package com.example.safetyapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private EditText emergencyNumberEditText;
    private SharedPreferences preferences;
    private static final String PREFS_NAME = "SafetyAppPrefs";
    private static final String KEY_EMERGENCY_NUMBER = "emergency_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize views
        emergencyNumberEditText = findViewById(R.id.emergencyNumberEditText);
        Button saveButton = findViewById(R.id.saveButton);

        // Initialize SharedPreferences
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Load saved emergency number
        loadSavedSettings();

        // Save button click listener
        saveButton.setOnClickListener(v -> saveSettings());

        // Back button click listener (top arrow)
        findViewById(R.id.backButton).setOnClickListener(v -> finish());
    }

    private void loadSavedSettings() {
        String savedNumber = preferences.getString(KEY_EMERGENCY_NUMBER, "112");
        emergencyNumberEditText.setText(savedNumber);
    }

    private void saveSettings() {
        String number = emergencyNumberEditText.getText().toString().trim();

        if (number.isEmpty()) {
            Toast.makeText(this, "Please enter emergency number", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save to SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_EMERGENCY_NUMBER, number);
        editor.apply();

        Toast.makeText(this, "Settings saved successfully!", Toast.LENGTH_SHORT).show();
    }
}