import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class ChatUser extends Thread 
{
    static Vector users = new Vector(10);
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public ChatUser(Socket socket) throws IOException 
    {
       //constructor for each new user connected to server
       this.socket = socket;
       in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    public void run() 
    {
        String line;
        synchronized(users) 
        {
            //adds new user to vector list
            users.addElement(this);
        }
        try 
        {
            while(!(line = in.readLine()).equalsIgnoreCase("/quit")) 
            //if typed /quit ends while loop
            {
                for(int i = 0; i < users.size(); i++) 
                {
                    //sends variable line to all other users on Vector list
                    synchronized(users) 
                    {
                        //user and users different variables to be noted
                        ChatUser user = (ChatUser)users.elementAt(i);
                        user.out.println(line + "\r");
                        out.flush();
                    }
                }
            }
        } 
        catch(IOException ioe) 
        {
            ioe.printStackTrace();
        } 
        finally 
        {
            try 
            {
                //closes sockets once code gets to this point
                in.close();
                out.close();
                socket.close();
            } 
            catch(IOException ioe) 
            {
            } 
            finally 
            {
                synchronized(users) 
                {
                    //removes the current user from the Vector list
                    users.removeElement(this);
                }
            }
        }
    }
}

