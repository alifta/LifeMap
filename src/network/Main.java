package network;

import edu.princeton.cs.algs4.StdOut;

public class Main {

    public static void main(String[] args) {

        String DB = "icc.db";
        String input;
//         input = "/home/alift/cloud/java/network/data/tinyEWD1.txt";
        input = "/Users/alift/cloud/java/network/data/tinyEWD1.txt";

        // create random network
        // StaticNetwork G = new StaticNetwork(10000, 200000);

        // create the network from file
        StaticNetwork G = new StaticNetwork(input, " ");

        // create the network from DB
        // StaticNetwork G = new StaticNetwork(DB);

        // OUTPUT
        StdOut.println(G);
        G.print();

        G.histogram();

        // ALGORITHM

        int[] testnodes = G.randNodes(G.V());
        int node1 = testnodes[0];
        int node2 = testnodes[1];

//        G.reachabilityRatio();
//        G.reachability(node1);
//        G.reachability(node2);

//        G.dfsPath(node1, node2);
//        G.bfsPath(node1, node2);
//        G.bfsPath(0, 6);
//        StdOut.println();

//        G.hits();

        // TEMPORAL

//        TemporalNetwork T = new TemporalNetwork(G);
//        StdOut.println(T);
//        T.print();

//        int[] testtimes = T.randTimes(1);
//        int time1 = testtimes[0];
//        int time2 = testtimes[1];

//        T.temporalPath(node1, node2, time1, time2);
//        T.temporalPath(0, 6, 0, 7); // test1 for "a...i" graph
//        T.temporalPath(0, 4, 0, 6); // test2 for "a...i" graph
//        T.temporalPath(8, 4, 3, 4); // test3 for "a...i" graph

//         T.temporalReachability(-2);

        // calculate hits
//        T.hits();

//===========================================

        // G.printAggregated();
        // G.outputAggregated();
        // G.outputStat();

//         G.temporalNetwork();
//         G.printTemporal();

        // int[] testnodes = G.randNodes(G.V());
        // int node1 = testnodes[0];
        // int node2 = testnodes[1];
        // int[] testtimes = G.getrandTimes(1);
        // int time1 = testtimes[0];
        // int time2 = testtimes[1];
        // G.temporalPath(node1, node2, time1, time2, true);

        // G.temporalReachability();
        // G.temporalReachability(1,1000,1,1,1,true,false);
        // G.temporalReachability(2,1000,2,10,1,true,false);
//
//        G.permutedTimes();
//        G.randomTimes();
//        G.randomizedContacts();
//        StdOut.println(G);

//        G.outputNullStat();

//        2
//        G.temporalNetwork();
//        G.printTemporal();

//        G.temporalReachability();
//        G.temporalReachability(1,1000,1,1,1,true,false);
//        G.temporalReachability(2,1000,2,30,1,true,false);
//        G.temporalReachability(2,1000,40,190,10,true,false);
//        G.temporalReachability(2,1000,250,250,1,true,false);
//        G.temporalReachability(2,1000,300,300,1,true,false);
//        G.temporalReachability(2,1000, 350,350,1,true,false);
//        G.temporalReachability(2,1000,450,450,1,true,false);
//        G.temporalReachability(2,1000,650,650,1,true,false);

//        G.temporalReachability(-1,1000,1,24,1,true,true);

    }

}
