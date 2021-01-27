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
    private final OnClickItemListener listener;

    public ShopsListAdapter(OnClickItemListener listener) {
        super(new ShopItemsDiffUtil());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.from(parent, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private final OnClickItemListener listener;

        private ViewHolder(View itemView, OnClickItemListener listener) {
            super(itemView);
            this.listener = listener;
        }

        public void bind(Shop shop) {
            TextView price = itemView.findViewById(R.id.totalItemsPrice);
            TextView name = itemView.findViewById(R.id.shopName);
            TextView date = itemView.findViewById(R.id.shopDate);

            // set data
            price.setText("€ " + shop.getTotalPrice());
            name.setText(shop.getName());
            date.setText("Dated: " + shop.getDate());

            // set listeners
            name.setOnClickListener((v) -> listener.onClickName(shop.getName()));
        }

        public static ViewHolder from(ViewGroup parent, OnClickItemListener listener) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.shop_list_item, parent, false);
            return new ViewHolder(itemView, listener);
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

    public interface OnClickItemListener {
        void onClickName(String name);
    }
}
