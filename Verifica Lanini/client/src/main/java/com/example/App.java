package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try {

            System.out.println("Il client è partito");
            Socket s = new Socket("localhost", 3000);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            Scanner tastiera = new Scanner(System.in);

            String scelta;

            do {

                System.out.println("a: indovina lettera \nb: indovina parola \nc: chiudi la connessione");
                System.out.println("scelta: ");
                scelta = tastiera.nextLine();

            switch (scelta) {
                case "a":

                    System.out.println("prova ad indovinare una lettera: ");
                    out.writeBytes(tastiera.nextLine()+"\n");
                    out.writeBytes("a\n");

                    System.out.println("parola: " + in.readLine());
                    
                break;
                
                case "b":

                    System.out.println("prova ad indovinare una parola");
                    out.writeBytes(tastiera.nextLine()+"\n");
                    out.writeBytes("b\n");

                    System.out.println(in.readLine());
                    scelta = in.readLine();

                break;

                case "c":
                
                    System.out.println("Chiusura della connessione in corso...");
                    out.writeBytes("c\n");

                break;
                default:
                    System.out.println("Scelta non valida");
                break;
            }

            } while(scelta != "c");
           
            tastiera.close();
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }
}