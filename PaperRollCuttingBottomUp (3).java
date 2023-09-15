
//Author: Joanne O Riordan
//Student Number: R00219398

import java.util.Scanner;


public class PaperRollCuttingBottomUp {

   public static void showCutSizes(int pieceLength, int[] sectionLengths) { //pieceLength is the size of the roll that was cut and sectionLengths 
                                                          //is an array that contains the length of the cuts made to get the maximum profit.
      int i = pieceLength; // initializing variable i to size
      while (i > 0) { //continues until i is greater than 0
         System.out.print(sectionLengths[i] + " "); //prints the size of the cut that was made at index i of the sectionLength array, 
                                        //by adding the value of sectionLength[i] to an empty string and then concatenating it with " ".
         i = i - sectionLengths[i]; //updates the value of i to i - sectionLength[i] which is the remaining size of the roll after the cut is made.
      }
      System.out.println(); // prints a newline character to move the cursor to the next line
   }

   public static void calculatePriceAndSizes(int size, float[] price) {
      float[] revenue = new float[size + 1]; //array of floats that stores the maximum profit that can be obtained for each roll size 
      //the + 1 is added to so that the array has enough space to store the maximum profit
      int[] cutLength = new int[size + 1]; // array of integers that stores the length of the last cut

      for (int i = 1; i <= size; i++) { //initializes a loop that iterates from 1 to size
         float maxProfit = Float.MIN_VALUE; //variable called maxProfit is declared and initialized to the minimum possible value of a float 
         // used to keep track of the maximum profit that can be obtained for rolls of size i
         // we need to find the highest price that can be obtained for each roll size i. By initializing the maxProfit variable to the minimum 
         //possible value, we ensure that any value higher than this will be considered as the new maximum profit.
         for (int j = 1; j <= i; j++) {
            float totalPrice = price[j] + revenue[i - j];//calculates the current revenue that can be obtained by cutting a roll of size i into 
            //two pieces a piece of size j and a piece of size i - j.
            //The expression i - j means the size of the remaining piece of the roll after cutting a piece of size j.
            //For example, if the roll size is i = 5 and we cut a piece of size j = 3, then the remaining piece has size i - j = 2
            //By calculating the profit for all possible sizes of the remaining piece the best way to cut the roll into pieces that increases the total profit can be found
            if (totalPrice > maxProfit) { //compares the current profit totalPrice with the previous maximum profit maxProfit
               maxProfit = totalPrice;//the current profit becomes the new maximum profit 
               cutLength[i] = j; //the length of the cut that yields this profit is stored in the cutLength array at index i.
                              //the  array at index i is set to the size of the current cut j.
            }
         }
         revenue[i] = maxProfit; //maxProfit will hold the maximum profit that can be obtained from a rod of size i. 
         //This value is then stored in revenur[i], indicating the maximum profit that can be obtained from a rod of size i.
      }

      System.out.printf("The best revenue of cutting the roll is: %.2f\n", revenue[size]); //prints the maximum profit up to two decimal places, 
      //which is stored in the profit array at index size.
      System.out.println("\n");
      System.out.print("The sequence of cuts made to the roll of size " + size + " were: ");
      showCutSizes(size, cutLength); // this prints the sequence of cuts required to obtain the maximum profit.
                                 //starts at the input size and repeatedly prints the size of the cut that was made to obtain that size until it 
                                 //reaches a size of zero
      System.out.println("\n");
   }

   public static void main(String[] args) {
      float[] rollPrice = {0, 1.2f, 3f, 5.8f, 0f, 10.1f}; // creates an array named rollPrice that contains the prices of the cuts for different lengths of the rod. The first element of the array is set to 0, indicating that there is no price for a cut of length 0. 
      Scanner input = new Scanner(System.in); //Scanner object is used to read input from the user
      System.out.print("Enter the length of the roll: ");
      int rollLength = input.nextInt(); //The integer value entered by the user is stored in the variable rollLength
      input.close();
      System.out.println("\n");

      if (rollLength >= rollPrice.length) { //if the user input size is greater than or equal to the length of the rollPrice array. 
         //If it is, then it means that the user input is greater than the size of the rollPrice array, which would cause an 
         //out of bounds error in calculatePriceAndSizes
         float[] newrollPrices = new float[rollLength + 1];//newrollPrices is a new float array with a length of rollLength+1. 
         //the roll length entered by the user can be greater than the length of the original price array, 
         //a new array with a larger size is created to hold the prices of the rod of the desired size. 
         //The  +1 is added to allocate space for the 0 price at the beginning of the rollPrice array
         //This ensures that the rollprice array has enough capacity to hold prices for all possible cut sizes up to the user input size.
         for (int i = 1; i < rollPrice.length; i++) { //copying the values from the old  array to the new array.
          
            newrollPrices[i] = rollPrice[i];
         }
         rollPrice = newrollPrices; //rollPrice array is updated to point to the new array,  replacing the old array with the new one.
                          //to ensure that the rollprice array is  as large as the  roll length entered by the user
      }
      
      calculatePriceAndSizes(rollLength, rollPrice); //calls the method with the rollLength and rollPrice arrays as arguments which then 
      //calculate the maximum profit that can be obtained by cutting a rod  into pieces of different lengths, 
      //and also determines the sizes of the cuts that yield this maximum profit
   }

}
