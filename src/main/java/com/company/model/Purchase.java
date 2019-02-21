package com.company.model;

public class Purchase {
    public String name;
    public String date;
    public int price;

    @Override
    public String toString() {
        return name + " " + date + " " + price;
    }
}
