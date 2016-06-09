package com.mcelrea;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Tech on 6/9/2016.
 */

public class UDP_Server {

    DatagramSocket socket = null;

    public UDP_Server() {

    }

    public void createAndListen() {
        try {
            socket = new DatagramSocket(2000); //2000 = port number to use
            byte[] incomingData = new byte[1024];

            while (true) {
                DatagramPacket incomingPacket = new DatagramPacket(incomingData,incomingData.length);
                socket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                try{
                    Integer myInteger = (Integer)is.readObject();
                    System.out.println("Recieved Integer: " + myInteger);
                }catch(Exception e){
                    System.out.println("FUCKED UP CASTING OBJECT");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {}
    }

}
