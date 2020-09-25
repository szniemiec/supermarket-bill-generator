package com.company.models;

public class Product {

    private final long barcode;
    private final String name;
    private float price;

    public Product(long barcode, String name, float price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }

    public long getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
