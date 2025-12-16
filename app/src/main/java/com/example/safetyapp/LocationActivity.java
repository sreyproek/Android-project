package com.example.safetyapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LocationActivity extends AppCompatActivity {

    private TextView tvLatitude, tvLongitude, tvAddress;
    private String lastKnownLocationUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Init Views
        tvLatitude = findViewById(R.id.tvLatitude);
        tvLongitude = findViewById(R.id.tvLongitude);
        tvAddress = findViewById(R.id.tvAddress);

        // Back Button
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Share Button
        findViewById(R.id.btnShareLocation).setOnClickListener(v -> {
            if (!lastKnownLocationUrl.isEmpty()) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "🚨 My current location: " + lastKnownLocationUrl);
                startActivity(Intent.createChooser(shareIntent, "Share Location"));
            } else {
                Toast.makeText(this, "Wait, fetching location...", Toast.LENGTH_SHORT).show();
            }
        });

        // Refresh Button
        findViewById(R.id.btnRefreshLocation).setOnClickListener(v -> getCurrentLocation());

        // Start
        getCurrentLocation();
    }

    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            tvAddress.setText("Permission Needed");
            return;
        }

        tvAddress.setText("Locating...");
        Location location = null;
        try {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            if (location == null && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (location != null) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();

                tvLatitude.setText(String.format("Lat: %.4f", lat));
                tvLongitude.setText(String.format("Lng: %.4f", lng));
                tvAddress.setText("Location Found");

                lastKnownLocationUrl = "https://maps.google.com/maps?q=" + lat + "," + lng;
            } else {
                tvAddress.setText("GPS Signal Weak");
            }
        } catch (Exception e) {
            tvAddress.setText("Error finding location");
        }
    }
}
