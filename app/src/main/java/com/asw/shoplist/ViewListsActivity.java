package com.asw.shoplist;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewListsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lists);

        RecyclerView shopsList = findViewById(R.id.shopsList);
        final ShopsListAdapter adapter = new ShopsListAdapter();
        final List<Shop> shops = getShops();
        adapter.submitList(shops);
        shopsList.setAdapter(adapter);
    }

    private List<Shop> getShops() {
        DbHelper helper = new DbHelper(getBaseContext());
        Cursor cursor = helper.getAllShops();
        List<Shop> shops = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("shop_name"));
            double price = Double.parseDouble(cursor.getString(cursor.getColumnIndex("item_price")));
            shops.add(new Shop(name , price));
        }
        return shops;
    }
}