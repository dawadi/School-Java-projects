//Kurt Whalen
//Race List Servant implements the RaceList interface and creates new Race entries
// and returns the list of races

import java.rmi.RemoteException;
import java.util.Vector;

public class RaceListServant implements RaceList
{
    private Vector raceList;
    
    //Creates a racelistservant
    public RaceListServant() throws RemoteException
    {
        raceList = new Vector();
    }
    
    //adds a race to the list
    public void newRace(Race r) throws RemoteException
    {     
        raceList.addElement(r);    
    }
    
    //returns all races
    public Vector allRaces() throws RemoteException
    {
        return raceList;
    }
}
