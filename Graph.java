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
 *  The Graph class represents a directed graph using adjacency lists
 *  with methods for graph manipulation and analysis
 */

public class Graph {
    private final int numVertices;          // Total vertices in graph
    private final LinkedList<Integer>[] adjListArr;  // Adjacency list storage
    private final List<Integer> vertexValues;        // Stores vertex values

    /**
     * Constructor initializes graph with specified number of vertices
     * @param numV Number of vertices in the graph
     */
    public Graph(int numV) {
        numVertices = numV;
        adjListArr = new LinkedList[numVertices];
        vertexValues = new ArrayList<>(numVertices);

        // Initialize each vertex's adjacency list and default value
        for (int i = 0; i < numVertices; i++) {
            adjListArr[i] = new LinkedList<>();
            vertexValues.add(0);
        }
    }

    /**
     * Assigns a value to a specific vertex
     * @param vertexIndex Index of vertex to modify (0-based)
     * @param value Value to assign to vertex
     * @throws IllegalArgumentException for invalid vertex indices
     */
    public void setValue(int vertexIndex, int value) {
        if (vertexIndex < 0 || vertexIndex >= numVertices) {
            throw new IllegalArgumentException(
                "Vertex index must be between 0 and " + (numVertices-1));
        }
        vertexValues.set(vertexIndex, value);
    }

    /**
     * Adds directed edge between two vertices
     * @param src Source vertex index
     * @param dest Destination vertex index
     */
    public void addEdge(int src, int dest) {
        adjListArr[src].add(dest);
    }

    /**
     * Displays graph as adjacency matrix with row/column headers
     */
    public void printGraph() {
        System.out.println("\nAdjacency Matrix Representation:\n");
        
        // Initialize matrix with 0s
        int[][] matrix = new int[numVertices][numVertices];
        
        // Populate matrix from adjacency lists
        for (int src = 0; src < numVertices; src++) {
            for (int dest : adjListArr[src]) {
                matrix[src][dest] = 1;
            }
        }

        // Print column headers
        System.out.print("  ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Print each row with row header
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrix[i][j] == 1 ? "| " : ". ");
            }
            System.out.println();
        }
    }

    /**
     * Identifies the root vertex (no incoming edges)
     * @return Value of root vertex, or -1 if none or multiple exist
     */
    public int findRoot() {
        int[] incomingCount = new int[numVertices];  // Track incoming edges

        // Count incoming edges for each vertex
        for (LinkedList<Integer> edges : adjListArr) {
            for (int dest : edges) {
                incomingCount[dest]++;
            }
        }

        Integer rootIndex = null;  // Using Integer to distinguish 0 from unset
        
        // Check all vertices for root candidates
        for (int i = 0; i < numVertices; i++) {
            if (incomingCount[i] == 0) {
                if (rootIndex != null) {  // Already found a root
                    return -1;  // Multiple roots
                }
                rootIndex = i;
            }
        }

        return (rootIndex != null) ? vertexValues.get(rootIndex) : -1;
    }
}
