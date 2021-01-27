package com.asw.shoplist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ShopsListAdapter extends ListAdapter<Shop, ShopsListAdapter.ViewHolder> {

    public ShopsListAdapter() {
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

        public void bind(Shop shop) {
            TextView price = itemView.findViewById(R.id.itemPrice);
            TextView name = itemView.findViewById(R.id.shopName);
            TextView date = itemView.findViewById(R.id.shopDate);
            price.setText("€ " + shop.getTotalPrice());
            name.setText(shop.getName());
            date.setText("Dated: " + shop.getDate());
        }

        public static ViewHolder from(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.shop_list_item, parent, false);
            return new ViewHolder(itemView);
        }
    }

    static class ShopItemsDiffUtil extends DiffUtil.ItemCallback<Shop> {
        @Override
        public boolean areItemsTheSame(@NonNull Shop oldItem, @NonNull Shop newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Shop oldItem, @NonNull Shop newItem) {
            return oldItem.equals(newItem);
        }
    }
}
