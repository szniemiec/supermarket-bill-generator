package com.company.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bill {

    Map<Product, Integer> productMap;

    public Bill() {
        this.productMap = new HashMap<>();
    }

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public void add(Product product, int amount) {
        if (productMap.containsKey(product)) {
            int actualAmount = productMap.get(product);
            productMap.replace(product, actualAmount + amount);
        } else {
            productMap.put(product, amount);
        }
    }

    public int getAmount(Product product) {
        return productMap.get(product);
    }

    public Bill createBill(List<Long> barcodeList, Bill productList) {
        Bill bill = new Bill();
        Map<Product, Integer> productMap = productList.getProductMap();
        for (Long barcode : barcodeList) {
            for (Product product : productMap.keySet()) {
                if (product.getBarcode() == barcode) {
                    bill.add(product, 1);
                }
            }
        }
        for (Product product : bill.productMap.keySet()) {
            int actualAmount = bill.getAmount(product);
            if (checkPromotion(actualAmount)) {
                addPromotion(product, actualAmount);
            }
        }
        return bill;
    }

    public float getTotalPrice() {
        float totalPrice = 0;
        for (Product product : productMap.keySet()) {
            int amount = getAmount(product);
            totalPrice += amount * product.getPrice();
        }
        return totalPrice;
    }

    public void readBill() {
        for (Product product : productMap.keySet()) {
            System.out.println(product.getBarcode() + " " + product.getName() +
                    " " + product.getPrice() + " " + productMap.get(product));
        }
        System.out.println("Total price: " + getTotalPrice());
    }

    private boolean checkPromotion(int amount) {
        return amount >= 2;
    }

    private void addPromotion(Product product, int amount) {
        float onePiecePrice = product.getPrice();
        float priceWithPromotion = 0;
        for (int i = 1; i <= amount; i++) {
            if (i % 2 == 0) {
                priceWithPromotion += onePiecePrice - onePiecePrice * 20 / 100;
            } else {
                priceWithPromotion += onePiecePrice;
            }
        }
        product.setPrice(priceWithPromotion);
    }
}
