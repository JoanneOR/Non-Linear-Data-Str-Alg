
//Name: Joanne O Riordan
//Student Number: R00219398

import java.util.LinkedList;

/*
 *  Implementation of the interface Graph with adjacency matrix.
*/

 
public class GraphAdjMatrix implements Graph{

	// ATTRIBUTES: 
    //TO-DO
    private int[][] matrix; //2D integer array that represents the adjacency matrix of the graph
    private int numVertices; //stores the number of vertices in the graph.
    private boolean directed; //indicates whether the graph is directed (true) or undirected (false).
 
    
    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
    public GraphAdjMatrix(int V, boolean directed) {
    //TO-DO
    this.numVertices = V;
        this.directed = directed; //
        this.matrix = new int[V][V]; // This initializes an empty adjacency matrix with no edges between any vertices.
    }


    // 1. IMPLEMENTATION METHOD numVerts: 
    public int numVerts() { 
    //TO-DO
    return numVertices;
    }
    
   
    // 2. IMPLEMENTATION METHOD numEdges:
    public int numEdges() { 
        //TO-DO
        int count = 0;//  keep track of the number of edges in the graph

        //variable i represents the row index, and the variable a represents the column index
        for (int i = 0; i < numVertices; i++) {
            for (int a = 0; a < numVertices; a++) {
                //checks if the element at the i row and a column of the adjacency matrix is not zero. 
                //If it is not zero it means there is an edge between the vertices represented by those indices. 
                if (matrix[i][a] != 0)
                    count++;  //The count variable is incremented by 1 to indicate that there is one more edge in the graph.
                }
            }

            //If it is undirected, the count variable is divided by 2 to account for that each edge is counted twice in an undirected graph
        if (!directed) {
            count /= 2; 
        }
        return count;
        }


   //  3. IMPLEMENTATION METHOD addEdge:
    public void addEdge(int v1, int v2, int w) {
        //TO-DO
        //checks if the vertex indices v1 and v2 are within the bounds of the adjacency matrix
        if (v1 >= 0 && v1 < numVertices && v2 >= 0 && v2 < numVertices) {
            matrix[v1][v2] = w; //sets the value at matrix[v1][v2] to w indicating there is an edge from v1 to v2 with weight w


            //sets the value at matrix[v2][v1] to w, indicating there is an edge from v2 to v1 with the same weight. 
            //This adds an edge in both directions since the graph is undirected.
            if (!directed) { //
                matrix[v2][v1] = w; 
            }
        }
        
    }
    
   // 4. IMPLEMENTATION METHOD removeEdge:
   public void removeEdge (int v1, int v2)
   {
    //TO-DO

    //  checks if both indices v1 and v2 are within the range between 0 and the total number of vertices
    if (v1 >= 0 && v1 < numVertices && v2 >= 0 && v2 < numVertices) { 
        matrix[v1][v2] = 0; //sets the value of the element in the adjacency matrix representing the edge between vertex v1 and vertex v2 to 0. 
        //This removes the edge between these two vertices.

        // If the graph is undirected remove the edge from v2 to v1 by 
        //setting the matching element in the matrix to 0.
        if (!directed) {
            matrix[v2][v1] = 0; 
        }
    }
   }

    // 5. IMPLEMENTATION METHOD hasEdge:
    public boolean hasEdge(int v1, int v2) {
        //TO-DO
        //accesses the element in the adjacency matrix at position [v1][v2]. If the value is not equal to 0, 
        //then there is an edge between v1 and v2 and the method returns true. Else the method returns false.
        return matrix[v1][v2] != 0;
    }
    
    // 6. IMPLEMENTATION METHOD getWeightEdge:
	public int getWeightEdge(int v1, int v2) {
		//TO-DO
        //accesses the element in the adjacency matrix at position [v1][v2], which represents the edge between v1 and v2.
        // The value of this is the weight of the edge. 
        return matrix[v1][v2];
	}

    
	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList getNeighbors(int v)
	{
    	//TO-DO
        LinkedList<Integer> neighbors = new LinkedList<>(); // creates an empty linked list neighbors to store the neighbors of vertex v
        for (int i = 0; i < numVertices; i++) { //iterates through all vertices in the graph

            //For each vertex i it checks the value of the element in the adjacency matrix at position [v][i].
            // If the value of this element is not equal to 0, then there is an edge between vertices v and i, 
            //meaning that vertex i is a neighbor of vertex v
            if (matrix[v][i] != 0) {
                neighbors.add(i); //then the method adds the index i to the linked list neighbors
            }
        }
        return neighbors; //the method returns the linked list neighbors which has the indices of all neighbors of vertex v
	}
   	
	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) 
	{
	   //TO-DO
       int degree = 0; //count the number of edges incident to vertex v
       for (int i = 0; i < numVertices; i++) {

        //For each vertex i, it checks the value of the element in the adjacency matrix at position [v][i]. If the value of this element is not equal to 0, 
        //then there is an edge between vertices v and i, meaning that vertex v has one more incident edge. 
           if (matrix[v][i] != 0) {
               degree++; //the method increments the variable degree
           }
       }
       //for undirected graphs, each edge is counted twice. 
       //if the graph is undirected, the method divides the degree by 2 before returning it.
       if (!directed) {
           degree /= 2;
       }
       return degree;
	}
	

	// 9. IMPLEMENTATION METHOD toString:
   	public String toString()
    {
    //TO-DO
    StringBuilder strings = new StringBuilder(); //creates a StringBuilder object named strings
    strings.append("Adjacency Matrix\n"); //appends the string Adjacency Matrix as a header 
        //loops through each row and column of the adjacency matrix using two nested for loops
        for (int i = 0; i < numVertices; i++) {
            for (int a = 0; a < numVertices; a++) {
                strings.append(matrix[i][a]).append(" "); //For each element in the matrix, the method appends its value followed by a space character
            }
            strings.append("\n"); //appends a newline character to move to the next row.
        }
        return strings.toString(); //After iterating through all elements in the matrix, it returns the string
    }
}    
