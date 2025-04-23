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
     * Determines if certification exams can be completed given prerequisites
     * @param numExams total number of exams to complete
     * @param prerequisites array of [exam, prerequisite] pairs
     * @return true if possible to complete all exams, false if cyclic dependency
     */
    public boolean canFinish(int numExams, int[][] prerequisites) {
        // Build graph representation
        ArrayList<Integer>[] adj = getAdjList(numExams, prerequisites);
        int[] incomingEdges = new int[numExams];
        
        // Calculate incoming edges for each node
        for (int[] prereq : prerequisites) {
            incomingEdges[prereq[0]]++;
        }
        
        // Initialize queue with root nodes (no prerequisites)
        Queue<Integer> readyQueue = new LinkedList<>();
        for (int exam = 0; exam < numExams; exam++) {
            if (incomingEdges[exam] == 0) {
                readyQueue.add(exam);
            }
        }
        
        int completedExams = 0;
        while (!readyQueue.isEmpty()) {
            int current = readyQueue.remove();
            completedExams++;
            
            // Update incoming edge counts for dependents
            for (int dependent : adj[current]) {
                if (--incomingEdges[dependent] == 0) {
                    readyQueue.add(dependent);
                }
            }
        }
        
        return completedExams == numExams;
    }

    /**
     * Constructs adjacency list from edge list
     * @param numNodes total nodes in graph
     * @param edges array of directed edges
     * @return adjacency list representation
     */
    private ArrayList<Integer>[] getAdjList(int numNodes, int[][] edges) {
        ArrayList<Integer>[] adj = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[1]].add(edge[0]);
        }
        return adj;
    }

    /**
     * Counts connected groups in undirected graph
     * @param adjMatrix adjacency matrix representation
     * @return number of connected components
     */
    public int numGroups(int[][] adjMatrix) {
        int groupCount = 0;
        boolean[] visited = new boolean[adjMatrix.length];
        
        for (int person = 0; person < adjMatrix.length; person++) {
            if (!visited[person]) {
                groupCount++;
                exploreGroup(adjMatrix, visited, person);
            }
        }
        
        return groupCount;
    }

    /**
     * Recursively explores all connections in a group
     * @param adjMatrix adjacency matrix
     * @param visited tracks visited nodes
     * @param current current node being visited
     */
    private void exploreGroup(int[][] adjMatrix, boolean[] visited, int current) {
        visited[current] = true;
        
        for (int friend = 0; friend < adjMatrix.length; friend++) {
            if (adjMatrix[current][friend] == 1 && !visited[friend]) {
                exploreGroup(adjMatrix, visited, friend);
            }
        }
    }
}
