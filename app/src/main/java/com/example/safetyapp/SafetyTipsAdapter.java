package com.example.safetyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SafetyTipsAdapter extends RecyclerView.Adapter<SafetyTipsAdapter.ViewHolder> {

    private Context context;
    private List<SafetyTipModel> safetyTipsList;

    public SafetyTipsAdapter(Context context, ArrayList<SafetyTipModel> safetyTipsList) {
        this.context = context;
        this.safetyTipsList = safetyTipsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_safety_tip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SafetyTipModel tip = safetyTipsList.get(position);
        holder.bind(tip);
    }

    @Override
    public int getItemCount() {
        return safetyTipsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconImageView;
        private TextView titleTextView, descTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.tipIcon);
            titleTextView = itemView.findViewById(R.id.tipTitle);
            descTextView = itemView.findViewById(R.id.tipDescription);
        }

        public void bind(SafetyTipModel tip) {
            titleTextView.setText(tip.title);
            descTextView.setText(tip.desc);
            iconImageView.setImageResource(tip.icon);
        }
    }
}