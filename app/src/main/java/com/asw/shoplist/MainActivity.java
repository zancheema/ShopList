package com.asw.shoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnAddList;
    private Button btnCompare;
    private Button btnViewLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Casting all the views with their ids
        btnAddList = findViewById(R.id.addList);
        btnCompare = findViewById(R.id.compare);
        btnViewLists = findViewById(R.id.viewLists);

        //setting onclick listener to add list button
        btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddListActivity.class));//Navigating user to AddList Activity
            }
        });

        //setting onclick listener to compare button
        btnCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CompareActivity.class));//Navigating user to Compare Activity
            }
        });

        // setting onclick listener to view shops
        btnViewLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewListsActivity.class));
            }
        });
    }
}