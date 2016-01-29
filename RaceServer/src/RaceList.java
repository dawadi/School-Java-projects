//Kurt Whalen
//RaceList is an interface that the servant uses to create a Vector list containing 
//all races

import java.rmi.*;
import java.util.Vector;

public interface RaceList extends Remote 
{
    void newRace(Race r) throws RemoteException;
    Vector allRaces() throws RemoteException;   
}
