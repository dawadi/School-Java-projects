//Kurt Whalen
//RaceClient remotely requests information from the racelist and can create new races
import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

public class RaceClient
{
    public static void main(String args[])
    {
        String name = "N/A";
        String loca = "N/A";
        int year = 0;
        double dist = 0;
        double time = 0;
        double rate = 0;
        String option = "Read";
        if(args.length > 0)  option = args[0];	// read, write, or question
        
        //if the first argument is Write you must have the full race information
        //to add the race
        if(option.equals("Write") && args.length == 7)
        {
            name = args[1];
            loca = args[2];
            
            //parses string arg into int
            year = Integer.parseInt(args[3]);
            //checks year boundaries
            if(year < 1900 || year > 2020)
            {
                System.out.println("The Year arg must be between 1900 and 2020");
                System.exit(0);
            }
            
            //parsing again the next few are doubles though
            dist = Double.parseDouble(args[4]);
            //checking distance boundaries
            if(dist < 0 || dist > 70)
            {
                System.out.println("The Distance arg must be above 0 and below 70");
                System.exit(0);
            }
            
            //parsing
            time = Double.parseDouble(args[5]);
            //checking time boundaries
            if(time < 0 || time > 500)
            {
                System.out.println("The Time arg must be above 0 and below 70 and "
                        + "is measured in minutes");
                System.exit(0);
            }
            
            //last parsing
            rate = Double.parseDouble(args[6]);
            if(rate < 0 || rate > 10)
            {
                System.out.println("The Rating arg must be above 0 and below 10");
                System.exit(0);
            }
        }
        
        //in case you didn't enter 7 arguments then it exits upon write command
        else if(option.equals("Write") && args.length < 7)
        {
            System.out.println("To write a race entry you must enter the arguments: "
                    + "Write Name Location Year Dist Time Rating");
            System.exit(0);
        }
        
        //checks if Question is asked that there is a second arg to ask the question
        if(option.equals("Question") && args.length < 2)
        {
            System.out.println("A second argument is needed to answer a question"
                    + "the options are: Longest, Mostrun, Best, Submissions, "
                    + "(Submissions and a Name arg after counts the subs for that race), and List");
            System.exit(0);
        }
        
        //checks if security manager is started or not yet
        if(System.getSecurityManager() == null)
        {	
            System.setSecurityManager(new RMISecurityManager());
        } 
        else 
            System.out.println("Already has a security manager, so cant set RMI SM");
        
        //initialize object before populating it
        RaceList aRaceList = null;
        double longest = 0;
        String title = "N/A";
        try
        {
            //uses naming lookup to find server at URL
            aRaceList = (RaceList) Naming.lookup("//uxb4.wiu.edu/home/kmw136/CS513/assn3/whalk2S/RaceList");
            System.out.println("Found server");
            //pulls the vectorlist from the remote object and puts in it rList
            Vector rList = aRaceList.allRaces();
            System.out.println("Got vector");
            
            if(option.equals("Write"))
            {
                Race r = new Race(name, loca, year, dist, time, rate);
                System.out.println("Created Race");
                aRaceList.newRace(r);
                System.out.println("Stored Race");
            }
            else if(option.equals("Read"))
            {
                for(int i=0; i<rList.size(); i++)
                {
                    ((Race)rList.elementAt(i)).print();
                }
            } 
            else if(option.equals("Question"))
            {
                //if you want to know the longest race involved
                if(args[2].equals("Longest"))
                {
                    for(int i = 0; i < rList.size(); i++)
                    {
                        if(longest < ((Race)rList.elementAt(i)).rDist)
                        {
                            longest = ((Race)rList.elementAt(i)).rDist;
                            title = ((Race)rList.elementAt(i)).rName;
                        }
                    }
                    System.out.println("The longest race in these records is: " + title);
                    System.exit(0);
                }
                
                //the submissions of a certain race and submissions for all races 
                //start here and branch according to a name being put in the args or not
                if(args.length > 2 && args[2].equals("Submissions")) 
                //will attempt to search for the third arg as if it were a race name
                {
                    int count = 0;
                    for(int i = 0; i < rList.size(); i++)
                    {
                        if(args[3].equals(((Race)rList.elementAt(i)).rName))
                        {
                            count++;
                        }
                        System.out.println("The number of submissions for that race is: " + count);
                        System.exit(0);
                    }
                }
                
                //for the total submissions request instead of named
                else if(args[2].equals("Submissions") && args.length < 3)
                {
                    int countB = 0;
                    for(int i = 0; i < rList.size(); i++)
                    {
                        countB++;
                    }
                    System.out.println("The number of total submissions made is: " + countB);
                    System.exit(0);
                }
                
                //for listing out the names of all races
                if(args[2].equals("List"))
                {
                    System.out.print("List of all races in these records: ");
                    for(int i = 0; i < rList.size(); i++)
                    {
                        System.out.print(((Race)rList.elementAt(i)).rName + " ");
                    }
                }
            }
        }
        
        catch(RemoteException e) 
        {
            System.out.println("allRaces: " + e.getMessage());    
        }
        
        catch(Exception e) 
        {
            System.out.println("Lookup: " + e.getMessage());
        }
    }
}
