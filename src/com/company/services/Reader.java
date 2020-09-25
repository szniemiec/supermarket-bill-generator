package com.company.services;

import com.company.models.Bill;
import com.company.models.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private String path;

    public Reader() {
        this.path = "";
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bill readProducts() {
        Bill bill = new Bill();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineContent = line.replaceAll("\\s+", "").split(",");
                long barcode = Long.parseLong(lineContent[0]);
                String name = lineContent[1];
                float price = Float.parseFloat(lineContent[2]);
                bill.add(new Product(barcode, name, price), 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bill;
    }

    public List<Long> readBill() {
        List<Long> barcodeList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineContent = line.replaceAll("\\s+", "").split(",");
                long barcode = Long.parseLong(lineContent[0]);
                barcodeList.add(barcode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return barcodeList;
    }
}
