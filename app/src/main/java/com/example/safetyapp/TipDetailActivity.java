package com.example.safetyapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TipDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);

        TextView title = findViewById(R.id.detailTitle);
        TextView description = findViewById(R.id.detailDescription);

        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));
    }
}
