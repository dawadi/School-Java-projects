//Kurt Whalen
//Artifical Intelligence
//2/2/15
 
public class Perceptron {
 
int inX;
int inY;
double out;
double weightX;
double weightY;
double outErrcase1s;
double lVar;
double barrier;
int[][] tIN = new int[][] {
 
      {0, 0},
      {0, 1},
      {1, 0},
      {1, 1}
      };
int[] tOUT = new int [4];
 
public boolean train() {
 
    double oldweightX = weightX;
 
    double oldweightY = weightY;
 
    for (int i = 0; i < tIN.length; i++) {
 
      inX = tIN[i][0];
 
      inY = tIN[i][1];
 
      double targetOutput = tOUT[i];
 
      //actual out
 
      out = inX * weightX + inY * weightY;
 
      int actualOutput;
 
      if (out >= barrier) {
 
        actualOutput = 1;
 
      } else {
 
        actualOutput = 0;
}
 
      // updates weight
 
      weightX += lVar * (targetOutput - actualOutput) * inX;
 
      weightY += lVar * (targetOutput - actualOutput) * inY;
 
      System.out.println("input (" + inX + "," + inY + ") "
 
              + "target:" + targetOutput
 
              + " actual:" + actualOutput
 
              + " updated weights:(" + weightX + "," + weightY + ")");
 
}
 
    //checks training
 
    if (weightX == oldweightX && weightY == oldweightY) {
 
      return true;
 
    } else {
 
      return false;
}
}

public static void Case1() {
//or     
//input values
Perceptron case1 = new Perceptron();
case1.weightX = 0.0;
case1.weightY = 0.0;
case1.lVar = 0.5;
case1.barrier = 2;
case1.tOUT[0] = 0;
case1.tOUT[1] = 1;
case1.tOUT[2] = 1;
case1.tOUT[3] = 1;
  
    
 
    //train
 
    for (int i = 1; i <= 21; i++) {
 
      System.out.println("stage: " + i);
 
      boolean trained = case1.train();

      if (trained == true) {
 
        System.out.println("Training Complete for 1-1.");
 
        break;
}
}
}
public static void Case2() {
    //and
    //input values
    Perceptron case2 = new Perceptron();
    case2.weightX = 0.2;
    case2.weightY = 0.5;
    case2.lVar = 0.5;
    case2.barrier = 2;  
    case2.tOUT[0] = 0; 
    case2.tOUT[1] = 0;
    case2.tOUT[2] = 0;
    case2.tOUT[3] = 1;
 
    //train
 
    for (int i = 1; i <= 21; i++) {
 
      System.out.println("stage: " + i);
 
      boolean trained = case2.train();
 
      if (trained == true) {
 
        System.out.println("Training Complete for 1-2.");
 
        break;
}
}
}
public static void Case21() {
     
  //or values
  Perceptron case1 = new Perceptron();
  case1.weightX = 0.5;
  case1.weightY = 0.3;
  case1.lVar = 0.6;
  case1.barrier = 2;  
  case1.tOUT[0] = 0;
  case1.tOUT[1] = 1;
  case1.tOUT[2] = 1;
  case1.tOUT[3] = 1;
  
    
    //train
    for (int i = 1; i <= 21; i++) {

      System.out.println("stage: " + i);

      boolean trained = case1.train();

      if (trained == true) {
        System.out.println("Training Complete for 2-1.");
        break;
      }
    }
}
public static void Case22() {

    //and values
    Perceptron case2 = new Perceptron();
    case2.weightX = 0.4;
    case2.weightY = 0.9;
    case2.lVar = 0.5;
    case2.barrier = 2;  
    case2.tOUT[0] = 0; 
    case2.tOUT[1] = 0;
    case2.tOUT[2] = 0;
    case2.tOUT[3] = 1;
    
    //train
    for (int i = 1; i <= 21; i++) {

      System.out.println("stage: " + i);

      boolean trained = case2.train();

      if (trained == true) {
        System.out.println("Training Complete for 2-2.");
        break;
      }
    }
}
public static void Case23() { 
    
    //input values
    Perceptron case3 = new Perceptron();
    case3.weightX = 0.2;
    case3.weightY = 0.8;
    case3.lVar = 0.5;
    case3.barrier = 2;
    case3.tOUT[0] = 1; 
    case3.tOUT[1] = 0;
    case3.tOUT[2] = 0;
    case3.tOUT[3] = 0;

    //train
    for (int i = 1; i <= 21; i++) {

      System.out.println("stage: " + i);

      boolean trained = case3.train();

      if (trained == true) {
        System.out.println("Training Complete for 2-3.");
        break;
      }
    }
}
public static void main(String[] args) {
    
Case1();
Case2();
Case21();
Case22();
Case23();
} 
}

