package com.asw.shoplist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//This class is to Handle Database Operations

public class DbHelper extends SQLiteOpenHelper {
    //Declaring variables for database
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "shopping_db";
    private static final String Table_list = "list_table";
    private static final String Table_shop = "shop_table";
    private static final String KEY_SHOPNAME = "shop_name";
    private static final String KEY_ITEMNAME = "item_name";
    private static final String KEY_ITEMPRICE = "item_price";
    private static final String KEY_CREATION_TIME = "creation_time";
    private static final String KEY_ID = "iD";
    private SQLiteDatabase db;

    //String variables which holds create and drop table query
    private String CREATE_LIST_TABLE = "CREATE TABLE  " + Table_list + " ("
            + KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_SHOPNAME + " VARCHAR, " +
            KEY_ITEMNAME + " VARCHAR, " +
            KEY_ITEMPRICE + " NUMERIC(10, 2)," +
            KEY_CREATION_TIME + " INTEGER NOT NULL)";
    private String CREATE_SHOP_TABLE = "CREATE TABLE  " + Table_shop + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_SHOPNAME + " VARCHAR)";
    private String DROP_LIST_TABLE = "DROP TABLE IF EXISTS " + Table_list;
    private String DROP_SHOP_TABLE = "DROP TABLE IF EXISTS " + Table_shop;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_LIST_TABLE);//Creating table for storing Item details;
        db.execSQL(CREATE_SHOP_TABLE);//Creating table for storing Shop Names

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_LIST_TABLE);//Drop table list if exists
        db.execSQL(DROP_SHOP_TABLE);//Drop table shop if exists
        onCreate(db);

    }

    //This function is to add details in List table
    public void addList(String shopname, String itemname, double itemprice) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_SHOPNAME, shopname);
        cv.put(KEY_ITEMNAME, itemname);
        cv.put(KEY_ITEMPRICE, itemprice);
        cv.put(KEY_CREATION_TIME, System.currentTimeMillis());
        db.insert(Table_list, null, cv);
        db.close();
    }

    //This function is to Check whether shop name is already there in the database or not
    public boolean shopExists(String shopname) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Table_shop, null);
        ArrayList<String> shopnames = new ArrayList<>();

        boolean shopExi = false;
        if (cursor.moveToFirst()) {
            do {
                String shopName = cursor.getString(cursor.getColumnIndex("shop_name"));
                shopnames.add(shopName);
            } while (cursor.moveToNext());

            if (cursor == null) {
                shopExi = false;
            } else {
                for (int i = 0; i < shopnames.size(); i++) {

                    if (shopnames.get(i).toLowerCase().trim().equals(shopname.toLowerCase().trim())) {
                        shopExi = true;
                    }
                }
            }

        }


        return shopExi;

    }

    //This function is to add shop name in the Shop table
    public void addShop(String shopname) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SHOPNAME, shopname);
        db.insert(Table_shop, null, contentValues);
        db.close();
    }

    //This function is to get shop names from shop table and return to cursor to the class
    public Cursor getShopNames() {

        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Table_shop, null);


        return cursor;


    }

    //This function is to get List details with shop name from List table and return to cursor to the class
    public Cursor getShopData(String shopname) {
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_list + " WHERE TRIM(shop_name) = '" + shopname.trim() + "'", null);

        return cursor;
    }

    public Cursor getAllShops() {
        db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT " +
                        KEY_SHOPNAME + ", " +
                        "SUM(" + KEY_ITEMPRICE + ") AS " + KEY_ITEMPRICE + ", " +
                        KEY_CREATION_TIME +
                        " FROM " + Table_list +
                        " GROUP BY " + KEY_SHOPNAME +
                        " ORDER BY " + KEY_CREATION_TIME,
                null
        );
    }
}
