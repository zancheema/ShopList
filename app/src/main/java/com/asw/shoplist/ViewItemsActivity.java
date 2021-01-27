package com.asw.shoplist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewItemsActivity extends AppCompatActivity {
    public static final String SHOP_NAME = "shop_name";
    private String shopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        Intent intent = getIntent();
        shopName = intent.getStringExtra(SHOP_NAME);

        RecyclerView shopItemsList = findViewById(R.id.shopItemsList);
        ShopItemsListAdapter adapter = new ShopItemsListAdapter();
        shopItemsList.setAdapter(adapter);
        adapter.submitList(getShopItems(shopName));
    }

    private List<ShopItem> getShopItems(String shopName) {
        DbHelper helper = new DbHelper(getBaseContext());
        Cursor cursor = helper.getShopData(shopName);
        List<ShopItem> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("item_name"));
            double price = cursor.getDouble(cursor.getColumnIndex("item_price"));
            long createDate = cursor.getLong(cursor.getColumnIndex("creation_time"));
            items.add(new ShopItem(shopName, name, price, LocalDate.ofEpochDay(createDate)));
        }

        return items;
    }
}