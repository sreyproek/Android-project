package com.example.safetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SafetyTipsActivity extends AppCompatActivity {

    EditText etSearch;

    LinearLayout cardSituationalAwareness,
            cardDigitalSafety,
            cardSafeTravel,
            cardLocalResources,
            btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_tips);

        // Initialize
        initViews();

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Card Click Listeners → open detail screen
        cardSituationalAwareness.setOnClickListener(v ->
                openDetail(
                        "Situational Awareness",
                        "Tips to stay alert and be aware of your surroundings. These practices help reduce risks in public spaces."
                )
        );

        cardDigitalSafety.setOnClickListener(v ->
                openDetail(
                        "Digital Safety Best Practices",
                        "Learn how to protect your online identity, secure your accounts, and browse safely."
                )
        );

        cardSafeTravel.setOnClickListener(v ->
                openDetail(
                        "Safe Travel Checklist",
                        "Important precautions for solo or group travel to ensure your journey stays safe."
                )
        );

        cardLocalResources.setOnClickListener(v ->
                openDetail(
                        "Local Safety Resources",
                        "Find nearby emergency services, support centers, and helpful community resources."
                )
        );

        // Search bar filtering
        setupSearchFilter();
    }

    private void initViews() {
        etSearch = findViewById(R.id.etSearch);

        btnBack = findViewById(R.id.btnBack);

        cardSituationalAwareness = findViewById(R.id.cardSituationalAwareness);
        cardDigitalSafety = findViewById(R.id.cardDigitalSafety);
        cardSafeTravel = findViewById(R.id.cardSafeTravel);
        cardLocalResources = findViewById(R.id.cardLocalResources);
    }

    // Opens the detail page with title + text
    private void openDetail(String title, String content) {
        Intent intent = new Intent(SafetyTipsActivity.this, TipDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("description", content);
        startActivity(intent);
    }

    // ----------------------------
// SEARCH BAR FILTER
// ----------------------------
    private void setupSearchFilter() {

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString().toLowerCase().trim();
                filterTips(keyword);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterTips(String keyword) {

        filterCard(cardSituationalAwareness, "situational awareness", keyword);
        filterCard(cardDigitalSafety, "digital safety", keyword);
        filterCard(cardSafeTravel, "safe travel", keyword);
        filterCard(cardLocalResources, "local resources", keyword);

        // Optional: If search is empty -> show all cards
        if (keyword.isEmpty()) {
            cardSituationalAwareness.setVisibility(LinearLayout.VISIBLE);
            cardDigitalSafety.setVisibility(LinearLayout.VISIBLE);
            cardSafeTravel.setVisibility(LinearLayout.VISIBLE);
            cardLocalResources.setVisibility(LinearLayout.VISIBLE);
        }
    }

    private void filterCard(LinearLayout card, String title, String keyword) {

        if (title.contains(keyword)) {
            card.setVisibility(LinearLayout.VISIBLE);
            card.setAlpha(1f); // fully visible
        } else {
            if (keyword.isEmpty()) {
                card.setVisibility(LinearLayout.VISIBLE); // show all when no search
                card.setAlpha(1f);
            } else {
                card.setVisibility(LinearLayout.GONE); // hide not matched items
            }
        }
    }

    private void handleVisibility(LinearLayout card, String text, String keyword) {
        if (text.contains(keyword)) {
            card.setAlpha(1f);
            card.setVisibility(LinearLayout.VISIBLE);
        } else {
            card.setAlpha(0.4f);
            card.setVisibility(keyword.isEmpty() ? LinearLayout.VISIBLE : LinearLayout.GONE);
        }
    }
}
