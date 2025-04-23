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
 * Graph traversal exercise
 *
 * The Graph class is a representing an oversimplified Directed Graph of vertices
 * (nodes) and edges. The graph is stored in an adjacency list
 */
public class Graph {
    int numVertices;                  // vertices in graph
    LinkedList<Integer>[] adjListArr; // Adjacency list
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

    /**
     * Sets a vertex's (node's) value.
     * @param vertexIndex the index of the vertex to set
     * @param value the value to set for the vertex
     * @throws IllegalArgumentException if vertexIndex is invalid
     */
    public void setValue(int vertexIndex, int value) {
        if (vertexIndex >= 0 && vertexIndex < numVertices) {
            vertexValues.set(vertexIndex, value);
        } else {
            throw new IllegalArgumentException(
                   "Invalid vertex index: " + vertexIndex);
        }
    }

    /**
     * Adds a directed edge from src to dest
     * @param src the source vertex index
     * @param dest the destination vertex index
     */
    public void addEdge(int src, int dest) {
        adjListArr[src].add(dest);
    }

    /**
     * Prints the graph as an adjacency matrix
     */
    public void printGraph() {
        System.out.println("\nAdjacency Matrix Representation:\n");
        int[][] matrix = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (Integer dest : adjListArr[i]) {
                matrix[i][dest] = 1;
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
     * Finds the root vertex of the graph (vertex with no incoming edges)
     * @return the value of the root vertex, or -1 if there is no unique root
     */
    public int findRoot() {
        int[] incomingEdges = new int[numVertices];
        
        // Count incoming edges for each vertex
        for (int i = 0; i < numVertices; i++) {
            for (Integer dest : adjListArr[i]) {
                incomingEdges[dest]++;
            }
        }
        
        int rootCount = 0;
        int rootVertex = -1;
        
        // Find vertices with no incoming edges
        for (int i = 0; i < numVertices; i++) {
            if (incomingEdges[i] == 0) {
                rootCount++;
                rootVertex = i;
            }
        }
        
        // Return the root's value if exactly one exists, else -1
        return (rootCount == 1) ? vertexValues.get(rootVertex) : -1;
    }
}
