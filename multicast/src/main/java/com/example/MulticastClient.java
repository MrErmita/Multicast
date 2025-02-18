package com.example;

import java.net.*;
import java.io.*;

public class MulticastClient {
    public static void main(String[] args) throws IOException {
        byte[] bufferIN = new byte[1024];

        // Parametri del server
        int porta = 6789;
        String gruppo = "255.4.5.6";
        
        InetAddress group = InetAddress.getByName(gruppo);
        NetworkInterface netIf = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

        try (MulticastSocket socket = new MulticastSocket(porta)) {
            socket.joinGroup(new InetSocketAddress(group, porta), netIf);

            DatagramPacket packetIN = new DatagramPacket(bufferIN, bufferIN.length);
            socket.receive(packetIN);

            System.out.println("Ho ricevuto i dati di lunghezza " + packetIN.getLength() + " da: " + packetIN.getAddress().toString() + " porta: " + packetIN.getPort());
            System.out.write(packetIN.getData(), 0, packetIN.getLength());
            System.out.println();

            socket.leaveGroup(new InetSocketAddress(group, porta), netIf);
        }
    }
}
