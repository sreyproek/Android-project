package com.example.safetyapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SafetyTipsAdapter extends RecyclerView.Adapter<SafetyTipsAdapter.ViewHolder> {

    private List<SafetyTip> tipsList;

    public SafetyTipsAdapter(List<SafetyTip> tipsList) {
        this.tipsList = tipsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_safety_tip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SafetyTip tip = tipsList.get(position);

        // Set emoji as text
        String emoji = getEmojiForPosition(position);
        holder.iconText.setText(emoji);
        holder.titleText.setText(tip.getTitle());
        holder.descText.setText(tip.getDescription());
    }

    private String getEmojiForPosition(int position) {
        String[] emojis = {
                "🔒", "🚶‍♀️", "📱", "👥", "🚗",
                "🔑", "🚫", "📞", "🎒", "🧭",
                "💡", "👀", "🗺️", "⏰", "🚨"
        };
        return emojis[position % emojis.length];
    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView iconText, titleText, descText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconText = itemView.findViewById(R.id.iconText);
            titleText = itemView.findViewById(R.id.titleText);
            descText = itemView.findViewById(R.id.descText);
        }
    }
}