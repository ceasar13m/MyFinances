package com.company.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase {
    public String name;
    public Date date;
    public int price;



    @Override
    public String toString() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.yyyy");
        return name + " " + simpleDateFormat.format(date) + " " + price;
    }
}
