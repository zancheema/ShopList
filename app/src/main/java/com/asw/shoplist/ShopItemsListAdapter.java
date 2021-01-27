package com.asw.shoplist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ShopItemsListAdapter extends ListAdapter<ShopItem, ShopItemsListAdapter.ViewHolder> {
    public ShopItemsListAdapter() {
        super(new ShopItemsDiffUtil());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(ShopItem shop) {
            TextView name = itemView.findViewById(R.id.itemName);
            TextView price = itemView.findViewById(R.id.itemPrice);
            TextView date = itemView.findViewById(R.id.itemDate);
            price.setText("€ " + shop.getItemPrice());
            name.setText(shop.getItemName());
            date.setText("Dated: " + shop.getDate());
        }

        public static ShopItemsListAdapter.ViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.items_list_item, parent, false);
            return new ShopItemsListAdapter.ViewHolder(itemView);
        }
    }

    static class ShopItemsDiffUtil extends DiffUtil.ItemCallback<ShopItem> {
        @Override
        public boolean areItemsTheSame(@NonNull ShopItem oldItem, @NonNull ShopItem newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShopItem oldItem, @NonNull ShopItem newItem) {
            return oldItem.equals(newItem);
        }
    }
}
