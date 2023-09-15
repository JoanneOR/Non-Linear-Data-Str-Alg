
//Author: Joanne O Riordan
//Student Number: R00219398
import java.util.Scanner;


public class RobotMoving {

    public static void printingMatrix(float[][] matrix, int numRowsColumns) { //a two-dimensional array of floats, and an integer representing the number of rows and columns in the matrix.
        System.out.println("Cost Matrix: ");
        for (int i = 0; i < numRowsColumns; i++) { //iterates through each row of the matrix
            for (int j = 0; j < numRowsColumns; j++) {//iterates through each column of the matrix
                System.out.printf("%.2f", matrix[i][j]);//For each element in the matrix, the method prints out its value with two decimal places
                if (j < numRowsColumns - 1) { //checks if it's the last element in the row then prints out a vertical line to separate the columns
                    System.out.print(" | ");
                }
            }
            System.out.println(" |"); // prints out a vertical line for end of row
            
          
            if (i < numRowsColumns - 1) { //If it's not the last row of the matrix 
                for (int j = 0; j < numRowsColumns; j++) { //iterating through each column again
                    System.out.print("-------");  //prints out a row of dashes to separate the rows
                }
                System.out.println();
            }
        }
    }
    
    
    public static void printingRobotMovement(float[][] matrix, int numRowsColumns) {
    
        int rowTracker = 0; // keep track of the current row position
        int columnTracker = 0; //keep track of the current column position

        System.out.println("Robot Direction Path:");
        String movement = "Start ->"; //string variable movement is initialized with the starting direction "Start ->. 
        //it is used to gather the directions that the robot takes as it moves through the matrix 
        //so that the directions can be printed at the end of the function
    
        boolean floatsObtained = false; //used so that the values of the diagonal, right, and down elements are only 
        //calculated once and stored in the variables diagonalValue, rightValue, and downValue. 
        //this value can be used in future calculations instead of adding the two numbers together every time. 
        //this reduces rounding errors
        

        // These variables will be used to store the values of the matrix elements that are diagonal, right  and below the current position of the robot
        float diagonalValue = 0f;
        float rightValue = 0f; 
        float downValue = 0f;
        //0f meansfloat value of 0

        while(!(columnTracker == numRowsColumns - 1 && rowTracker == numRowsColumns - 1)) {//traverses through the matrix until the robot reaches the bottom right corner
        
            //These two if statements are used to check if the robot is on the top or bottom edge or the 
            //left or right edge of the matrix. If it is on an edge the only  direction is right or down.

            if(rowTracker == numRowsColumns - 1) {// checks if the robot has reached the top or bottom edge of the matrix
            
                //columnTracker is incremented by 1 to move the robot to the next column to the right
                columnTracker++;             movement += " Right -> "; //right is added to movement path
            }

            else if(columnTracker == numRowsColumns - 1){ // checks if the robot has reached the left or right edge of the matrix
            
                // If the robot has reached the right edge of the matrix it cant move right anymore so it moves down. 
                //If the robot has reached the left edge of the matrix it cant move left anymore  so it has to move down.
                rowTracker++;           movement += " Down ->";
            }

            //If the robot is not on an edge  the if statement inside the else block is executed. 
            //This block is used to find the cheapest path to the next element
            else{
                    if(!floatsObtained) {// checks if the diagonal, right, and down values have not been obtained yet for the current position and only calculates them once.
                
                    //matrix[rowTracker + 1][columnTracker + 1] represents the diagonal element to the current position which is one row and one column away from the current position, 
                    //while matrix[rowTracker][columnTracker] represents the current element at the current position. 
                    //The minus is used to find the difference between these two values
                    diagonalValue  = matrix[rowTracker + 1][columnTracker + 1] - matrix[rowTracker][columnTracker];

                    //the element that has the same row index as the current position but a column index that is one times greater than the current position
                    rightValue = matrix[rowTracker][columnTracker + 1] -  matrix[rowTracker][columnTracker];

                    //the element that is located one row below the current positionmwhile in the same column
                    downValue =  matrix[rowTracker + 1][columnTracker] -  matrix[rowTracker][columnTracker];

                    floatsObtained = true; // the floatsObtained is set to true meaning that these values have been obtained and 
                    //should not be recalculated in future iterations of the loop
                }
                               
                if(diagonalValue < rightValue + downValue){ //This condition checks if it's cheaper to travel diagonally to the 
                //element that is below and right to the current position compared to moving right then down
                    rowTracker++;       columnTracker++;     movement += " Diagonal ->";
                }

                else {// executed when it is cheaper for the robot to travel right or down instead of diagonally.
                    if(rightValue < downValue) {

                        //columnTracker is incremented by one to move right
                        columnTracker++;        movement += " Right ->";
                    }
                    else {// If it is not cheaper to travel right the robot moves down

                        //incrementing rowTracker by one
                        rowTracker++;          movement += " Down ->";
                    }
                }
               
            }
        }
        System.out.println(movement + " End");//prints the entire movement path of the robot. It uses the movement variable which has been updated 
        //with each movement made by the robot within the matrix. End will be printed indicating that the robot has reached its final point.
    }


