/* Written by Kurt Whalen and Fidelia Awenegieme
 * Advanced Artifical Intelligence
 * Final Project
 */

import java.util.Random;
import java.util.*;
 
public class CompetitiveLearning
{
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
    
    public double[][] setWeights(int f, int s)
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
    
    public String[] getActual(double[][]targ)
    {
        String[] animal = new String[100];
        String[] type = {"Invertibrate", "Insect", "Amphibian", "Fish", "Reptile", "Bird", "Mammal"};
        for(int i = 0; i < 100; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                if(targ[i][j] == 1)
                {
                    animal[i] = type[j];
                }
            }
        }
        return(animal);
    }
    public static void main(String args[])
    {
 	 //System.out.println("************************************************************");
 	 //System.out.println("*************** Simple Competitive Learning ****************");
 	 //System.out.println("*************************************************************\n");
         CompetitiveLearning A = new CompetitiveLearning();
         double learning = 0.2;
      	 //System.out.println("Learning Coefficient = " + learning);
         int in = 16;
         int out = 7;
         double[][] input;
         double[][] output = new double [100][7];
         double[][] group = new double [100][7];
         double[][] target;
         double[][] weightO;
         int[] groupCount = new int [7];
         String[] animalType;
         input = A.getInputs(Input.inputs);
         target = A.getTargetOut(Input.inputs);
         weightO = A.setWeights(in, out);
            ////////Running the Algorithm
         animalType = A.getActual(target);
         for(int x = 0; x < 100; x++)
         {
            for(int i = 0; i < 7; i++)
            {
               for(int j = 0; j < 16; j++)
               {
                 //Generating the output values for each output unit
                  output[x][i] += input[x][j] * weightO[j][i];
               }
             }
 
            //Comparing the output units to check for the Winner
           double max = 0;
           int bestOut = 0;
           for(int i = 0; i < 7; i++)
           {   
               if(max < output[x][i])
               {
                   max = output[x][i];
                   bestOut = i;
               }
           }
           group[x][bestOut] = 1;
 	 	 	 	 	 
 	 //Checking the total number of active inputs
            int m=0;
            for(int i = 0; i < 16; i++)
            {
               if(input[x][i] != 0) 
               { m++; }
            }
 
           //Updating the weights for the input nodes to the winning unit
                for(int i = 0; i < 16; i++)
                {
                    weightO[i][bestOut] = weightO[i][bestOut] + learning * ((input[x][i] / m) - weightO[i][bestOut]);
                }
         
//            double total = 0;
//            for(int k = 0; k<16;k++)
//            {
//                for(int i = 0; i < 7; i++)
//                {
//                    total += weightO[k][i];
//                }
//            }
//            
//            total = (1/total);
//             //Normalizing the weights
// 
//            for(int k = 0; k<16;k++)
//            {
//                for(int i = 0; i < 7; i++)
//                {
//                    weightO[k][i] = (weightO[k][i] * total );
//                    weightO[k][i] = (double)(Math.round(weightO[k][i] * 1000)) / 1000;
//                }
//            }
        }
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
 	 	 	 System.out.println("Animals Belonging to Group 1");
 	 	 	 System.out.println("----------------------------");
 	 	 	 for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                 {
                                    groupCount[a]++;
                                    System.out.println("\t Animal " + j + " is actually: " + animalType[j]);    
                                 }
 	 	 	 }
                         System.out.println("Group total is " + groupCount[a] + " animals.");
                         }
 	 	 	 if(a==1)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group 2");
 	 	 	 System.out.println("----------------------------");
 	 	 	 for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                   {
                                    groupCount[a]++;
                                    System.out.println("\t Animal " + j + " is actually: " + animalType[j]);    
                                   }
 	 	 	 }
                         System.out.println("Group total is " + groupCount[a] + " animals.");
                         }
 	 	 	 if(a==2)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group 3");
 	 	 	 System.out.println("----------------------------");
 	 	 	 for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                  {
                                    groupCount[a]++;
                                    System.out.println("\t Animal " + j + " is actually: " + animalType[j]);    
                                  }
 	 	 	 }
                         System.out.println("Group total is " + groupCount[a] + " animals.");
                         }
 	 	 	 if(a==3)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group 4");
 	 	 	 System.out.println("----------------------------");
 	 	 	 for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                    {
                                        groupCount[a]++;
                                        System.out.println("\t Animal " + j + " is actually: " + animalType[j]);    
                                    }
 	 	 	 }
                         System.out.println("Group total is " + groupCount[a] + " animals.");
                         }
 	 	 	 if(a==4)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group 5");
 	 	 	 System.out.println("----------------------------");
 	 	 	 for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                    {
                                        groupCount[a]++;
                                        System.out.println("\t Animal " + j + " is actually: " + animalType[j]);    
                                   }
 	 	 	 }
                         System.out.println("Group total is " + groupCount[a] + " animals.");
                         } 
 	 	 	 if(a==5)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group 6");
 	 	 	 System.out.println("----------------------------");
 	 	 	 for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                   {
                                        groupCount[a]++;
                                        System.out.println("\t Animal " + j + " is actually: " + animalType[j]);    
                                   }
 	 	 	 }
                         System.out.println("Group total is " + groupCount[a] + " animals.");
                         }
 	 	 	 if(a==6)
 	 	 	 {
 	 	 	 System.out.println("----------------------------");
 	 	 	 System.out.println("Animals Belonging to Group 7");
 	 	 	 System.out.println("----------------------------");
 	 	 	 for(int j = 0; j < 100; j++)
                         {
 	 	 	 	 if(group[j][a] == 1)
                                    {
                                        groupCount[a]++;
                                        System.out.println("\t Animal " + j + " is actually: " + animalType[j]);    
                                    }
                         }
                         System.out.println("Group total is " + groupCount[a] + " animals.");
 	 	 	 }
                         a++;  
                }
    }
}