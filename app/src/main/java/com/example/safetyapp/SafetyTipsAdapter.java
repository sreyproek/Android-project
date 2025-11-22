package com.example.safetyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SafetyTipsAdapter extends RecyclerView.Adapter<SafetyTipsAdapter.ViewHolder> {

    Context c;
    ArrayList<SafetyTipModel> list;

    public SafetyTipsAdapter(Context c, ArrayList<SafetyTipModel> list) {
        this.c = c;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_safety_tip, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int pos) {
        h.title.setText(list.get(pos).title);
        h.desc.setText(list.get(pos).desc);
        h.icon.setImageResource(list.get(pos).icon);
    }

    @Override
    public int getItemCount() { return list.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title, desc;

        public ViewHolder(View v) {
            super(v);
            icon = v.findViewById(R.id.tipIcon);
            title = v.findViewById(R.id.tipTitle);
            desc = v.findViewById(R.id.tipDescription);
        }
    }
}
