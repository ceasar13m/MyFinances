package com.company;


import com.company.model.Purchase;

import java.util.concurrent.ConcurrentHashMap;

class InMemoryDB {
    private ConcurrentHashMap<String, Purchase> purchases = new ConcurrentHashMap<>();


    void add(Purchase purchase) {
        purchases.put(purchase.date, purchase);
    }


    int get(String date) {
        int sum = 0;
        for (String string : purchases.keySet()) {
            System.out.println(sum);
            if (string.equals(date)) {
                Purchase purchase = purchases.get(string);
                sum = sum + purchase.price;
            }
        }

        return sum;
    }

}



