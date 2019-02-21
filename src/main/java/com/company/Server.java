package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    Socket clientSocket;

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Сервер запущен");

            try {
                while(true) {
                    clientSocket = serverSocket.accept();
                    Worker worker = new Worker(clientSocket);
                    worker.start();
                }
            }
            finally {
                serverSocket.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
