
//Name: Joanne O Riordan
//Student Number: R00219398

import java.util.LinkedList;
import java.util.Iterator;

	/**
	* Graph implementation that uses Adjacency Lists to store edges. It
	* contains one linked-list for every vertex i of the graph. The list for i
	* contains one instance of VertexAdjList for every vertex that is adjacent to i.
	* For directed graphs, if there is an edge from vertex i to vertex j then there is
	* a corresponding element in the adjacency list of node i (only). For
	* undirected graphs, if there is an edge between vertex i and vertex j, then there is a
	* corresponding element in the adjacency lists of *both* vertex i and vertex j. The
	* edges are not sorted; they contain the adjacent nodes in *order* of
	* edge insertion. In other words, for a graph, the node at the head of
	* the list of some vertex i corresponds to the edge involving i that was
	* added to the graph least recently (and has not been removed, yet). 
	*/

	public class GraphAdjList implements Graph {

	// ATTRIBUTES: 
	    
	 //TO-DO
	
	private int V; // number of vertices
    private int E; // number of edges
    private boolean directed; // boolean value indicating whether the graph is directed (true) or undirected (false)
    private LinkedList<Edge>[] adjacent; // an array of linked lists of type Edge, representing the adjacency list of the graph.
  

	 /*
	  * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges.
	  * It initializes the array of adjacency edges so that each list is empty.
	  */
	    
	 public GraphAdjList(int V, boolean directed) {
	     
	    //TO-DO
		this.V = V;
        this.directed = directed;
        adjacent = new LinkedList[V];//initializes adjacent as an array of LinkedList<Edge> objects, where the size of the array is equal to V

	   //uses a for loop to create a new LinkedList<Edge> for each vertex in the graph, 
	   //and adds the new linked list to the corresponding position in the adjacent array.
        for (int i = 0; i < V; i++) {
            adjacent[i] = new LinkedList<Edge>();
        }
	 }

	 
	  // 1. IMPLEMENTATION METHOD numVerts: 
	  public int numVerts() { 

	    //TO-DO
		return V; //returns the number of vertices in the graph,
     }

	  // 2. IMPLEMENTATION METHOD numEdges:
	  public int numEdges() { 
	    //TO-DO
		int count = 0;
		for (int i = 0; i < V; i++) { //iterates over all vertices in the graph 
			//For each vertex, the size() method is called on the corresponding linked list to get the number of edges in the vertex's adjacency list. 
			//This value is added to the count variable.
			count += adjacent[i].size();
		}

		//If the graph is undirected the count is divided by 2 before it is returned, 
		// each undirected edge is represented only once in the adjacency list.
		if (!directed) {
			count /= 2;
		}
		return count; //the count variable will contain the total number of edges in the graph. 
	}
	

	    
	  //  3. IMPLEMENTATION METHOD addEdge:
	  public void addEdge(int v1, int v2, int w) {
		// TO DO
		//adds a new Edge object to the adjacency list of v1, which represents an edge between v1 and v2 with weight w.
		//The Edge object is added at the beginning of the list which inserts the new edge at the front
		// vertex indices are 1 based so the first vertex has to have index 1 which is why v1-1 is used 
		adjacent[v1-1].add(0,new Edge(v2,w));
	  }
    
	  
	 // 4. IMPLEMENTATION METHOD removeEdge: 
	 public void removeEdge(int v1, int v2) {
		//TO-DO
		Iterator<Edge> iterate = adjacent[v1 - 1].iterator(); //creates an Iterator over the edges in the adjacency list of vertex v1.
		//while loop that iterates over the elements of the Iterator object. The hasNext() returns true if there are more elements in the iterator
        while (iterate.hasNext()) { 
            Edge edge1 = iterate.next();// gets the next edge object from the iterator and assigns it to the variable edge1

			 //is used to check if  edge1 has the same destination vertex v2 as the one we want to remove
			 //Since vertex indices in the adjacency list are 1 based subtract 1 from v2
			 if (edge1.getVertex() == v2 - 1) {
				// removes the edge between vertices v1 and v2.
                iterate.remove();
                E--; //decrements the count of the total number of edges in the graph

				//starts a new iterator for the adjacency list of vertex v2 - 1. 
				//If the graph is undirected, removing the edge from the adjacency list of vertex v1 also 
				//requires removing the corresponding edge from the adjacency list of vertex v2, because the edge is bidirectional.
                if (!directed) {
                    Iterator<Edge> iterate2 = adjacent[v2 - 1].iterator();
					// starts a loop that iterates over the adjacency list of vertex v2.
					// It is used to find the edge going in the opposite direction from v2 to v1
                    while (iterate2.hasNext()) {
						//creates a new Edge object that refers to the next element in the iterator 
						//The iterate2.next() method moves the iterator to the next element in the adjacency list of v2
                        Edge edge2 = iterate2.next();

						//checking if the vertex of edge2 is the same as the index v1 - 1 in the adjacency list of v2.
						// If this condition is true, then the second edge to be removed has been found
						//If edge2 has destination vertex v1, it means that there is an edge from v2 to v1,
						// so we remove edge2 from the list using iterate2.remove() and decrement the edge count E by 1
                        if (edge2.getVertex() == v1 - 1) {
                            iterate2.remove();
                            E--;
                            break;// We then exit the loop using break because we have removed the edge we were looking for
                        }
                    }
                }
                break;
            }
        }
	 }
	 
	 // 5. IMPLEMENTATION METHOD hasEdge:
	 public boolean hasEdge(int v1, int v2) {
		 //TO-DO
		 Iterator<Edge> iterate = adjacent[v1 - 1].iterator(); // iterator is created for the adjacency list of vertex v1
		 while (iterate.hasNext()) {//used to iterate over the adjacency list of vertex v1
			 Edge edge = iterate.next();//gets the next element in the iterator and assigns it to variable edge
			 //checking if the vertex of the edge in the adjacency list of vertex v1 is the same as the vertex v2 which is what we are searching the adjacency list for
			 //If true it means there is an edge between vertex v1 and vertex v2
			 if (edge.getVertex() == v2) { 
				 return true;
			 }
		 }
		 return false; //there is no edge between vertex v1 and vertex v2, and returns false.
	 }

	// 6. IMPLEMENTATION METHOD getWeightEdge:
	 public int getWeightEdge(int v1, int v2) {
	    //TO-DO
		Iterator<Edge> iterate = adjacent[v1].iterator();
		while (iterate.hasNext()) {//iterating over the edges in the adjacency list of vertex v1
			Edge edge = iterate.next();
			//For each edge it checks if the adjacent vertex of edge is v2. If it is, it returns the weight of edge
			if (edge.getVertex() == v2) {
				return edge.getWeight();
			}
		}
		//If it finishes iterating over all edges in the adjacency list of v1 without finding an edge with adjacent vertex equal to v2, 
		//it returns 0 meaning that the edge doesn't exist.
		return 0; 
	 }

	// 7. IMPLEMENTATION METHOD getNeighbors:
	 public LinkedList getNeighbors(int v) {
	     //TO-DO

		 //returns the adjacency list of the vertex v which is a linked list containing all the edges that are incident to the vertex v
		 return adjacent[v]; 
	 }

    // 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) {
		//TO-DO
		// the number of edges incident on v
		return adjacent[v].size(); //returning the size of the linked list of edges that corresponds to the vertex v
	}

	// 9. IMPLEMENTATION METHOD toString:
	 public String toString() {
	     //TO-DO
		 StringBuilder strings = new StringBuilder(); //Creates a StringBuilder object to build the string
    strings.append(V + " vertices, " + E + " edges\n"); //Append the number of vertices and edges in the graph 
    for (int v = 0; v < V; v++) {
        strings.append(v + ": ");//Append the vertex index and a colon 
        for (Edge edges : adjacent[v]) { //for each vertex v, it loops through all the edges that are adjacent to v
            strings.append(edges + " "); //append each edge and a space after each edge to separate them.
        }
        strings.append("\n"); //Append a newline character to separate the next vertex
    }
    return strings.toString(); //Return the string 

	}

}


