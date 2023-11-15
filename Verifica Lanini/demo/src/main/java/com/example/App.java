package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {

            System.out.println("Server in avvio");
            server = new ServerSocket(3000);
            
            while (true) {

                Socket s = server.accept();

                System.out.println("un client si è collegato");

                Thread t = new GestoreThread(s);
                t.start();   
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        } finally {
            try {
                if(server != null) {
                    server.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } 
        }
    }
}