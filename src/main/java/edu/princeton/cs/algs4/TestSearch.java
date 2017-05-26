package edu.princeton.cs.algs4;

public class TestSearch {
    public static void main(String[] args) {
        In in = new In("data/algs4-data/tinyG.txt");
        Graph G = new Graph(in);
        int s = Integer.parseInt("0");
        UnionFindSearch search = new UnionFindSearch(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();

        if (search.count() != G.V()) {
            StdOut.print("NOT ");
        }
        StdOut.println("connected");
    }
}