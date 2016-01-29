/*
   CS250 Spring 2015
   Covert
   Hwk 3 Supplied Die.java
   
 */
import java.util.Random;

public class Die
{
   private final int sides;
   private int currentValue;
   private static Random rand;
   
   public Die()
   {
      sides = 6;
      
      if( rand == null )
         rand = new Random();
      roll();
   }
     
   public void roll()
   {
      currentValue = rand.nextInt(sides)+1;
   }
      
   public int getValue()
   {
      return currentValue;
   }
   
   public String toString()
   {
      return "Die class, current value: " + currentValue;
   }
    
   public void draw(int i, double x, double y )
   {
      if (i > 1)  //draw upper left hand dot
           StdDraw.filledCircle(x - 0.1, y + 0.1, 0.02);
       if (i > 3) //draw upper right hand dot
           StdDraw.filledCircle(x + 0.1, y + 0.1, 0.02);
       if (i == 6) //draw middle left dot
          StdDraw.filledCircle(x - 0.1, y, 0.02);
       if (i % 2 == 1) //if number is odd draw middle dot
           StdDraw.filledCircle(x, y, 0.02);
       if (i == 6) // middle right dot
             StdDraw.filledCircle(x + 0.1, y, 0.02);
       if(i > 3) //draw the bottom left dot for 4, 5, and 6
           StdDraw.filledCircle(x - 0.1, y - 0.1, 0.02);
       if(i > 1) //draw the bottom right dot for all numbers other than 1 
           StdDraw.filledCircle(x + 0.1, y - 0.1, 0.02);
   }
}