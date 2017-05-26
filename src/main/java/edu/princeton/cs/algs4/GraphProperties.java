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

/**
 * The eccentricityof a vertex vis the the length of the shortest path from that vertex to the furthest vertex from v. The diameterof a graph is the maximum eccentricity
 * of any vertex. The radiusof a graph is the smallest eccentricity of any vertex. A centeris
 * a vertex whose eccentricity is the radius. Implement the following API
 */
public class GraphProperties {
    private Graph g;
    private int diameter;
    private int radius;
    private int center;

    public GraphProperties(Graph G) {
        g = G;

        int[] dists = new int[g.V()];
        for (int i=0;i<g.V();i++){
            dists[i] = eccentricity(i);
        }

        diameter = dists[0];
        radius = dists[0];
        center = 0;
        for (int i=1;i<g.V();i++){
            if(dists[i] > diameter){
                diameter = dists[i];
            }

            if(dists[i] < radius){
                radius = dists[i];
                center = i;
            }
        }
    }

    public int eccentricity(int v){
        BreadthFirstPaths bfp = new BreadthFirstPaths(g,v);
        int max = 0;
        for (int i=0;i<g.V();i++){
            int dist = bfp.distTo(i);
            if(dist > max){
                max = dist;
            }
        }

        return max;
    }

    public int diameter(){
        return diameter;
    }

    public int radius(){
        return radius;
    }

    public int center(){
        return center;
    }

    /**
     * Unit tests the {@code DepthFirstSearch} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In("data/algs4-data/4.1.16.txt");
        Graph G = new Graph(in);
        GraphProperties p = new GraphProperties(G);
        StdOut.println("diameter " + p.diameter);
        StdOut.println("radius " + p.radius);
        StdOut.println("center " + p.center);
    }

}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
