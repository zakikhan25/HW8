/******************************************************************
 *
 *   Zaki Khan / 272 001
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  Graph traversal exercise
 *
 *  The DirectedGraph class represents a simplified Directed Graph of vertices
 *  (nodes) and edges. The graph is stored using an adjacency list structure.
 */
public class DirectedGraph {
  private int vertexCount;                // number of vertices in graph
  private LinkedList<Integer>[] adjacencyList; // Adjacency list representation
  private List<Integer> nodeValues;       // values stored at each vertex
  
  // Constructor
  public DirectedGraph(int vertices) {
    this.vertexCount = vertices;
    this.adjacencyList = new LinkedList[vertexCount];
    this.nodeValues = new ArrayList<>(vertexCount);
    
    // Initialize adjacency lists and default values
    for (int i = 0; i < vertexCount; i++) {
      adjacencyList[i] = new LinkedList<>();
      nodeValues.add(0);  // Default value is 0
    }
  }
  
  /**
   * Sets a specific node's value.
   * 
   * @param nodeIndex the index of the node to modify
   * @param value the new value to assign
   */
  public void assignValue(int nodeIndex, int value) {
    if (nodeIndex < 0 || nodeIndex >= vertexCount) {
      throw new IllegalArgumentException("Node index out of bounds: " + nodeIndex);
    }
    nodeValues.set(nodeIndex, value);
  }
  
  /**
   * Creates a directed edge from source to destination vertex.
   * 
   * @param source the starting vertex
   * @param destination the ending vertex
   */
  public void createEdge(int source, int destination) {
    adjacencyList[source].add(destination);
  }
  
  /**
   * Displays the graph as an adjacency matrix for visualization.
   */
  public void displayGraph() {
    System.out.println("\nAdjacency Matrix Representation:\n");
    
    // Create a temporary matrix representation
    int[][] matrix = new int[vertexCount][vertexCount];
    for (int i = 0; i < vertexCount; i++) {
      for (Integer dest : adjacencyList[i]) {
        matrix[i][dest] = 1;
      }
    }
    
    // Print column headers
    System.out.print("  ");
    for (int i = 0; i < vertexCount; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    
    // Print matrix with row headers
    for (int i = 0; i < vertexCount; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < vertexCount; j++) {
        System.out.print((matrix[i][j] == 1) ? "| " : ". ");
      }
      System.out.println();
    }
  }
  
  /**
   * Finds the root node of the graph.
   * 
   * A root is defined as a node with no incoming edges.
   * Returns the value of the root vertex if exactly one exists,
   * or -1 if no root exists or multiple roots are found.
   * 
   * @return value of the root node or -1
   */
  public int findRootNode() {
    // Track incoming edge count for each vertex
    int[] incomingEdges = new int[vertexCount];
    
    // Count incoming edges
    for (int i = 0; i < vertexCount; i++) {
      for (Integer target : adjacencyList[i]) {
        incomingEdges[target]++;
      }
    }
    
    // Find vertices with no incoming edges
    int rootVertex = -1;
    for (int i = 0; i < vertexCount; i++) {
      if (incomingEdges[i] == 0) {
        // If we already found a root, this means multiple roots exist
        if (rootVertex != -1) {
          return -1;  // Multiple roots found
        }
        rootVertex = i;  // First root found
      }
    }
    
    // Return the value of the root or -1 if no root found
    return (rootVertex == -1) ? -1 : nodeValues.get(rootVertex);
  }
}
