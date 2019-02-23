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
        String url;
        gson = new Gson();
        try {
            System.out.println("Новый поток...");
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            url = reader.readLine();
            while(true) {
                System.out.println("Получено сообщение: " + url);
                String command = urlParsed(url);



                if(command.equals("exit"))
                    break;

                switch (command) {
                    case "add": {
                        Purchase purchase = makePurchaseObject(url);
                        processAdd(purchase);
                        break;
                    }
                    case "get": {
                        processGet(url);
                        break;
                    }
                    default: {
                        break;
                    }
                }

                url = reader.readLine();

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    String urlParsed(String url) {
        String[] command = url.split("/");
        return command[0];
    }

    Purchase makePurchaseObject(String url) {
        String[] strings = url.split("/");
        return gson.fromJson(strings[1], Purchase.class);
    }


    void processAdd(Purchase purchase) throws IOException {
        inMemoryDB.add(purchase);
        writer.write("OK" + "\n");
        writer.flush();
    }

    void processGet(String url) throws IOException {

        String[] strings = url.split("/");
        String date = strings[1];

        int sum = inMemoryDB.get(date);

        System.out.println(sum);
        writer.write(sum);
        writer.flush();
    }

}
