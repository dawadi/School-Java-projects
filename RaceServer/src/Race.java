//Kurt Whalen
//Race is a constructor for the Race Object that holds the information of a single
//Race

import java.io.Serializable;

public class Race implements Serializable
{
    //obvious race variables
    public String rName;
    public String rLoca;
    public int rYear;
    public double rDist;
    public double rTime;
    public double rRate;
    
    //constructor, didn't create a plain constructor as without the info here a race being added would be useless
    //and it wasn't explicitly specified in the assignment documentation
    public Race(String name, String loca, int year, double dist, double time, double rate)
    {
        rName = name;
        rLoca = loca;
        rYear = year;
        rDist = dist;
        rTime = time;
        rRate = rate;
    }

    //print funtion
    public void print()
    {
        System.out.println("Name: " + rName + ", Location: " + rLoca + ", Year: " 
                + rYear + ", Distance: " + rDist + " kilometers, Time: " + rTime
                + " minutes, Satisfaction: " + rRate + "/10.");
    }
}
