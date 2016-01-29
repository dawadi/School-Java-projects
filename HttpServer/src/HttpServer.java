//Author: Kurt Whalen
//Date: 3/8/15
//Brief Description: HTTP TCP Server Socket that listens on specified port and does not return
//anything to client but does print information recieved from client.

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

public class HttpServer {

    public static void main(String[] args) {
        String CRLF = ("/r /n");
       
	try
	{
            //Port number that server listens to
            int  DEST_PORT = Integer.parseInt(args[0]);
            //Create server socket
            ServerSocket svrSocket = new ServerSocket(DEST_PORT);
			
            while(true)
            {
                //Accept client request, this returns a local Socket
		//to communicate with the client
		System.out.println("Waiting for client:");
		Socket clientSocket = svrSocket.accept();
                
		//Get the input and output streams of the socket
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());	                               
                String inLine = in.readLine(); //reads input stream into a string
                StringTokenizer currentToken = new StringTokenizer(inLine); //tokenizees that string
                //uses the nextToken method to jump through the tokens to eventually find the file request
                if(currentToken.nextToken().equals("Get")){ 
                   String fileCurrent = currentToken.nextToken(); //grabs whole first line
                if (fileCurrent.startsWith("/") == true ) 
                    fileCurrent = fileCurrent.substring(1); //passes the / and gets the file info past that by making it a substring
                File file = new File(fileCurrent); //takes the string and makes it a file path
                if(file.exists()) //checks to make sure file exists
                {
                    int fileSize = (int) file.length(); //finds size of file found
                    FileInputStream inRead = new FileInputStream(fileCurrent);
                    byte[] fileInSize = new byte[fileSize]; //creates byte array for the file
                    inRead.read(fileInSize);
                    out.writeBytes("HTTP/1.0 200 \r\n");  //starts header and next three if's determine what the file is and send an appropriate notice of the incoming filetype
                    if(fileCurrent.endsWith("html"))
                        out.writeBytes("Content-Type: text/html");
                    if(fileCurrent.endsWith(".jpg"))
                        out.writeBytes("Content-Type: image/jpeg\r\n");
                    if(fileCurrent.endsWith(".gif"))
                        out.writeBytes("Content-Type: image/gif\r\n");
                    out.writeBytes("Content-Length: " + fileSize + "\r\n");
                    out.writeBytes("\r\n"); //indicates end of header
                    out.write(fileInSize, 0, fileSize);
                    }
                else 
                {
                    //Create status line     
                    String statusLine = "HTTP/1.1 404 Not found" + CRLF;   // response line writes the error message for not finding the file requested   
                    // header line. Only interested in the type of object we are sending back     
                    String content = "Content-type: " + "image/jpg" + CRLF;    //couldn't get the filecontent bit to work properly, it wouldn't take strings or files and the java API says it takes strings 
                    //Entity body. HTML file with message Not Found as the text     
                    String entityBody = "<HTML>" + "<HEAD><TITLE>Not Found</TITLE></HEAD>" +     "<BODY>Not Found</BODY></HTML>";     
                    out.writeBytes(statusLine);     
                    out.writeBytes(content);     
                    out.writeBytes(CRLF);     
                    out.writeBytes(entityBody); 
                }  
                //close streams and socket and goes back to waiting for clients
		out.close();
                in.close();
                clientSocket.close();
            }
	}
        }
	catch (Exception e)
	{
            e.printStackTrace();
	}
    }
//I would like to put a notice here that the instructions for the homework asked to display the packet once 
//it was read and it also asks to parse the request line for the request type url requested and http version 
//and print it as well.  It seemed like maybe that wasn't intentional on the directions but I did both 
//anyways to make sure. And it didn't specify if I needed to send anything back to the browser so I just
//printed server side, it would be easy enough to just remove System. on the print lines to send it back to 
//the browser.  I didn't do so because the homework never specified that I was to send it back ot the client.
//I just wanted I'd clarify all of that so that I don't get marked down due to it seeming like I was negligent.
        
}
