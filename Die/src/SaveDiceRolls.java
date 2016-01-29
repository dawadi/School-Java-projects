import java.io.*;
import java.util.*;
public class SaveDiceRolls {

    public static void main(String[] args)
    {
        Out out;
        out = new Out("DiceRolls.txt");
        Die die1 = new Die();
        for(int i = 0; i < 1000; i++)
        {
           die1.roll();
           out.println(die1.getValue());
        }
        out.close();
    }
}
