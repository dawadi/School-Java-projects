//Kurt Whalen
//Networking Project
//5/3/15

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer 
{
    
    public static void main(String[] args) 
    {
        int port;
        ServerSocket serverSocket = null;
        try
        {
            port = Integer.parseInt(args[0]);
            serverSocket = new ServerSocket(port);
            while(true) 
            {
                Socket socket = serverSocket.accept();
                ChatUser user = new ChatUser(socket);
                user.start();
            }
        }
        catch(Exception a) 
        {
            a.printStackTrace();
        }
            finally
            {
                try
                {
                    serverSocket.close();
                }
                catch(Exception e) 
                {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
     }
}

