package com.company;


import com.company.model.Purchase;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDB {
    ConcurrentHashMap<String, Purchase> purchases = new ConcurrentHashMap<String, Purchase>();
}
