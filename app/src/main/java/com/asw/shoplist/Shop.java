package com.asw.shoplist;

import java.util.Objects;

public class Shop {
    private String name;
    private double totalPrice;

    public Shop(String name, double totalPrice) {
        this.name = name;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Double.compare(shop.totalPrice, totalPrice) == 0 &&
                Objects.equals(name, shop.name);
    }
}
