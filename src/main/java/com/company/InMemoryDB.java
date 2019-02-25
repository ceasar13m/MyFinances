package com.company;


import com.company.model.Purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryDB {
    private ConcurrentHashMap<String, ArrayList<Purchase>> purchases = new ConcurrentHashMap<>();


    void add(Purchase purchase) {
        String keyString = getMonthAndYear(purchase);

        if(purchases.containsKey(keyString)) {
            ArrayList<Purchase> purchasesArray = purchases.get(keyString);
            purchasesArray.add(purchase);
            purchases.put(keyString, purchasesArray);
        } else {
            ArrayList<Purchase> purchasesArray = new ArrayList<>();
            purchasesArray.add(purchase);
            purchases.put(keyString, purchasesArray);
        }

    }


    int get(String date) {
        int sum = 0;
        if (purchases.containsKey(date)) {
            ArrayList<Purchase> arrayPurchases = purchases.get(date);
            for(int i = 0; i < arrayPurchases.size(); i++) {
                sum += arrayPurchases.get(i).price;
            }
        }
        return sum;
    }


    String getMonthAndYear(Purchase purchase) {
        String[] tempString = purchase.date.toString().split(" ");
        return tempString[1] + " " + tempString[5];
    }

}



