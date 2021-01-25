package com.asw.shoplist;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Double> shopsMap = new HashMap<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("shop_name"));
            double price = Double.parseDouble(cursor.getString(cursor.getColumnIndex("item_price")));
            Double previousPrice = shopsMap.get(name);
            if (previousPrice == null) previousPrice = 0.0;
            shopsMap.put(name, previousPrice + price);
        }

        List<Shop> shops = new ArrayList<>();
        for (String name : shopsMap.keySet()) {
            shops.add(new Shop(name, shopsMap.get(name)));
        }
        cursor.close();
        return shops;
    }
}