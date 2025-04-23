/******************************************************************
 *
 *   Zaki Khan / 272 001
 *
 *   This java file contains the problem solutions of canFinish and
 *   numGroups methods with a different implementation approach.
 *
 ********************************************************************/

import java.util.*;

public class ProblemSolutions {

    /**
     * Method canFinish
     *
     * Determines if all courses can be completed given the prerequisites.
     * Uses topological sorting to detect cycles in the directed graph.
     *
     * @param numExams      - number of exams (nodes in graph)
     * @param prerequisites - 2D array of directed edges representing prerequisites
     * @return boolean      - True if all exams can be taken, false otherwise
     */
    public boolean canFinish(int numExams, int[][] prerequisites) {
        // Build adjacency list for the graph
        List<Integer>[] graph = buildDirectedGraph(numExams, prerequisites);
        
        // Calculate in-degree for each node
        int[] inDegree = new int[numExams];
        for (int[] prereq : prerequisites) {
            int dest = prereq[0];
            inDegree[dest]++;
        }
        
        // Initialize queue with nodes having no incoming edges
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numExams; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Process nodes in topological order
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visitedCount++;
            
            // Process all neighbors
            for (int neighbor : graph[current]) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // If we processed all nodes, there's no cycle
        return visitedCount == numExams;
    }
    
    /**
     * Helper method to build directed graph from edges
     * 
     * @param numNodes - number of nodes in graph (labeled 0 through n-1) for n nodes
     * @param edges    - 2-dim array of directed edges
     * @return List<Integer>[] - adjacency list representation
     */
    private List<Integer>[] buildDirectedGraph(int numNodes, int[][] edges) {
        List<Integer>[] adjacencyList = new ArrayList[numNodes];
        
        // Initialize all adjacency lists
        for (int i = 0; i < numNodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        
        // Add edges to graph (note: reversed for prerequisites)
        for (int[] edge : edges) {
            adjacencyList[edge[1]].add(edge[0]); // Reverse edge for prerequisites
        }
        
        return adjacencyList;
    }
    
    /**
     * Method numGroups
     *
     * Determines the number of connected groups in an undirected graph.
     *
     * @param adjMatrix - adjacency matrix representing an undirected graph
     * @return int      - the number of connected groups (or components)
     */
    public int numGroups(int[][] adjMatrix) {
        int n = adjMatrix.length;
        boolean[] visited = new boolean[n];
        int groups = 0;
        
        // Find all connected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                groups++; // Found a new component
                dfs(adjMatrix, visited, i);
            }
        }
        
        return groups;
    }
    
    /**
     * Helper Method DFS
     *
     * Conducts a depth-first search (DFS) on an undirected graph to visit all
     * connected nodes in the current group.
     *
     * @param adjMatrix - adjacency matrix representing the graph
     * @param visited   - boolean array to track visited nodes
     * @param node      - the current node being visited
     */
    private void dfs(int[][] adjMatrix, boolean[] visited, int node) {
        // Mark current node as visited
        visited[node] = true;
        
        // Check all possible neighbors
        for (int neighbor = 0; neighbor < adjMatrix.length; neighbor++) {
            // If there's an edge in either direction (undirected graph) and the neighbor hasn't been visited
            if ((adjMatrix[node][neighbor] == 1 || adjMatrix[neighbor][node] == 1) && !visited[neighbor]) {
                dfs(adjMatrix, visited, neighbor);
            }
        }
    }
}
