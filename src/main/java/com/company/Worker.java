package com.company;

import com.company.model.Purchase;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class Worker extends Thread {

    BufferedWriter writer;
    BufferedReader reader;
    Gson gson;
    Socket socket;
    InMemoryDB inMemoryDB;


    public Worker(Socket socket, InMemoryDB inMemoryDB) {
        this.socket = socket;
        this.inMemoryDB = inMemoryDB;
    }

    @Override
    public void run() {
        System.out.println("Новый поток для клиента");
        String message;
        gson = new Gson();
        try {
            System.out.println("Новый поток...");
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = reader.readLine();
            while(true) {
                System.out.println("Получено сообщение: " + message);
                if(message.equals("exit"))
                    break;

                System.out.println(message);
                Purchase purchase = gson.fromJson(message, Purchase.class);

                inMemoryDB.purchases.put(purchase.date, purchase);
                System.out.println(inMemoryDB.purchases.containsKey("11.2012"));

                message = reader.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void makePurchase() {

    }
}
