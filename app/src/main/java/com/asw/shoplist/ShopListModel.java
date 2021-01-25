package com.asw.shoplist;

public class ShopListModel {

    //This class is a Model class fror Shop list
    //With this we can get or set values to shop list from anywhere in the application

    private String shopName;
    private String itemName;
    private String itemPrice;

    //Empty constructor
    public ShopListModel() {
    }

    //Constructor with all values
    public ShopListModel(String shopName, String itemName, String itemPrice) {
        this.shopName = shopName;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }



    //Getters and Setters for all values


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
