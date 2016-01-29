//Kurt Whalen
//Networking Assignment 9: Extra Credit
//5/9/15

import java.io.*;
import java.net.*;

public class WOLClient 
{
    public static final int PORT = 7;
    
    public static void main(String[] args) 
    {
        String MAC_ID = null;
        String subnetID = "255.255.255.255";
        //makes sure MAC ID is the corrent length including :'s
        if(args[0].length() == 17)   
            MAC_ID = args[0];
        else
            System.exit(1);
        
        try 
        {
            byte[] splitBytes = new byte[6];
            String[] splitString = MAC_ID.split("(\\:|\\-)");
            for (int i = 0; i < 6; i++) 
            {
                splitBytes[i] = (byte) Integer.parseInt(splitString[i], 16);
            }
            byte[] bytes = new byte[6 + 16 * splitBytes.length];
            for (int i = 0; i < 6; i++) 
            {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += splitBytes.length) 
            {
                System.arraycopy(splitBytes, 0, bytes, i, splitBytes.length);
            }
            InetAddress address = InetAddress.getByName(subnetID);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
            System.out.println("Packet sent");
        }
        catch (Exception e) 
        {
            System.out.println("Failed to Wake");
            System.exit(1);
        }
    }
}
