/** Kurt Whalen
 * 2/14/15
 *  Assignment 2 PingClient code
 */

import java.net.*;
import java.util.*;
import java.io.*;

public class PingClient {

	// variables to collect port and server name from user input
        public static String serverName;
        public static int serverPort;
	
        
	public static void main(String[] args) {
		//breaks args servername:portnum into two strings and then makes the port num an int value
                String[] ServNPort = args[0].split(":", 2);
                serverName = ServNPort[0];
                serverPort = Integer.parseInt(ServNPort[1]);
               
		try
		{
			//finding IP address and saving it
			InetAddress ServAddr;
			ServAddr = InetAddress.getByName(serverName);
                        DatagramSocket clientSocket = new DatagramSocket();
			
                        for(int i = 0; i < 9; i++)
                        {
                        //creating timestamp variable
                        Date timeStamp = new Date();
                        String inputString = "PING " + i + " " + timeStamp.getTime();
                        //socket and packet creation and sending
                        DatagramPacket clientPacket = new DatagramPacket(inputString.getBytes(), inputString.length(), ServAddr, serverPort);
			
			
			//Send packet to server 
			clientSocket.send(clientPacket);
                        
                        //time out set here
			clientSocket.setSoTimeout(1000);
                        
                        //recieved bytes to be turned into data
			byte[] receiveData = new byte[1024];
			
			//Packet to recieve data to
			DatagramPacket recvPacket = new DatagramPacket(receiveData, receiveData.length);
			
			//Receive reply from server
			clientSocket.receive(recvPacket);
			
			String recvString = new String(recvPacket.getData(), 0, recvPacket.getLength());
			System.out.println("Received " + recvString + " from Server");
                        
                        //waits for one second before starting the next for loop iteration
                     
                        }
			//Close socket before exiting
			clientSocket.close();
		}
                
                catch (SocketTimeoutException f)
                {
                System.err.println ("Host timeout");
                f.printStackTrace();
                }
                
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
