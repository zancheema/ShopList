package com.asw.shoplist;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.Objects;

public class ShopItem {
    private String shopName;

    private String itemName;

    private double itemPrice;

    private LocalDate date;

    public ShopItem(String shopName, String itemName, double itemPrice) {
        this.shopName = shopName;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        date = LocalDate.now();
    }

    public ShopItem(@NonNull String shopName, @NonNull String itemName, double itemPrice, LocalDate date) {
        this.shopName = shopName;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.date = date;
    }

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

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopItem shopItem = (ShopItem) o;
        return Double.compare(shopItem.itemPrice, itemPrice) == 0 &&
                Objects.equals(shopName, shopItem.shopName) &&
                Objects.equals(itemName, shopItem.itemName) &&
                Objects.equals(date, shopItem.date);
    }
}
