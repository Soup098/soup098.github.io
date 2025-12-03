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
    private OnDeleteClickListener deleteListener;

    public interface OnDeleteClickListener {
        void onDelete(Item item);
    }

    public ItemAdapter(ArrayList<Item> items, OnDeleteClickListener deleteListener) {
        this.items = items;
        this.deleteListener = deleteListener;
    }

    //Inflate the item layout and create the view holder
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    //bind data from items to each view holder
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);

        holder.itemName.setText(item.getName());
        holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
        holder.deleteBtn.setOnClickListener(view -> deleteListener.onDelete(item));

        holder.increaseBtn.setOnClickListener(view -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
        });

        holder.decreaseBtn.setOnClickListener(v -> {
            if (item.getQuantity() > 0) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
            }
        });

    }

    //get the number of items currently in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //viewholder class to hold onto references for each item view
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
