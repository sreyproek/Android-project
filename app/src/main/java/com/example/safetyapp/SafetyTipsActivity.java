package com.example.safetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SafetyTipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_tips);

        // Setup back button
        findViewById(R.id.backButton).setOnClickListener(v -> finish());

        // Setup tip click listeners
        setupTipClickListeners();
    }

    private void setupTipClickListeners() {
        // Tip 1
        findViewById(R.id.tip1).setOnClickListener(v ->
                openTipDetail(1, "Lock Doors & Windows",
                        "Always lock all doors and windows when leaving home or going to sleep. This is your first line of defense against intruders.\n\n• Lock main doors with deadbolts\n• Secure sliding doors with bars\n• Install window locks on all windows\n• Don't forget garage and basement access",
                        "🔒")
        );

        // Tip 2
        findViewById(R.id.tip2).setOnClickListener(v ->
                openTipDetail(2, "Walk in Well-Lit Areas",
                        "Stick to well-lit streets and avoid shortcuts through alleys or parks at night.\n\n• Plan your route in advance\n• Stay on main roads\n• Avoid isolated areas\n• Be aware of your surroundings",
                        "🚶‍♀️")
        );

        // Tip 3
        findViewById(R.id.tip3).setOnClickListener(v ->
                openTipDetail(3, "Keep Phone Charged",
                        "Always keep your phone charged and carry a power bank in emergencies.\n\n• Charge phone overnight\n• Carry portable charger\n• Enable battery saving mode\n• Save emergency numbers offline",
                        "📱")
        );

        // Tip 4
        findViewById(R.id.tip4).setOnClickListener(v ->
                openTipDetail(4, "Share Your Location",
                        "Let someone know where you're going and when you expect to return.\n\n• Share live location with family\n• Set check-in times\n• Update if plans change\n• Use safety apps with location sharing",
                        "👥")
        );

        // Tip 5
        findViewById(R.id.tip5).setOnClickListener(v ->
                openTipDetail(5, "Check Your Car",
                        "Before entering your car, check the back seat and around the vehicle.\n\n• Check under car and around\n• Look in back seat\n• Have keys ready\n• Lock doors immediately after entering",
                        "🚗")
        );

        // Tip 6
        findViewById(R.id.tip6).setOnClickListener(v ->
                openTipDetail(6, "Keys Ready",
                        "Have your keys ready before reaching your door or car to avoid fumbling.\n\n• Hold keys between fingers\n• Don't search in bag at door\n• Have separate car and house keys\n• Keep spare keys secure",
                        "🔑")
        );

        // Tip 7
        findViewById(R.id.tip7).setOnClickListener(v ->
                openTipDetail(7, "Avoid Shortcuts",
                        "Don't take shortcuts through dark areas, alleys, or construction sites.\n\n• Stick to familiar routes\n• Avoid poorly lit areas\n• Stay visible to others\n• Trust your instincts",
                        "🚫")
        );

        // Tip 8
        findViewById(R.id.tip8).setOnClickListener(v ->
                openTipDetail(8, "Emergency Contacts",
                        "Save emergency contacts with ICE prefix (In Case of Emergency).\n\n• Add ICE before contact names\n• Include local emergency numbers\n• Add family doctor number\n• Program quick dial shortcuts",
                        "📞")
        );
    }

    private void openTipDetail(int tipId, String title, String details, String emoji) {
        Intent intent = new Intent(this, TipDetailActivity.class);
        intent.putExtra("TIP_ID", tipId);
        intent.putExtra("TIP_TITLE", title);
        intent.putExtra("TIP_DETAILS", details);
        intent.putExtra("TIP_EMOJI", emoji);
        startActivity(intent);
    }
}