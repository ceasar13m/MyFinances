package com.company;

import com.company.model.Purchase;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class Worker extends Thread {

    BufferedWriter writer;
    BufferedReader reader;
    Gson gson;

    Socket socket;


    public Worker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Новый поток...");
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = null;
            message = reader.readLine();
            System.out.println("Получено сообщение: " + message);
            Purchase purchase = makePurchase(message);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    Purchase makePurchase(String url) {
        String[] string = url.split("/");
        Purchase purchase = new Purchase();
        purchase.name = string[1];
        purchase.day = Integer.parseInt(string[2]);
        purchase.month = Integer.parseInt(string[3]);
        purchase.year = Integer.parseInt(string[4]);
        purchase.price = Integer.parseInt(string[5]);

        return purchase;
    }
}
