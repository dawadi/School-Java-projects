




public class Rollto100 {

    public static void main(String[] args)
    {
       
       int i = 0;
       while(i < 100)
       {
           int p = 0;
           Die die1 = new Die();
           Die die2 = new Die();
           p += die1.getValue() + die2.getValue();
           i += p;
           StdDraw.square(0.75, 0.5, 0.2); //die 1
           StdDraw.square(0.25, 0.5, 0.2); //die 2
           die1.draw(die1.getValue(), 0.75, 0.5);
           die2.draw(die2.getValue(), 0.25, 0.5);
           StdDraw.text(0.25, 0.25, "Current sum: " + p);
           StdDraw.text(0.75, 0.25, "Overall sum: " + i);
           StdDraw.show(2000);
           StdDraw.clear();
       }
    }
}
