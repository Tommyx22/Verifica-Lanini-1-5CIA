package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GestoreThread extends Thread {
    private final Socket clientSocket;

    public GestoreThread(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            String[] parole = {"cacca", "senatore", "lasagna", "patata", "rinoceronte"};
            StringBuilder caratteriIndovinati;

            String lunghezza = "";
            int num = (int) Math.random()*4;
            StringBuilder parolaScelta = new StringBuilder(parole[num]);
            String tipoOperazione = in.readLine();
            int tentativi = 0;

            for(int i = 0; i < parolaScelta.length(); i++) {
                lunghezza += "*" ;
            }

            caratteriIndovinati = new StringBuilder(lunghezza);

            while(tipoOperazione != "c") {

                tentativi++;
                String StringaUtente = in.readLine();

                if(tipoOperazione == "a") {
                    if(caratteriIndovinati == parolaScelta) {
                        out.writeBytes("Hai indovinato tutti i caratteri quindi hai vinto");
                        tipoOperazione = "c";
                    }
                    char carattere = StringaUtente.charAt(0);
                    if(parole[num].contains(StringaUtente)) {
                        for(int i = 0; i < parolaScelta.length(); i++) {
                            if(parolaScelta.charAt(i) == carattere) {
                                caratteriIndovinati.setCharAt(i, carattere);
                            }
                        }
                        out.writeBytes(caratteriIndovinati+"\n");

                    } else {
                        out.writeBytes("La lettera non è compresa nella parola\n");
                    }

                } else if(tipoOperazione == "b"){
                    if(StringaUtente == parole[num]) {
                        out.writeBytes("hai indovinato bravo ci hai messo: " + tentativi + " tentativi" + "\n");
                        out.writeBytes("3\n");
                        tipoOperazione = "c";
                    } else {
                        out.writeBytes("hai sbagliato ci hai messo: " + tentativi + " tentativi" + "\n");
                        out.writeBytes("3\n");
                        tipoOperazione = "c";
                    }
                }
            }

            clientSocket.close();
            System.out.println("Connessione chiusa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}