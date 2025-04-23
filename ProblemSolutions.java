/******************************************************************
 *
 *   Zaki Khan / 272 001
 *
 *   This java file contains the problem solutions of canFinish and
 *   numGroups methods.
 *
 ********************************************************************/

import java.util.*;

public class ProblemSolutions {

    /**
     * Method canFinish
     *
     * Determines if all exams can be completed given the prerequisites.
     *
     * @param numExams      - number of exams (nodes in graph)
     * @param prerequisites - 2D array of directed edges representing prerequisites
     * @return boolean      - True if all exams can be taken, false otherwise
     */
    public boolean canFinish(int numExams, int[][] prerequisites) {
        // Create adjacency list
        ArrayList<Integer>[] adjList = getAdjList(numExams, prerequisites);

        // Create in-degree array
        int[] inDegree = new int[numExams];
        for (int[] prereq : prerequisites) {
            int dest = prereq[0];
            inDegree[dest]++;
        }

        // Add all nodes with in-degree 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numExams; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Perform BFS
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visitedCount++; // Count the node as visited

            for (int neighbor : adjList[current]) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If all nodes are visited, there's no cycle; otherwise, there's a cycle.
        return visitedCount == numExams;
    }

    /**
     * Method getAdjList
     *
     * Builds an adjacency list based on directed edges.
     *
     * @param numNodes - number of nodes in graph (labeled 0 through n-1) for n nodes
     * @param edges    - 2-dim array of directed edges
     * @return ArrayList<Integer>[] - An adjacency list representing the provided graph.
     */
    private ArrayList<Integer>[] getAdjList(int numNodes, int[][] edges) {
        ArrayList<Integer>[] adjList = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adjList[edge[1]].add(edge[0]); // Reverse edge for prerequisites
        }
        return adjList;
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

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                groups++; // Start a new group
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
        visited[node] = true;

        for (int neighbor = 0; neighbor < adjMatrix.length; neighbor++) {
            // If there's an edge in either direction (undirected graph) and the neighbor hasn't been visited
            if ((adjMatrix[node][neighbor] == 1 || adjMatrix[neighbor][node] == 1) && !visited[neighbor]) {
                dfs(adjMatrix, visited, neighbor);
            }
        }
    }
}
