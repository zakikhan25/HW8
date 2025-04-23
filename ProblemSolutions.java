/******************************************************************
 *
 *   Zaki Khan / 272 001
 *
 *   This java file contains the problem solutions of canFinish and
 *   numGroups methods.
 *
 ********************************************************************/

import java.util.*;

class ProblemSolutions {

    /**
     * Determines if all exams can be completed given their prerequisites
     * @param numExams the number of exams/nodes in the graph
     * @param prerequisites array of prerequisite pairs [a,b] meaning b must be taken before a
     * @return true if all exams can be completed, false if there's a cycle
     */
    public boolean canFinish(int numExams, int[][] prerequisites) {
        ArrayList<Integer>[] adj = getAdjList(numExams, prerequisites);
        int[] inDegree = new int[numExams];
        
        // Calculate in-degree for each node
        for (int[] edge : prerequisites) {
            inDegree[edge[0]]++;
        }
        
        // Queue for nodes with no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numExams; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            // Reduce in-degree for neighbors
            for (int neighbor : adj[current]) {
                if (--inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return count == numExams;
    }

    /**
     * Builds an adjacency list for the graph
     * @param numNodes number of nodes in the graph
     * @param edges array of directed edges
     * @return adjacency list representation of the graph
     */
    private ArrayList<Integer>[] getAdjList(int numNodes, int[][] edges) {
        ArrayList<Integer>[] adj = new ArrayList[numNodes];
        for (int node = 0; node < numNodes; node++) {
            adj[node] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            adj[edge[1]].add(edge[0]);
        }
        return adj;
    }

    /**
     * Counts the number of connected groups in an undirected graph
     * @param adjMatrix adjacency matrix representation of the graph
     * @return number of connected components
     */
    public int numGroups(int[][] adjMatrix) {
        int n = adjMatrix.length;
        boolean[] visited = new boolean[n];
        int groups = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                groups++;
                dfs(i, adjMatrix, visited);
            }
        }
        
        return groups;
    }

    /**
     * Depth-first search helper for numGroups
     * @param node current node being visited
     * @param adjMatrix adjacency matrix of the graph
     * @param visited array tracking visited nodes
     */
    private void dfs(int node, int[][] adjMatrix, boolean[] visited) {
        visited[node] = true;
        for (int neighbor = 0; neighbor < adjMatrix.length; neighbor++) {
            if (adjMatrix[node][neighbor] == 1 && !visited[neighbor]) {
                dfs(neighbor, adjMatrix, visited);
            }
        }
    }
}
