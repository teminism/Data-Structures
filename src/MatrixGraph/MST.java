package MatrixGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MST {
    static Graph getRandomGraph(int n){
        int randomWeight = (int) (Math.random()*(10-1)+1);
        Graph randomG = new MatrixGraph(n,Graph.UNDIRECTED_GRAPH);
        for (int i = 0; i < 20; i++){
            for(int y = 0; y < 20; y++){
                randomG.addEdge(i,y,randomWeight);
            }
        }
        return randomG;
    }
    static double getTotalEdgeWeight(Graph g) {
        double totalWeightofEdges = 0;
        for (int i = 0; i < g.numVertices(); i++) {
            for (int j = i + 1; j < g.numVertices(); j++) {
                if (g.isEdge(i, j)) totalWeightofEdges += g.weight(i, j);
            }
        }
        return totalWeightofEdges;
    }

    static boolean isConnected(Graph g) {
        boolean[] visited = new boolean[g.numVertices()];
        Arrays.fill(visited, false);

        ConcurrentLinkedQueue<Integer> searchQueue = new ConcurrentLinkedQueue<>();
        searchQueue.add(0);

        while (searchQueue.stream().count() != 0) {
            int i = searchQueue.poll();
            for (int n : g.neighbours(i)) {
                if (!visited[n]) searchQueue.add(n);
            }
            visited[i] = true;
        }
        for (boolean b : visited)
            if (!b) return false;
        return true;
    }



    /*----------------- 3 ----------------- */
    // 3 - Minimum spanning trees
    static Graph makeMST(Graph g) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < g.numVertices(); i++) {
            for (int j = i + 1; j < g.numVertices(); j++) {
                if (g.isEdge(i, j)) {
                    //edges.add(new Edge(i, j, g.weight(i, j)));
                }
            }
        }
        Collections.sort(edges);
        Collections.reverse(edges);

        for (Edge i : edges) {
            g.deleteEdge(i.x, i.y);
            if (!isConnected(g)) {
                g.addEdge(i.x, i.y, i.w);
            }
        }
        return g;
    }

     /* public static void main(String[] args) {
        Graph essexGraph = GraphOfEssex.getGraph();
        makeMST(essexGraph);
        double essexGraphWeight = getTotalEdgeWeight(essexGraph);
        System.out.println("A) The Graph of Essex MST total weight is " + essexGraphWeight);

        double weightSum = 1;
        for (int i = 0; i < 20; i++) {
            Graph graphs = getRandomGraph(100);
            makeMST(graphs);
            weightSum += getTotalEdgeWeight(graphs);
        }
        double avgMST = weightSum / 20;
        System.out.println ("B) The average weight of these MSTs is, " + avgMST);

    } */


}
