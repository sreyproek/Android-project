package com.example.safetyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvGoToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if user is already logged in
        SharedPreferences prefs = getSharedPreferences("SafetyAppPrefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("is_logged_in", false);
        if (isLoggedIn) {
            startActivity(new Intent(this, MainActivity.class));
            finish(); // Close login screen
            return;
        }

        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoToSignUp = findViewById(R.id.tvGoToSignUp);

        // Handle Login Button
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Call the real API now
                performLogin(email, password);
            }
        });;

        // Handle Navigation to Sign Up
        tvGoToSignUp.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
    }

    private void performLogin(String email, String password) {
        // TEMPORARY TESTING: Simulate a successful API response
        // Remove these 3 lines when your real server is ready
        boolean isTestMode = true;

        if (isTestMode) {
            // --- SIMULATED SUCCESS START ---
            Toast.makeText(LoginActivity.this, "Test Login Successful!", Toast.LENGTH_SHORT).show();

            SharedPreferences prefs = getSharedPreferences("SafetyAppPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("is_logged_in", true);
            editor.putString("user_email", email);
            editor.putString("user_name", "Test User");
            editor.apply();

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
            // --- SIMULATED SUCCESS END ---
        }

        // ... The rest of your real Retrofit code stays below here ...
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        // ...
    }

}