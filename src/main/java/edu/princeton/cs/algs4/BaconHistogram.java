/******************************************************************************
 *  Compilation:  javac DegreesOfSeparation.java
 *  Execution:    java DegreesOfSeparation filename delimiter source
 *  Dependencies: SymbolGraph.java Graph.java BreadthFirstPaths.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41graph/routes.txt
 *                http://algs4.cs.princeton.edu/41graph/movies.txt
 *  
 *  
 *  %  java DegreesOfSeparation routes.txt " " "JFK"
 *  LAS
 *     JFK
 *     ORD
 *     DEN
 *     LAS
 *  DFW
 *     JFK
 *     ORD
 *     DFW
 *  EWR
 *     Not in database.
 *
 *  % java DegreesOfSeparation movies.txt "/" "Bacon, Kevin"
 *  Kidman, Nicole
 *     Bacon, Kevin
 *     Woodsman, The (2004)
 *     Grier, David Alan
 *     Bewitched (2005)
 *     Kidman, Nicole
 *  Grant, Cary
 *     Bacon, Kevin
 *     Planes, Trains & Automobiles (1987)
 *     Martin, Steve (I)
 *     Dead Men Don't Wear Plaid (1982)
 *     Grant, Cary
 *
 *  % java DegreesOfSeparation movies.txt "/" "Animal House (1978)"
 *  Titanic (1997)
 *     Animal House (1978)
 *     Allen, Karen (I)
 *     Raiders of the Lost Ark (1981)
 *     Taylor, Rocky (I)
 *     Titanic (1997)
 *  To Catch a Thief (1955)
 *     Animal House (1978)
 *     Vernon, John (I)
 *     Topaz (1969)
 *     Hitchcock, Alfred (I)
 *     To Catch a Thief (1955)
 *
 ******************************************************************************/

package edu.princeton.cs.algs4;

public class BaconHistogram {

    // this class cannot be instantiated
    private BaconHistogram() { }

    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        String source    = args[2];

        // StdOut.println("Source: " + source);

        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.graph();
        cc(G);

//        if (!sg.contains(source)) {
//            StdOut.println(source + " not in database.");
//            return;
//        }
//
//        int s = sg.indexOf(source);
//        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
//        for(int i=0;i<G.V();i++){
//            if(!isMovie(sg.nameOf(i))){
//                if (!bfs.hasPathTo(i)) {
//                    StdOut.println("   " + sg.nameOf(i) + "-> not connected");
//                }else{
//                    int count = 0;
//                    for(int w : bfs.pathTo(i)){
//                        count++;
//                    }
//                    StdOut.println("   " + sg.nameOf(i) + "-> " + (count/2));
//                }
//            }
//        }
    }

    // test if a movie or actor
    private static boolean isMovie(String name){
        //movie name like: 'Breaker' Morant (1980)
        if(name.length() > 6){
            if(name.charAt(name.length()-1) == ')' && name.charAt(name.length()-6) == '('){
                String year = name.substring(name.length()-5,name.length()-1);
                int y = -1;
                try {
                    y = Integer.parseInt(year);
                }catch (Exception e){
                }
                if(y > 0){
                    return true;
                }
            }
        }

        return false;
    }

    private static void cc(Graph G) {
        CC cc = new CC(G);// have to set -Xss2m for vm options lest stackoverflowerror
        //CCBfs cc = new CCBfs(G);

        // number of connected components
        int m = cc.count();
        StdOut.println(m + " components");

        // compute list of vertices in each connected component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            StdOut.println(i + " " + components[i].size());
        }
    }

}