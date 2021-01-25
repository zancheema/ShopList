package com.asw.shoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddListActivity extends AppCompatActivity {
    private Button btnAdd;
    private EditText edtShopName;
    private EditText edtItemName;
    private EditText edtItemPrice;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        //Setting Action Bar title
        getSupportActionBar().setTitle("Add List");

        //Casting all the views with their ids
        btnAdd = findViewById(R.id.btnAddList);
        edtShopName = findViewById(R.id.edtShopName);
        edtItemName = findViewById(R.id.edtItemName);
        edtItemPrice = findViewById(R.id.edtItemPrice);

        //initializing DbHelper object to handle database operations
        db = new DbHelper(this);


        //setting a click listener to add Button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting user entered values from edittext to string variables
                String shopeName = edtShopName.getText().toString();
                String itemname = edtItemName.getText().toString();
                String itemprice = edtItemPrice.getText().toString();

                //checks whether user entered all avlues
                if (!TextUtils.isEmpty(edtShopName.getText()) && !TextUtils.isEmpty(edtItemName.getText()) && !TextUtils.isEmpty(edtItemPrice.getText())) {

                    //checks shop name is already there in database or not
                    if (!db.shopExists(shopeName)) {

                        db.addShop(shopeName);//if shop name is not entered in database, we will add it from here
                        db.addList(shopeName, itemname, itemprice);//adding all list data to database
                        Toast.makeText(AddListActivity.this, "Added", Toast.LENGTH_SHORT).show();//showing added message to user

                        //Making edit text fields clear
                        edtItemName.setText("");
                        edtItemPrice.setText("");

                    }
                    //if shop name is already present in database this else section will execute
                    else {
                        db.addList(shopeName, itemname, itemprice);//adding all list data to database
                        Toast.makeText(AddListActivity.this, "Added", Toast.LENGTH_SHORT).show();//showing added message to user

                        //Making edit text fields clear
                        edtItemName.setText("");
                        edtItemPrice.setText("");
                    }

                }else {
                    //if user does not fill all values this message will show
                    Toast.makeText(AddListActivity.this, "Enter all values", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}