    private static float getMathMinimumCost(float rightCost, float downCost, float diagonalCost) {
        //Math.min function is called twice first to find the minimum of rigthcost and downcost
        //and then to find the minimum of the result of the first call and diagonalcost.
        //then minimum of the three input values is returned
        return Math.min(rightCost, Math.min(downCost, diagonalCost));
    }


    private static float getMinimumCost(float matrix[][], int numRowsColumns, float rightCost, float downCost, float diagonalCost) {
    
       matrix[0][0] = 0; //  sets the first element of the matrix to zero

        for (int i = 1; i <= numRowsColumns; i++) {// i represents the current row of the matrix that is being filled
            //matrix[i][0] is the current cell being filled in. It is in the ith row and 0th column in the matrix.
            //matrix[i-1][0]  is the cell directly above the current cell
            //matrix[i][0] value of the current cell before the price of moving down is added.
            //downCost the cost of moving down from the cell above to the current cell
            //process is repeated for all cells in the first column of the matrix to fill in the values for the whole column.
            matrix[i][0] = matrix[i - 1][0] + matrix[i][0] + downCost; // gets the column above and adds the price of going down 
        }
        
        for (int j = 1; j <= numRowsColumns; j++) { // fill in the values for the first row of the matrix
            // cost of reaching that cell is calculated by adding the cost of moving to the left plus the cost of moving to the right 
             matrix[0][j] = matrix[0][j - 1] + matrix[0][j] + rightCost; 
        }

        for (int i = 1; i <= numRowsColumns; i++) {// iterates over the rows of the matrix, starting from the second row since the first row was already filled
            
            for (int j = 1; j <= numRowsColumns; j++){//loop iterates over the columns of the matrix, starting from the second column
            
                //matrix[i][j] is the current cell being filled in.
                matrix[i][j] = getMathMinimumCost( // method is called within the loop to find the minimum of these three values.
                    matrix[i-1][j-1] + matrix[i][j] + diagonalCost, //cost of moving diagonally to the current cell from the cell that is diagonally above and to the left
                    matrix[i-1][j] + matrix[i][j] + downCost, // the cost of moving down to the current cell from the cell directly above.
                    matrix[i][j-1] + matrix[i][j] + rightCost // cost of moving right to the current cell from the cell directly to the left.
                );
            } 
        }

        System.out.println();
        return matrix[numRowsColumns- 1][numRowsColumns- 1]; // last cell of the matrix which is the bottom right cell and the minimum cost
    }

    public static void main(String[] args){

        // user can input the size of the matrix
        System.out.print("Enter the size of the matrix: "); 
        Scanner input = new Scanner(System.in);
        int numRowsColumns = input.nextInt();
        input.close();
        System.out.println("\n");

        //COST1 
        System.out.println("Cost 1");
        float matrix1[][] = new float[numRowsColumns + 1][numRowsColumns + 1]; // store the cost of reaching each cell in the matrix from the starting cell
        //+1" is to account for the additional row and column that will be used as edges to the matrix
        //it works by iteratively filling in this matrix with the minimum cost of reaching each cell from the adjacent cells
        System.out.printf("The minimum cost of reaching the finish point is: %.2f", getMinimumCost(matrix1, numRowsColumns, 1.1f, 1.3f, 2.5f));
       //1.1f, 1.3f, and 2.5f are float values that represent the cost of moving right, down, and diagonally. 
       //getMinimumCost method then iteratively fills in the matrix1 with the minimum cost of reaching each cell from the adjacent cells using the three costs 
       //and the method returns the minimum cost of reaching the finish point
       
        System.out.println("\n");
        printingMatrix(matrix1, numRowsColumns); //takes in the matrix and the number of rows & columns and prints out the matrix 
        System.out.println("\n");
        printingRobotMovement(matrix1, numRowsColumns); //prints out the best path directions by going back from the bottom right cell (end) to the top-left cell (start).
        
        System.out.println("\n");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("\n");


        //COST2
        System.out.println("Cost 2");
        float matrix2[][] = new float[numRowsColumns + 1][numRowsColumns + 1];
        System.out.printf("The minimum cost of reaching the finish point is: %.2f", getMinimumCost(matrix2, numRowsColumns, 1.5f, 1.2f, 2.3f));
        System.out.println("\n");
        printingMatrix(matrix2, numRowsColumns);
        System.out.println("\n");
        printingRobotMovement(matrix2, numRowsColumns);
    } 
}