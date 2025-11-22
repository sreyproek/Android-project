package com.example.safetyapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SafetyTipsActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<SafetyTipModel> list;
    SafetyTipsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_tips);

        rv = findViewById(R.id.recyclerTips);
        rv.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        list.add(new SafetyTipModel("Stay Alert", "Always observe surroundings.", R.drawable.ic_eye));
        list.add(new SafetyTipModel("Avoid Dark Areas", "Stay in bright, busy places.", R.drawable.ic_street_light));
        list.add(new SafetyTipModel("Share Location", "Share your live GPS location.", R.drawable.ic_location));
        list.add(new SafetyTipModel("Emergency Numbers", "Keep 117 or 118 ready.", R.drawable.ic_phone));

        adapter = new SafetyTipsAdapter(this, list);
        rv.setAdapter(adapter);
    }
}
