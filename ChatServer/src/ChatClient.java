//Kurt Whalen
//Networking Project
//5/5/15

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient 
{
    public static void main(String[] args)
    {
        int port = Integer.parseInt(args[0]);
        int servPort = 9100;
        String hostName = "ChatServer";
        String line = "";
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try
        {
            socket = new Socket(hostName, servPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(!line.equals("quit"))
            {
                try
                {  
                    line = in.readLine();
                    out.println(line + "/r");
                    out.flush();
                }
                catch(IOException e)
                {  
                    System.out.println("Sending error: " + e.getMessage());
                }
            }
        }
        catch(IOException a) 
        {
            a.printStackTrace();
        }
        finally
        {
                try
                {  
                    if (in != null)  
                        in.close();
                    if (out != null)  
                        out.close();
                    if (socket != null)  
                        socket.close();
                }
                catch(IOException i)
                {  
                    i.printStackTrace();
                }
        }
    }
}
