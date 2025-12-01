package com.example.safetyapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SafetyTipsAdapter extends RecyclerView.Adapter<SafetyTipsAdapter.ViewHolder> {

    Context context;
    ArrayList<SafetyTipModel> list;

    public SafetyTipsAdapter(Context context, ArrayList<SafetyTipModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_safety_tip, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SafetyTipModel item = list.get(position);

        holder.image.setImageResource(item.getImage());
        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, TipDetailActivity.class);
            i.putExtra("title", item.getTitle());
            i.putExtra("description", item.getDescription());
            i.putExtra("image", item.getImage());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.tipImage);
            title = itemView.findViewById(R.id.tipTitle);
            desc = itemView.findViewById(R.id.tipDesc);
        }
    }
}
