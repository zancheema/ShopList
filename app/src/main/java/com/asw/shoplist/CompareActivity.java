package com.asw.shoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CompareActivity extends AppCompatActivity {
    //Declaring variables for views and values
    private Spinner spinnerShop1;
    private Spinner spinnerShop2;
    private Button btnCompare;
    private TextView textViewShop1;
    private TextView textViewShop2;
    private TableLayout tableShop1;
    private TableLayout tableShop2;
    private TextView textViewResult;
    private  TableRow heder;
    private  TableRow heder2;

    private TextView ItemName,ItemName2;
    private TextView ItemPrice,ItemPrice2;
    private DbHelper db;
    ArrayList<String> shopNames1;
    ArrayList<String> shopNames2;
    ArrayList<ShopListModel> shopListModels1;
    ArrayList<ShopListModel> shopListModels2;
    private ShopListModel shopList1;
    private ShopListModel shopList2;
    private String selectedShop1;
    private String selectedShop2;
    private int shop1Counter =0;
    private int shop2Counter =0;
    private int dip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        getSupportActionBar().setTitle("Compare List");//setting title for action bar

        //casting all the views with their ids
        spinnerShop1 = findViewById(R.id.spinnerShop1);
        spinnerShop2 = findViewById(R.id.spinnerShop2);
        btnCompare = findViewById(R.id.btnCompareList);
        textViewShop1 = findViewById(R.id.textView_shop1);
        textViewShop2 = findViewById(R.id.textView_shop2);
        tableShop1 = findViewById(R.id.table_shop1);
        tableShop2 = findViewById(R.id.table_shop2);
        textViewResult = findViewById(R.id.text_result);

        heder  = new TableRow(this);//table row for table1 header
        heder2 = new TableRow(this);//table row for table2 header

        dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 1, getResources().getDisplayMetrics());//an integer variable for setting height and width for text views inside a table

        //Making a text view for header(Item) for table1
        ItemName = new TextView(this);
        ItemName.setText("Item");//setting text
        ItemName.setTextColor(getResources().getColor(R.color.colorBlack));//setting color
        ItemName.setBackground(getResources().getDrawable(R.drawable.border_table));//setting background
        ItemName.setWidth(90*dip);//setting width
        ItemName.setHeight(30*dip);//setting height
        ItemName.setGravity(Gravity.CENTER);//setting text center in the text view

        //Making a text view for header(Item Price) for table1
        ItemPrice = new TextView(this);
        ItemPrice.setText("Item Price");
        ItemPrice.setTextColor(getResources().getColor(R.color.colorBlack));
        ItemPrice.setBackground(getResources().getDrawable(R.drawable.border_table));
        ItemPrice.setWidth(90*dip);
        ItemPrice.setHeight(30*dip);
        ItemPrice.setGravity(Gravity.CENTER);

        //Making a text view for header(Item) for table2
        ItemName2 = new TextView(this);
        ItemName2.setText("Item");//setting text
        ItemName2.setTextColor(getResources().getColor(R.color.colorBlack));//setting color
        ItemName2.setBackground(getResources().getDrawable(R.drawable.border_table));//setting background
        ItemName2.setWidth(90*dip);//setting width
        ItemName2.setHeight(30*dip);//setting height
        ItemName2.setGravity(Gravity.CENTER);//setting text center in the text view


        ItemPrice2 = new TextView(this);
        ItemPrice2.setText("Item Price");
        ItemPrice2.setTextColor(getResources().getColor(R.color.colorBlack));
        ItemPrice2.setBackground(getResources().getDrawable(R.drawable.border_table));
        ItemPrice2.setWidth(90*dip);
        ItemPrice2.setHeight(30*dip);
        ItemPrice2.setGravity(Gravity.CENTER);


        db = new DbHelper(this);//initializing a DbHelper object,which we can use for database operations

        //initializing 2 array lists.with this we can store shop names for spinner
        shopNames1 = new ArrayList<>();
        shopNames2 = new ArrayList<>();


        shopNames1.clear();//clears the list
        shopNames1.add(0,"Select Shop 1");//adding first option in the list

        shopNames2.clear();//clears the list
        shopNames2.add(0,"Select Shop 2");//adding first option in the list

        final Cursor cursor = db.getShopNames();//cursor object which holds shop names from the database
        if(cursor.moveToNext()){
            do{

                    shopNames1.add(cursor.getString(cursor.getColumnIndex("shop_name")));//adding all shop names to list
                    shopNames2.add(cursor.getString(cursor.getColumnIndex("shop_name")));//adding all shop names to list


            }while (cursor.moveToNext());



        }
        cursor.close();//closing cursor

        //Making array adapter for spinner with shop name list
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,shopNames1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerShop1.setAdapter(arrayAdapter);//setting adapter to spinner

        //Making array adapter for spinner with shop name list
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,shopNames2);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerShop2.setAdapter(arrayAdapter2);//setting adapter to spinner

        //setting item clicked listener for spinner1
        spinnerShop1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedShop1 = (String) adapterView.getSelectedItem();//storing selected item to a variable

                //checks user is not selected the first item
                if (!selectedShop1.equals("Select Shop 1")) {
                    shopListModels1 = new ArrayList<>();


                    Cursor cursor1 = db.getShopData(selectedShop1);//getting list details with shopname


                    while (cursor1.moveToNext()) {
                        //getting data fro database and storing it to string variable
                        String shopname = cursor1.getString(cursor1.getColumnIndex("shop_name"));
                        String itemname = cursor1.getString(cursor1.getColumnIndex("item_name"));
                        String itemprice = cursor1.getString(cursor1.getColumnIndex("item_price"));

                        shopList1 = new ShopListModel(shopname, itemname, itemprice);//Making ShopListModel with data from the database

                        shopListModels1.add(shopList1);//Adding shop list model to array list

                    }
                    cursor1.close();//closing cursor
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //setting item clicked listener for spinner2
        spinnerShop2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedShop2 = (String) adapterView.getSelectedItem();//storing selected item to a variable

                //checks user is not selected the first item
                if (!selectedShop2.equals("Select Shop 2")) {
                    shopListModels2 = new ArrayList<>();


                    Cursor cursor2 = db.getShopData(selectedShop2);//getting list details with shopname


                    while (cursor2.moveToNext()) {
                        //getting data fro database and storing it to string variable
                        String shopname = cursor2.getString(cursor2.getColumnIndex("shop_name"));
                        String itemname = cursor2.getString(cursor2.getColumnIndex("item_name"));
                        String itemprice = cursor2.getString(cursor2.getColumnIndex("item_price"));

                        shopList2 = new ShopListModel(shopname, itemname, itemprice);//Making ShopListModel with data from the database

                        shopListModels2.add(shopList2);//Adding shop list model to array list

                    }
                    cursor2.close();//closin cursor
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //setting click listener for compare button
        btnCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //checks user is selected shop names or not
                if (spinnerShop1.getSelectedItem().equals("Select Shop 1") || spinnerShop2.getSelectedItem().equals("Select Shop 2") ){

                    Toast.makeText(CompareActivity.this, "Please select correct Shop", Toast.LENGTH_SHORT).show();//if user is no selected shop name. this message will show
                }
                else {

                    setViews();//if user is selected correctly this method will exeute
                }
            }
        });
    }



    private void setViews() {

        //we need these two counter values to get the number of less price items from each shop
        shop1Counter =0;//setting shop1counter to 0
        shop2Counter =0;//setting shop2counter to 0

        heder.removeAllViews();//remove all views from row

        tableShop1.removeAllViews();//remove all views from table



        heder.addView(ItemName);//adding text view to row
        heder.addView(ItemPrice);//adding text view to row


       configureTable();//this method is to make table & text views visible and non visible to user










        tableShop1.addView(heder);//adding heder row to table

        heder2.removeAllViews();
        tableShop2.removeAllViews();
        heder2.addView(ItemName2);
        heder2.addView(ItemPrice2);
        tableShop2.addView(heder2);

        //Making text views & rows for items
        TextView shopOneItem = null,shopTwoItem=null,shoponeItemPrice=null,shoptwoItemPrice=null;
        TableRow shop1Row = null;
        TableRow shop2Row=null;



        //getting all data of shop1
        for (int i=0;i<shopListModels1.size();i++){
            shop1Row = new TableRow(this);//making a new row
            shopOneItem = new TextView(this);//making text view

            shopOneItem.setText(shopListModels1.get(i).getItemName());//setting text to text view
            shopOneItem.setBackground(getResources().getDrawable(R.drawable.border_table));//setting background
            shopOneItem.setWidth(90*dip);//setting width
            shopOneItem.setHeight(30*dip);//setting height
            shopOneItem.setGravity(Gravity.CENTER);//making text in the center of the text view

            shoponeItemPrice = new TextView(this);
            shoponeItemPrice.setText(shopListModels1.get(i).getItemPrice());
            shoponeItemPrice.setBackground(getResources().getDrawable(R.drawable.border_table));
            shoponeItemPrice.setWidth(90*dip);
            shoponeItemPrice.setHeight(30*dip);
            shoponeItemPrice.setGravity(Gravity.CENTER);

            //getting all data from shop2
            for (int j=0; j < shopListModels2.size(); j++){

                double price1 = Double.parseDouble(shopListModels1.get(i).getItemPrice());//getting shop1 current item price
                double price2 = Double.parseDouble(shopListModels2.get(j).getItemPrice());//getting shop2 current item price
                String itemName1 = shopListModels1.get(i).getItemName();//getting shop1 current item name
                String itemName2 = shopListModels2.get(j).getItemName();//getting shop2 current item name

                //checking current item name is equal to shop1 current item
                if (itemName1.toLowerCase().trim().equals(itemName2.toLowerCase().trim())){

                    //comparing both item prices
                    if (price1 < price2){

                        //if shop1 item price is less, making the text color to green
                        shopOneItem.setTextColor(getResources().getColor(R.color.colorGreen));
                        shoponeItemPrice.setTextColor(getResources().getColor(R.color.colorGreen));
                        shop1Counter++;//increasing the counter value by one

                    }


                }

            }

            shop1Row.addView(shopOneItem);//adding item row
            shop1Row.addView(shoponeItemPrice);//adding price row

            //making shop1 table with rows
            tableShop1.addView(shop1Row,new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT) );
        }
        //getting all data of shop2
        for (int i=0;i<shopListModels2.size();i++){
            shop2Row = new TableRow(this);//making new row

            shopTwoItem = new TextView(this);//making new text view
            shopTwoItem.setText(shopListModels2.get(i).getItemName());//setting text with current item
            shopTwoItem.setWidth(90*dip);//setting width
            shopTwoItem.setHeight(30*dip);//setting height
            shopTwoItem.setBackground(getResources().getDrawable(R.drawable.border_table));//setting background
            shopTwoItem.setGravity(Gravity.CENTER);//setting text to center of text view

            shoptwoItemPrice = new TextView(this);
            shoptwoItemPrice.setText(shopListModels2.get(i).getItemPrice());
            shoptwoItemPrice.setWidth(90*dip);
            shoptwoItemPrice.setHeight(30*dip);
            shoptwoItemPrice.setBackground(getResources().getDrawable(R.drawable.border_table));
            shoptwoItemPrice.setGravity(Gravity.CENTER);

            //getting all data of shop1
            for (int j=0; j< shopListModels1.size(); j++){

                double price1 = Double.parseDouble(shopListModels2.get(i).getItemPrice());//getting shop2 current item price
                double price2 = Double.parseDouble(shopListModels1.get(j).getItemPrice());//getting shop1 current item price
                String itemName1 = shopListModels2.get(i).getItemName();//getting shop2 current item name
                String itemName2 = shopListModels1.get(j).getItemName();//getting shop1 current item name

                //checking current item name is equal to shop1 current item
                if (itemName1.toLowerCase().trim().equals(itemName2.toLowerCase().trim())){

                    //comparing the prices
                    if (price1 < price2){

                        //if shop2 item price is less, making the text color to green
                        shopTwoItem.setTextColor(getResources().getColor(R.color.colorGreen));
                        shoptwoItemPrice.setTextColor(getResources().getColor(R.color.colorGreen));
                        shop2Counter++;//increasing the counter value by one

                    }


                }


            }




            shop2Row.addView(shopTwoItem);////adding item row
            shop2Row.addView(shoptwoItemPrice);//adding item price row


            //making shop1 table with rows
            tableShop2.addView(shop2Row,new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT) );

        }

        if (shop1Counter == shop2Counter ){
            if (shop1Counter !=0 && shop2Counter !=0){
                textViewResult.setText("you can buy from any store");//show this results when items are equal
            }else {
                textViewResult.setText("There are No matching Products");//show this results when no matching items
            }



        }
        else if (shop1Counter < shop2Counter) {
            textViewResult.setText("It is more convenient to buy from"+" " +selectedShop2+ " "+"because there are "+shop2Counter+" "+"items cheaper than "+selectedShop1);//show this results when items are grater than shop1
        }else {
            textViewResult.setText("It is more convenient to buy from"+" " +selectedShop1+ " "+"because there are "+shop1Counter+" "+"items cheaper than "+selectedShop2);//show this results when items are grater than shop2
        }

    }



    private void configureTable() {

        tableShop1.setVisibility(View.VISIBLE);//making text view visible to users
        tableShop2.setVisibility(View.VISIBLE);//making text view visible to users
        textViewShop1.setVisibility(View.VISIBLE);//making table visible to users
        textViewShop2.setVisibility(View.VISIBLE);//making table visible to users
        textViewShop1.setText(selectedShop1+" " +"Details");//setting text with shop name
        textViewShop2.setText(selectedShop2+" " +"Details");//setting text with shop name

    }
}