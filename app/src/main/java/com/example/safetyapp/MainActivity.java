package com.example.safetyapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    Button btnSOS, btnCall, btnTips;

    String[] permissions = {
            Manifest.permission.SEND_SMS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    ActivityResultLauncher<String[]> permLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSOS = findViewById(R.id.btnSOS);
        btnCall = findViewById(R.id.btnCall);
        btnTips = findViewById(R.id.btnTips);

        permLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> Toast.makeText(this, "Permissions Updated", Toast.LENGTH_SHORT).show()
        );

        btnSOS.setOnClickListener(v -> checkPerm(() -> sendSOS()));
        btnCall.setOnClickListener(v -> checkPerm(() -> callNumber("117")));
        btnTips.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, SafetyTipsActivity.class))
        );
    }

    private void checkPerm(Runnable action) {
        boolean ok = true;
        for (String p : permissions) {
            if (ContextCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED)
                ok = false;
        }
        if (ok) action.run();
        else permLauncher.launch(permissions);
    }

    private void sendSOS() {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("0123456789", null,
                "SOS! I need help. (Test Message)",
                null, null);

        Toast.makeText(this, "SOS Message Sent!", Toast.LENGTH_LONG).show();
    }

    private void callNumber(String num) {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" + num));
        startActivity(i);
    }
}
