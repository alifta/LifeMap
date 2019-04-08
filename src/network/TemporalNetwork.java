package network;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class TemporalNetwork extends StaticNetwork {

    // array list of all original time weights, including repeated ones
    // imported from original network
    // todo we probably don't need it because now we are extending from static network instead of network itself
    // ArrayList<Integer> timestampList;

    // timeId -> timestamp of edge list
    // for example: timeId = 0 -> timestamp = 8234234852 (from input data)
    ST<Integer, Integer> timeMap;

    // timestamp -> time id (opposite of timeMap)
    // we need it because we we convert original edge time to time index for new temporal edges
    ST<Integer, Integer> timestampMap;

    // CONSTRUCTOR

    // create a temporal staticNetwork from a static staticNetwork
    public TemporalNetwork(StaticNetwork staticNetwork) {
        // first call super
        // to have same elements of a abstract Network class
        super();

        // initialize variables
        // init();

        // read and initialize the timestamp list and map
        timestampList = new ArrayList<>(staticNetwork.timestampList);
        timestampMap = new ST<>();

        // initialize timeMap while reading data from original staticNetwork
        timeMap = new ST<>();
        int index = 0;
        // for every timestamp from original staticNetwork
        for (int time : staticNetwork.timestampSet()) {
            timestampMap.put(time, index);
            timeMap.put(index, time);
            index++;
        }

        // loop through all the nodes of static network's nodes
        // and create |node| * |time| new set of nodes
        for (int v = 0; v < staticNetwork.n(); v++) {
            int T = staticNetwork.t();

            // create and add nodes
            // we need extra node at the end after last timestampSet
            // reason is that we start with time slot 0 and end with t+1
            for (int t = 0; t < T + 1; t++) {
                addNode(v * (T + 1) + t,
                        String.format("%d@%d", v, t),
                        v,
                        t);
            }

            // create edges between two consecutive temporal nodes with one time difference e.g. t & t+1
            for (int i = 1; i < T + 1; i++) {
                Edge e = new Edge(
                        v * (T + 1) + (i - 1),
                        v * (T + 1) + i,
                        i - 1
                );
                addEdge(e);
            }
        }

        // loop through all the edges of static network's nodes
        // and create edge based on original edges
        for (Edge e : staticNetwork.edges()) {
            int T = staticNetwork.t();
            Edge newedge = new Edge(
                    e.from() * (T + 1) + timestampMap.get(e.time()),
                    e.to() * (T + 1) + timestampMap.get(e.time()) + 1,
                    timestampMap.get(e.time())
            );
            addEdge(newedge);
        }
    }

    // OVERRIDE

    // add a temporal node to the network
    // difference with static is a few more property such as parent and timestamp
    public void addNode(int id, String name, int parent, int timestamp) {
        // if node id does not exit in node id table
        // meaning it is a new node so add it
        if (!nodeMap.contains(id)) {
            if (!nodeNameMap.contains(name)) nodeNameMap.put(name, id);
            // update node map
            nodeMap.put(id, new Vertex(id, name, parent, timestamp));
            // update edge map
            edgeMap.put(id, new HashSet<>());
            V++;
        }
    }

    // we dont need it since extending static network
    /*
    @Override
    // add a new edge to graph
    public void addEdge(Edge edge) {
        // validate nodes
        validateVertex(edge.from());
        validateVertex(edge.to());

        // add time
        // this exit in static (not in original Network)
        timestampList.add(edge.time());

        // add edge to adjacency list of from() node
        edgeMap.get(edge.from()).add(edge);

        // update out-degree
        // add to() to the out-neighbor set of from() node
        node(edge.from()).addOutNeighbor(edge.to());

        // update in-degree
        // add from() to the in-neighbor set of to() node
        node(edge.to()).addInNeighbor(edge.from());

        // add number of edges
        E++;
    }
    */

    // RANDOM

    // return a random time
    public int randTime() {
        // something between [0, timeSet.size()-1 ]
        return getRand(timeMap.size());
    }

    // return 2 random times between start and end
    // type:
    // <0 -> 2 random time with fixed interval between them
    public int[] randTimes(int type) {
        return randTimes(type, false);
    }

    public int[] randTimes(int type, boolean print) {
        int t1 = 0;
        int t2 = 0;
        /*
         * two times with a specific (given) path length
         * */
        if (type < 0) {
            do {
                t1 = randTime();
            } while (t1 + (-type) >= t());
            t2 = t1 + (-type);
        }
        /*
         * two completely random timestamps in the range start...end with any random path length
         * */
        else if (type == 0) {
            // print = true;
            do {
                t1 = randTime();
                t2 = randTime();
                if (t1 == t2) System.out.println("***********ahaaa***********"); // TODO: Test this later
            } while (t1 >= t2);
        }
        /*
         * path length are the whole experiment length
         * */
        else if (type == 1) {
            t1 = 0;
            t2 = t() - 1; // can not set to timestampSet.size() => we get error
        }
        /*
         * dividing total experiment time to a specific number (=type)
         * */
        else {
            t1 = randTime();
            if (t1 <= (type - 1) * (t() - 1) / type)
                t2 = t1 + (t() / type);
            else
                t2 = t() - 1;
        }
        if (print)
            StdOut.println(String.format("t1: %d - t2: %d", t1, t2));
        return new int[]{t1, t2};
    }

    // ALGORITHM

    public boolean temporalPath(int v, int w, int start, int end) {
        return temporalPath(v, w, start, end, false);
    }

    public boolean temporalPath(int v, int w, int start, int end, boolean print) {
        if (print) {
            StdOut.print(String.format(
                    "node %d|%s(time:%d|%d) -> node %d|%s(time:%d|%d) = ",
                    v,
                    nodeMap.get(v).name(),
                    start,
                    timeMap.get(start),
                    w,
                    nodeMap.get(w).name(),
                    end,
                    timeMap.get(end)
            ));
        }
        boolean result = false;
        // temporal.marked = new boolean[temporal.V];
        Iterable<Integer> path = bfsPath(
                v * (timestampMap.size() + 1) + start,
                w * (timestampMap.size() + 1) + end + 1
        );
        if (!((Stack<Integer>) path).isEmpty()) {
            result = true;
            int node = v;
            StringBuilder p1 = new StringBuilder();
            StringBuilder p2 = new StringBuilder();
            StringBuilder p3 = new StringBuilder();
            p1.append(nodeMap.get(node).name() + "\t");
            p2.append(String.valueOf(node) + "\t");
            p3.append(String.valueOf(nodeMap.get(node).timestamp()) + "\t");
            for (int i : path) {
                if (nodeMap.get(i).parent() != node) {
                    p1.append(nodeMap.get(nodeMap.get(i).parent()).name() + "\t");
                    p2.append(String.valueOf(nodeMap.get(i).parent()) + "\t");
                    p3.append(String.valueOf(nodeMap.get(i).timestamp()) + "\t");
                    node = nodeMap.get(i).parent();
                }
            }
            if (print) StdOut.println("org nodes:\t" + p1 + "\nnew nodes:\t" + p2 + "\nnew times:\t" + p3 + "\n");
        } else {
            if (print) StdOut.println("not exist!\n");
        }
        return result;
    }

    public void temporalReachability() {
        temporalReachability(0, 1000, 0, 0, 0, false, false);
    }

    public void temporalReachability(int type, int repeat, int start, int end, int step, boolean print, boolean save) {
        /*
         * type<0 defines path length
         * type>0 defines denominator of (t_end - t_start)/?
         * e.g. |timeMap| = 1295//2 => 647 length of path in timestamps#
         * type=0 defines random start and end time initialization
         * */
        ArrayList<String[]> resultlist = new ArrayList<>();
        boolean iszero = false;
        boolean neg = false;
        if (type < 0) {
            neg = true;
        } else if (type == 0) { // random path size averaged over $repeat of simulation
            temporalRandomReachability(repeat, print);
            iszero = true;
        } else if (type == 1) { // only one path size => start-2-end
            end = start = 1;
            repeat = 1;
            step = 1; // optional
        }
        if (!iszero) {
            for (int t = start; t <= end; t += step) {
                float sum = 0;
                ArrayList<Float> ratiolist = new ArrayList<>();
                for (int r = 0; r < repeat; r++) {
                    int total = 0;
                    int hit = 0;
                    float ratio = 0;
                    for (int i = 0; i < V; i++) {
                        for (int j = 0; j < V; j++) {
                            if (i != j) {
                                total++;
                                int[] rt;
                                if (neg) {
                                    rt = randTimes(-t); // negative for absolute size of a path
                                } else {
                                    rt = randTimes(t); // can set denominator to 2,3,... or 1 for full length
                                }
                                if (temporalPath(i, j, rt[0], rt[1], false)) {
                                    hit++;
                                }
                            }
                        }
                    }
                    ratio = (float) hit / total;
                    sum += ratio;
                    ratiolist.add(ratio);
                }
                System.out.println("type: " + t + "\tratio: " + sum / repeat);
                if (print) System.out.println(ratiolist);
                resultlist.add(new String[]{String.valueOf(t), String.format("%.2f", sum / repeat * 100)});
            }
        }
        if (save) {
            File file = new File("ratio.txt");
            FileWriter fr = null;
            BufferedWriter br = null;
            try {
                fr = new FileWriter(file);
                br = new BufferedWriter(fr);
                for (String[] s : resultlist) {
                    br.write(String.format("%s\t%s\n", s[0], s[1]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public boolean temporalPathSimple(int v, int w, int start, int end) {
        boolean result = false;
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        for (int i = 0; i < V; i++)
            distTo[v] = INFINITY;
        int source = v * (timestampMap.size() + 1) + start;
        int destination = w * (timestampMap.size() + 1) + end + 1;
        validateVertex(source);
        validateVertex(destination);
        // bfs code, instead of: temporal.bfs(source);
        Queue<Integer> q = new Queue<>();
        marked[source] = true;
        distTo[source] = 0;
        q.enqueue(source);
        while (!q.isEmpty()) {
            int node = q.dequeue();
            for (Edge neighbor : edges(node)) {
                if (!marked[neighbor.to()]) {
                    edgeTo[neighbor.to()] = node;
                    distTo[neighbor.to()] = distTo[node] + 1;
                    marked[neighbor.to()] = true;
                    q.enqueue(neighbor.to());
                }
            }
        }
        if (marked[destination]) {
            result = true;
        }
        return result;
    }

    public ArrayList<Float> temporalRandomReachability(int repeat, boolean print) {
        float sum = 0;
        ArrayList<Float> ratiolist = new ArrayList<>();
        for (int r = 0; r < repeat; r++) {
            int total = 0;
            int hit = 0;
            float ratio = 0;
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (i != j) {
                        total++;
                        int[] testtimes = randTimes(0, false);
                        int time1 = testtimes[0];
                        int time2 = testtimes[1];
                        //System.out.println(String.format("%d,%d,%d,%d", i, j, time1, time2));
                        if (temporalPathSimple(i, j, time1, time2)) {
                            temporalPath(i, j, time1, time2, false);
                            hit++;
                        }
                    }
                }
            }
            ratio = (float) hit / total;
            sum += ratio;
            ratiolist.add(ratio);
        }
        System.out.println("simulations #: " + repeat + "\tratio: " + sum / repeat);
        if (print) System.out.println(ratiolist);
        return ratiolist;
    }

    // PRINT & SAVE

    public void printTemporal() {
        this.print();
        StdOut.println(new HashSet<>(timestampList).toString());
    }

}