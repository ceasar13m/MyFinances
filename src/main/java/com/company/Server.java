package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    ServerSocket serverSocket;
    Socket clientSocket;

    @Override
    public void run() {
        InMemoryDB inMemoryDB = new InMemoryDB();
        try {
            serverSocket = new ServerSocket(8012);
            System.out.println("########=>Сервер запущен");
                while(true) {
                    clientSocket = serverSocket.accept();
                    Worker worker = new Worker(clientSocket, inMemoryDB);
                    worker.start();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
