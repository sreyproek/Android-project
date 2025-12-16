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

        // 1. Initialize Views using the IDs from your XML
        TextView tipNumberView = findViewById(R.id.tipNumberTextView);
        TextView emojiView = findViewById(R.id.emojiTextView);
        TextView titleView = findViewById(R.id.titleTextView);
        TextView detailsView = findViewById(R.id.detailsTextView);
        LinearLayout backButton = findViewById(R.id.backButton);

        // 2. Setup Back Button functionality
        backButton.setOnClickListener(v -> finish());

        // 3. Receive data from the previous activity
        // We look for a "TIP_TITLE" string. If it's missing, we default to "General".
        String tipTitle = getIntent().getStringExtra("TIP_TITLE");

        if (tipTitle == null) {
            tipTitle = "General Safety"; // Default fallback
        }

        // 4. Update the UI based on the specific tip
        loadTipData(tipTitle, tipNumberView, emojiView, titleView, detailsView);
    }

    private void loadTipData(String key, TextView numberView, TextView emojiView, TextView titleView, TextView detailsView) {

        // This switch statement sets the text and emoji based on the title passed
        switch (key) {
            case "Emergency Contacts":
                numberView.setText("Tip #1");
                emojiView.setText("📞");
                titleView.setText("Emergency Contacts");
                detailsView.setText(
                        "• Keep a list of emergency numbers saved in your phone and written down in your wallet.\n\n" +
                                "• Include: Local Police (100/112), Ambulance, Fire Department, and a trusted family member.\n\n" +
                                "• Set up 'Emergency SOS' on your smartphone settings to quickly alert contacts."
                );
                break;

            case "Location Sharing":
                numberView.setText("Tip #2");
                emojiView.setText("📍");
                titleView.setText("Live Location");
                detailsView.setText(
                        "• When traveling alone, share your live location via WhatsApp or Google Maps with a trusted friend.\n\n" +
                                "• If you feel unsafe in a taxi, take a picture of the license plate and send it to someone.\n\n" +
                                "• Keep your GPS on while traveling in unknown areas."
                );
                break;

            case "Night Safety":
                numberView.setText("Tip #3");
                emojiView.setText("🌙");
                titleView.setText("Staying Safe at Night");
                detailsView.setText(
                        "• Avoid poorly lit or deserted areas.\n\n" +
                                "• Walk confidently and stay off your phone to remain aware of your surroundings.\n\n" +
                                "• If you think you are being followed, head to a crowded public place immediately (like a shop or gas station)."
                );
                break;

            case "Self Defense":
                numberView.setText("Tip #4");
                emojiView.setText("🥋");
                titleView.setText("Basic Self Defense");
                detailsView.setText(
                        "• Your voice is a weapon. Shout 'STOP' or 'FIRE' loudly to attract attention.\n\n" +
                                "• Carry a safety whistle or pepper spray if legal in your area.\n\n" +
                                "• Aim for vulnerable areas if attacked: eyes, nose, throat, or groin."
                );
                break;

            case "Cyber Safety":
                numberView.setText("Tip #5");
                emojiView.setText("💻");
                titleView.setText("Cyber Safety");
                detailsView.setText(
                        "• Never share your home address or real-time location on public social media posts.\n\n" +
                                "• Use strong, unique passwords for your accounts.\n\n" +
                                "• Be cautious of meeting people from online dating apps; always meet in public first."
                );
                break;

            default:
                // Fallback for any tip not listed above
                numberView.setText("Tip Info");
                emojiView.setText("🛡️");
                titleView.setText(key);
                detailsView.setText("Stay alert, stay safe, and always trust your instincts. Contact authorities if you feel threatened.");
                break;
        }
    }
}
