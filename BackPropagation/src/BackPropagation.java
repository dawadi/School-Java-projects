/* Written by Kurt Whalen and Fidelia Awenegieme
 * Advanced Artifical Intelligence
 * Final Project
 */

import java.util.Random;

public class BackPropagation {
    
    
public double[][] setWeights(int f, int s) //can be used for input to hidden and hidden to output this way
{
    double[][] first = new double [f][s];
    for(int i = 0; i < f; i++)
    {
        for(int w = 0; w < s; w++)
        {
            Random r = new Random(); 
            first[i][w] = r.nextDouble() * 2 - 1; //creates new random number for each weight
        }
    }
    return(first);
}
public double[][] getInputs(double[][] inputs)
{
    double[][] animals = new double [100][16];
    for(int i = 0; i < 100; i++)
    {
        for(int j = 0; j < 16; j++)
        {
            animals[i][j] = inputs[i][j];
        }
    }
    return animals;
}
public double[][] getTargetOut(double[][] inputs)
{
    double[][] targets = new double [100][7];
    for(int i = 0; i < 100; i++)
    {
        for(int j = 0; j < 7; j++)
        {
            targets[i][j] = inputs[i][j + 16];
        }
    }
    return targets;
}
public double[] getOuts(double[][] first, double[][] second, int f, int s, int c) //sets the nets
{
    double[] net = new double[s];
    double[] sigOut = new double[s];
    for(int i = 0; i < f; i++)
    {
        for(int h = 0; h < s; h++)
        {
            net[h] += first[i][h] * second[c][i]; //compiles individual weights into net
        }
    }
    for(int a = 0; a < s; a++)
    {
        sigOut[a] = 1/(1 + Math.exp(-net[a])); //sigmoid function
    }
    return(sigOut);
}
public double[] getOuts(double[][] first, double[] second, int f, int s) //sets the nets
{
    double[] net = new double[s];
    double[] sigOut = new double[s];
    for(int i = 0; i < f; i++)
    {
        for(int h = 0; h < s; h++)
        {
            net[h] += first[i][h] * second[i]; //compiles individual weights into net
        }
    }
    for(int a = 0; a < s; a++)
    {
        sigOut[a] = 1/(1 + Math.exp(-net[a])); //sigmoid function
    }
    return(sigOut);
}
public boolean testOuts(double[] first, double[][] second, double[][] third, int f, int t) //nested if's return false if value doesn't hit target and returns true otherwise
{
    for(int o = 0; o < f; o++)
    {
       if(second[t][o] == 1) 
       {
         if(first[o] >= 0.9)
         {
             third[t][o] = 1;
         }
         else
         {return false;}
       }
       else if(second[t][o] == 0)
       {
         if(first[o] <= 0.1) 
         {} 
         else
         {return false;}
       }
    }
    return true;
}
public double[] getErrors(int f, double[][] first, double[] second, int t) //gets called if testOut returns false
{
    double[] errO = new double[f];
    for(int o = 0; o < f; o++)
    {
        errO[o] = (first[t][o] - second[o]) * (1 - second[o]) * second[o];
    }
    return(errO);
}
public double[] getErrors(double[] first, double[] second, double[][] third, int f, int s) //overloaded version for hidden error value
{
  double[] errH = new double[f];
  for(int h = 0; h < f; h++)
  {
      for(int o = 0; o < s; o++)
      {
          errH[h] += second[o] * third[h][o];
      }
      errH[h] = errH[h] * (1 - first[h]) * first[h];
  }
  return(errH);
}
public double[][] resetWeights(double[][] first, double[] second, double[] third, int f, int s, double t)
{
    for(int o = 0; o < f; o++)
    {
        for(int h = 0; h < s; h++)
        {
            first[o][h] = first[o][h] + t * second[h] * third[h];
        }
    }
    return(first);
}
public double[][] resetWeights(double[] second, double[][] first, double[][] third, int f, int s, double t, int r)
{
    for(int o = 0; o < f; o++)
    {
        for(int h = 0; h < s; h++)
        {
            first[o][h] = first[o][h] + t * second[h] * third[r][o];
        }
    }
    return(first);
}
public void animalType(double[] out, int s)
{
    String[] type = {"Invertibrate", "Insect", "Amphibian", "Fish", "Reptile", "Bird", "Mammal"};
    for(int i = 0; i < 7; i++)
    {
        if(out[i] >= 0.9)
        { 
            System.out.println("Animal " + s + " belongs to class " + type[i]);
        }
        else
        { }
    }   
}
public static void main(String[] args) 
{
    BackPropagation A = new BackPropagation();
    int in = 16;
    int hid = 5;
    int out = 7;
    int[] groupCount = new int [7];
    double[][] input;
    double[] hidden;
    double[] output;
    double[][] target;
    double[] errorO;
    double[] errorH;
    double[][] weightH;
    double[][] weightO;
    double[][] group = new double[100][7];
    double learn = 0.25;
    input = A.getInputs(Input.inputs);
    target = A.getTargetOut(Input.inputs);
    weightH = A.setWeights(in, hid);
    weightO = A.setWeights(hid, out);
    for(int i = 0; i < 100; i++)
    {
    while(true){
        hidden = A.getOuts(weightH, input, in, hid, i);
        output = A.getOuts(weightO, hidden, hid, out);
    if(A.testOuts(output, target, group, out, i) == true)
    {
       System.out.println("Outputs have reached target values: ");
       for(int a = 0; a < out; a++)
       {
           System.out.println("Output for " + i + "." + a + ". " + output[a]);
       }
       for(int a = 0; a < out; a++)
       {
           if(group[i][a] == 1)
           {
               groupCount[a]++;
           }
       }
       break; //only way to exit loop is to hit target
    }
    else
    {
        System.out.println("Outputs have not met the target values: ");
//       for(int a = 0; a < out; a++)
//       {
//           System.out.println("Output for " + i + "." + a + ". " + output[a]);
//       }
    }
    errorO = A.getErrors(out, target, output, i);
    weightO = A.resetWeights(weightO, errorO, output, hid, out, learn);  
    errorH = A.getErrors(hidden, errorO, weightO, hid, out);
    weightH = A.resetWeights(errorH, weightH, input, in, hid, learn, i);
    }
    }
    System.out.println("System is fully trained.");
    //Printing the results by grouping the answers
 	 System.out.println("\n\n****************************************");
         System.out.println("*************** RESULTS ****************");
         System.out.println("****************************************");
         int a = 0;
                 
         for(int i = 0; i < 7 ;i++)
 	 	 {
 	 	 if(a == 0)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group Invertebrates");
 	 	 	 System.out.println("----------------------------");
                         System.out.println("Number of animals in group " + groupCount[a]);
 	 	 	 for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                 { System.out.println("\t Animal " + j); }
 	 	 	 }
                         }
 	 	 	 if(a==1)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group Insects");
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Number of animals in group " + groupCount[a]);
                         for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                 { System.out.println("\t Animal " + j); }
 	 	 	 }
                         }
 	 	 	 if(a==2)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group Amphibians");
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Number of animals in group " + groupCount[a]);
                         for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                 { System.out.println("\t Animal " + j); }
 	 	 	 }
                         }
 	 	 	 if(a==3)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group Fish");
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Number of animals in group " + groupCount[a]);
                         for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                 { System.out.println("\t Animal " + j); }
 	 	 	 }
                         }
 	 	 	 if(a==4)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group Reptiles");
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Number of animals in group " + groupCount[a]);
                         for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                 { System.out.println("\t Animal " + j); }
 	 	 	 }
                         } 
 	 	 	 if(a==5)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group Birds");
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Number of animals in group " + groupCount[a]);
                         for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                 { System.out.println("\t Animal " + j); }
 	 	 	 }
                         }
 	 	 	 if(a==6)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group Mammals");
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Number of animals in group " + groupCount[a]);
                         for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                 { System.out.println("\t Animal " + j); }
                         }
 	 	 	 }
                         a++;  
                }
}
}
