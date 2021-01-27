package com.asw.shoplist;

import java.time.LocalDate;
import java.util.Objects;

public class Shop {
    private String name;
    private double totalPrice;
    private LocalDate date;

    public Shop(String name, double totalPrice, long epoch) {
        this.name = name;
        this.totalPrice = totalPrice;
        this.date = LocalDate.ofEpochDay(epoch);
    }

    public String getName() {
        return name;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Double.compare(shop.totalPrice, totalPrice) == 0 &&
                Objects.equals(name, shop.name);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}
