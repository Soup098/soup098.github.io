package com.zybooks.cs_360_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<Item> items;
    private DatabaseHelper dbHelper;

    private OnDeleteClickListener deleteListener;

    public interface OnDeleteClickListener {
        void onDelete(Item item);
    }

    // Updated constructor: now accepts the delete listener again
    public ItemAdapter(ArrayList<Item> items, DatabaseHelper dbHelper, OnDeleteClickListener deleteListener) {
        this.items = items;
        this.dbHelper = dbHelper;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);

        holder.itemName.setText(item.getName());
        holder.itemQuantity.setText(String.valueOf(item.getQuantity()));

        // DELETE BUTTON
        holder.deleteBtn.setOnClickListener(view -> deleteListener.onDelete(item));

        // INCREASE BUTTON
        holder.increaseBtn.setOnClickListener(view -> {
            int newQty = item.getQuantity() + 1;
            item.setQuantity(newQty);

            dbHelper.updateQuantity(item.getId(), newQty); // <-- update DB
            notifyItemChanged(position);
        });

        // DECREASE BUTTON
        holder.decreaseBtn.setOnClickListener(v -> {
            if (item.getQuantity() > 0) {
                int newQty = item.getQuantity() - 1;
                item.setQuantity(newQty);

                dbHelper.updateQuantity(item.getId(), newQty); // <-- update DB
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView itemQuantity;
        Button deleteBtn;
        Button increaseBtn;
        Button decreaseBtn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            increaseBtn = itemView.findViewById(R.id.increaseBtn);
            decreaseBtn = itemView.findViewById(R.id.decreaseBtn);
        }
    }
}
