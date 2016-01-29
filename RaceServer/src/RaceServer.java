//Kurt Whalen
//Race Server set up to prepare RaceList object, send it to the RMIregistry, and
//create a stub.  Also the class renames the remote object so that it's easier to
//find for the client and prints a line letting the user know the server is ready.

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class RaceServer 
{
    public static void main(String[] args) 
    {
        //starts up an RMI security manager
        System.setSecurityManager(new RMISecurityManager());
        
        try
        {
            //creates RaceList object
            RaceList aRaceList = new RaceListServant();
            
            //creates stub that is cast to type RaceList for exporting on port 2017
            RaceList stub = (RaceList) UnicastRemoteObject.exportObject(aRaceList, 2017);
            
            //renames the aRaceList object in the RMI registry to RaceList for 
            //the client to pick up from there
            Naming.rebind("RaceList", aRaceList);
            System.out.println("ShapeList server ready");
        }
        
        //catches errors from trying to establish the server and prints them
        catch(Exception e)
        {
            System.out.println("ShapeList server main " + e.getMessage());
        }
    }
}
