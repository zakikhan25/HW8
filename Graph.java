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
 *  The Graph class is a representing an oversimplified Directed Graph of vertices
 *  (nodes) and edges. The graph is stored in an adjacency list
 */
public class Graph {
  int numVertices;                  // vertices in graph
  LinkedList[] adjListArr; // Adjacency list
  List<Integer> vertexValues;       // vertex values
  // Constructor
  public Graph(int numV) {
    numVertices = numV;
    adjListArr = new LinkedList[numVertices];
    vertexValues = new ArrayList<>(numVertices);
    for (int i = 0; i < numVertices; i++) {
      adjListArr[i] = new LinkedList<>();
      vertexValues.add(0);
    }
  }
  /*
   * method setValue
   *
   * Sets a vertex's (node's) value.
   */
  public void setValue(int vertexIndex, int value) {
    if (vertexIndex >= 0 && vertexIndex < numVertices) {
      vertexValues.set(vertexIndex, value);
    } else {
      throw new IllegalArgumentException(
              "Invalid vertex index: " + vertexIndex);
    }
  }
  public void addEdge(int src, int dest) {
    adjListArr[src].add(dest);
  }
  /*
   * method printGraph
   *
   * Prints the graph as an adjacency matrix
   */
  public void printGraph() {
    System.out.println(
            "\nAdjacency Matrix Representation:\n");
    int[][] matrix = new int[numVertices][numVertices];
    for (int i = 0; i < numVertices; i++) {
      for (Object dest : adjListArr[i]) {
        matrix[i][(int) dest] = 1;
      }
    }
    System.out.print("  ");
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < numVertices; j++) {
        if (matrix[i][j] == 1) {
          System.out.print("| ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }
  /**
   * method findRoot
   *
   * This method returns the value of the root vertex, where root is defined in
   * this case as a node that has no incoming edges. If no root vertex is found
   * and/or more than one root vertex, then return -1.
   */
  public int findRoot() {
    int[] inDegree = new int[numVertices];
    // Count incoming edges for each vertex
    for (int i = 0; i < numVertices; i++) {
      for (Object neighbor : adjListArr[i]) {
        inDegree[(int) neighbor]++;
      }
    }
    int root = -1; // Variable to store the root vertex
    for (int i = 0; i < numVertices; i++) {
      if (inDegree[i] == 0) { // Check for vertices with no incoming edges
        if (root != -1) { // If we already found a root
          return -1; // More than one root found
        }
        root = i; // Set the current vertex as root
      }
    }
    return root == -1 ? -1 : vertexValues.get(root); // Return the value of the root or -1 if none found
  }
}
