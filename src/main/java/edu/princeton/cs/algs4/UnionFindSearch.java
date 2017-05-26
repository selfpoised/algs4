/******************************************************************************
 *  Compilation:  javac DepthFirstSearch.java
 *  Execution:    java DepthFirstSearch filename.txt s
 *  Dependencies: Graph.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                http://algs4.cs.princeton.edu/41graph/mediumG.txt
 *
 *  Run depth first search on an undirected graph.
 *  Runs in O(E + V) time.
 *
 *  % java DepthFirstSearch tinyG.txt 0
 *  0 1 2 3 4 5 6 
 *  NOT connected
 *
 *  % java DepthFirstSearch tinyG.txt 9
 *  9 10 11 12 
 *  NOT connected
 *
 ******************************************************************************/

package edu.princeton.cs.algs4;

public class UnionFindSearch {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int count;           // number of vertices connected to s
    private int parent[];

    /**
     * Computes the vertices in graph {@code G} that are
     * connected to the source vertex {@code s}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public UnionFindSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);

        parent = new int[G.V()];
        for(int i=0;i<G.V();i++){
            parent[i] = i;
        }

        for(int i=0;i<G.V();i++){
            for(int j:G.adj(i)){
                union(i,j);
            }
        }

        for(int i=0;i<G.V();i++){
            marked[i] = find(s,i);
            if(marked[i]){
                count++;
            }
        }
    }

    private boolean find(int p, int q) {
        validateVertex(p);
        validateVertex(q);

        return parent[p] == parent[q];
    }

    private void union(int p, int q){
        validateVertex(p);
        validateVertex(q);
        int pid = parent[p];
        for(int i=0;i<parent.length;i++){
            if(parent[i] == pid){
                parent[i] = parent[q];
            }
        }
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of vertices connected to the source vertex {@code s}.
     * @return the number of vertices connected to the source vertex {@code s}
     */
    public int count() {
        return count;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}