package com.example.safetyapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TipDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);

        // Get data from intent
        String title = getIntent().getStringExtra("TIP_TITLE");
        String details = getIntent().getStringExtra("TIP_DETAILS");
        String emoji = getIntent().getStringExtra("TIP_EMOJI");
        int tipId = getIntent().getIntExtra("TIP_ID", 1);

        // Initialize views
        TextView emojiTextView = findViewById(R.id.emojiTextView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView detailsTextView = findViewById(R.id.detailsTextView);
        TextView tipNumberTextView = findViewById(R.id.tipNumberTextView);

        // Set data
        emojiTextView.setText(emoji);
        titleTextView.setText(title);
        detailsTextView.setText(details);
        tipNumberTextView.setText("Tip #" + tipId);

        // Set background color based on tip ID
        setTipColor(tipId);

        // Setup back button
        findViewById(R.id.backButton).setOnClickListener(v -> finish());
    }

    private void setTipColor(int tipId) {
        LinearLayout emojiBg = findViewById(R.id.emojiBackground);

        int[] colors = {
                R.color.tip_color_1, // Blue
                R.color.tip_color_2, // Green
                R.color.tip_color_3, // Orange
                R.color.tip_color_4, // Purple
                R.color.tip_color_5, // Indigo
                R.color.tip_color_6, // Yellow
                R.color.tip_color_7, // Red
                R.color.tip_color_8  // Cyan
        };

        int colorIndex = (tipId - 1) % colors.length;
        emojiBg.setBackgroundResource(colors[colorIndex]);
    }
}