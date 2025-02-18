package main.java.com.example;

import java.net.*;
import java.io.*;
import java.util.*;

public class ServerMulticast {

    public static void main(String[] args) throws IOException {
        boolean attivo = true;
        byte[] bufferOUT = new byte[1024];
        int conta = 20;
        int porta = 6789;
        InetAddress group = InetAddress.getByName("255.4.5.6");
        
        MulticastSocket socket = new MulticastSocket(porta);

        String dString = null;

        while (attivo) {
            dString = new Date().toString();
            bufferOUT = dString.getBytes();

            DatagramPacket packet;

            packet = new DatagramPacket(bufferOUT, bufferOUT.length, gruppo, porta);

            socket.send(packet);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            conta--;
            if (conta == 0) {
                System.out.println("SERVER IN CHIUSURA. Buona serata.");
                attivo = false;
            } else {
                System.out.println("SERVER attivo per altri " + conta + " secondi.");
            }
        }

        socket.close();
    }
}
