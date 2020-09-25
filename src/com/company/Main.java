package com.company;

import com.company.models.Bill;
import com.company.services.Reader;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Reader reader = new Reader();
        reader.setPath("products.csv");
        Bill products = reader.readProducts();

        reader.setPath("sampleBill.csv");
        List<Long> barcodeList = reader.readBill();

        Bill bill = new Bill();
        bill = bill.createBill(barcodeList, products);

        bill.readBill();
    }
}
