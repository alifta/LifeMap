package network;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import java.util.*;

public class StaticNetwork extends Network {

    // array list of all original time weights, including repeated ones
    ArrayList<Integer> timestampList;

    // map of nodes hub score
    ST<Integer, Double> hubScore;

    // map of nodes authority score
    ST<Integer, Double> authorityScore;

    // db connection
    Connection conn;

    // needed initialization of class variables
    private void init() {
        this.timestampList = new ArrayList<>();
    }

    // CONSTRUCTOR

    // empty constructor
    public StaticNetwork() {
        // call super of abstract class (Network)
        super();

        // initialize variables
        init();
    }

    // create a random graph of V nodes and E edges
    public StaticNetwork(int V, int E) {
        // call super of abstract class (Network)
        super();

        // initialize variables
        init();

        // creating nodes
        if (V < 0) throw new IllegalArgumentException("Number of vertices can not be negative");
        for (int v = 0; v < V; v++) {
            addNode(v);
        }

        // creating edges
        if (E < 0) throw new IllegalArgumentException("Number of edges can not be negative");
        for (int i = 0; i < E; i++) {
            int[] node = randNodes(V);
            addEdge(new Edge(node[0], node[1], getRand(100)));
        }
    }

    // create a graph from "filename" that has: a b 1 meaning a connect to b at time 1
    // separated by "delimiter"
    public StaticNetwork(String filename, String delimiter) {
        // call super of abstract class (Network)
        super();

        // initialize variables
        init();

        // read input file & add nodes to network
        In in = new In(filename);
        while (!in.isEmpty()) {
            // read line and split to parts
            String[] line = in.readLine().split(delimiter);
            for (int i = 0; i < line.length - 1; i++) {
                addNode(line[i]);
            }
        }

        // read the input file again & add edges this time
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] line = in.readLine().split(delimiter);
            int v = nodeNameMap.get(line[0]);
            int w = nodeNameMap.get(line[1]);
            int t = Integer.valueOf(line[2]);
            addEdge(new Edge(v, w, t));
        }
    }

    // create a graph from db, and its edgelist table
    public StaticNetwork(String DB) {
        // call super of abstract class (Network)
        super();

        // initialize variables
        init();

        // reading the network from DB
        try {
            if (conn != null && !conn.isClosed()) conn.close();
            conn = DriverManager.getConnection("jdbc:sqlite:" + DB);

            String query = "SELECT * FROM edgelist";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String v = rs.getString("i");
                String w = rs.getString("j");
                int t = rs.getInt("t");
                // System.out.println(v + "\t" + w + "\t" + t);

                // add time to list of all times including repeated ones
                // so later we can use in shuffling and randomization
                timestampList.add(t);


                // add vertices
                addNode(v);
                addNode(w);

                // add edges
                Edge e1 = new Edge(nodeNameMap.get(v), nodeNameMap.get(w), t);
                Edge e2 = new Edge(nodeNameMap.get(w), nodeNameMap.get(v), t);
                addEdge(e1);
                addEdge(e2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    // TIME

    // number of timestamps, including repeated ones
    public int timeSize() {
        return timestampList.size();
    }

    // sorted set of all time weights, without duplicate ones
    public Set<Integer> timestampSet() {
        Set<Integer> timeSet = new HashSet<>(timestampList);
        return timeSet;
    }

    // number of distinct timestamps
    public int t() {
        return timestampSet().size();
    }

    // OVERRIDE

    @Override
    // add a node: apply only for random graph
    public void addNode(int id) {
        // if node id does not exit in node id table
        // meaning it is a new node so add it
        if (!nodeMap.contains(id)) {
            nodeMap.put(V, new Vertex(V));
            nodeNameMap.put(String.valueOf(id), V);
            edgeMap.put(V, new HashSet<>());
            // increment size of graph by 1.
            // |V| is always 1 more than the latest assigned node id
            V++;
        }
    }

    @Override
    // add a vertex to node tables (id, name, ...)
    public void addNode(String name) {
        // add the node, if its name doesn't exit
        if (!this.nodeNameMap.contains(name)) {
            this.nodeNameMap.put(name, V);
            // id of new node equals the size of graph
            // keep in mind we don't have nodeDelete operation yet
            nodeMap.put(V, new Vertex(this.V, name));
            // create an empty edge set table for the new vertex
            edgeMap.put(V, new HashSet<>());
            V++;
        }
    }

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

    // ALGORITHM

    // DFS
    public void dfs(int v) {
        marked[v] = true;
        for (Edge w : edges(v)) {
            if (!marked[w.to()]) {
                edgeTo[w.to()] = v;
                dfs(w.to());
            }
        }
    }

    // calculate DFS
    public void calculateDfs(int v) {
        // initialize arrays
        marked = new boolean[V];
        edgeTo = new int[V];

        validateVertex(v);

        // perform DFS
        dfs(v);
    }

    // calculate DFS from a source node to destination
    public Iterable<Integer> dfsPath(int src, int dest) {
        validateVertex(dest);
        calculateDfs(src);

        // if "d" is not reachable by "s" return null
        if (!marked[dest]) return null;

        // other wise find the path from "s" to "d"
        return getPath(src, dest);
    }

    // calculate reachability using DFS from source node to all other nodes
    public void reachability(int src) {
        StdOut.print("node " + src + " reaches: ");
        calculateDfs(src);
        String reachable = "";
        for (int v = 0; v < V; v++) {
            if (marked[v]) reachable += v + " ";
        }
        StdOut.print(reachable + "\n");
    }

    // BFS
    public void bfs(int src) {
        Queue<Integer> q = new Queue<>();
        marked[src] = true;
        distTo[src] = 0;
        q.enqueue(src);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (Edge w : edges(v)) {
                if (!marked[w.to()]) {
                    edgeTo[w.to()] = v;
                    distTo[w.to()] = distTo[v] + 1;
                    marked[w.to()] = true;
                    q.enqueue(w.to());
                }
            }
        }
    }

    // calculate BFS
    public void calculateBfs(int src) {
        // initialize arrays
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        for (int v = 0; v < V; v++)
            distTo[v] = INFINITY;

        validateVertex(src);

        // perform BFS
        bfs(src);
    }

    public Stack<Integer> bfsPath(int src, int dest) {
        validateVertex(dest);
        calculateBfs(src);

        // if "d" is not reachable by "s"
        // return empty Stack, in DFS is returning null
        // if (!marked[dest]) return null;
        if (!marked[dest]) return new Stack<Integer>();

        // other wise find the path from "s" to "d"
        return getPath(src, dest);
    }

    // read path after performing a route finding algorithm
    public Stack<Integer> getPath(int src, int dest) {
        return getPath(src, dest, true);
    }

    public Stack<Integer> getPath(int src, int dest, boolean print) {
        Stack<Integer> path = new Stack<>();

        // finding path with going back from destination to source
        for (int x = dest; x != src; x = edgeTo[x]) {
            path.push(x);
        }

        // we take out the source from the path
        path.push(src);

        // print
        if (print) {
            StdOut.printf("%d to %d:  ", src, dest);
            // only uncomment next line for BFS distance
            // StdOut.printf("%d to %d (%d):  ", src, dest, distTo[dest]);
            StdOut.println(path);
        }

        return path;
    }

    // revise DFS and its difference with DFS:
    // we don't set source node reachability to true
    // also, we only used marked array and not edgeTo anymore
    public void dfsRevised(int v) {
        for (Edge w : edges(v)) {
            if (!marked[w.to()]) {
                marked[w.to()] = true;
                dfsRevised(w.to());
            }
        }
    }

    public void reachabilityRatio() {
        StdOut.print("reachablity ratio: ");
        marked = new boolean[V];
        for (int v = 0; v < V; v++) {
            if (!marked[v]) dfsRevised(v);
        }
        int numberofmarked = 0;
        String reachable = "";
        for (int v = 0; v < marked.length; v++) {
            if (marked[v]) {
                numberofmarked++;
                reachable += v + " ";
            }
        }
        StdOut.println((float) numberofmarked / marked.length);
    }


    public void hits() {
        int maxIteration = 100;
        double tolerance = 1.0e-8;
        hits(maxIteration, tolerance);
        saveHits("normal");
    }

    public void hits(int maxIteration, double tolerance) {
        double diffA;
        double diffH;
        hubScore = new ST<>();
        authorityScore = new ST<>();
        ST<Integer, Double> copyHubScore = new ST<>();
        ST<Integer, Double> copyAuthorityScore = new ST<>();
        // double startValue = 1.0;
        double startValue = 1.0 / V();
        // initializing the score maps with initial value of 1
        for (int v = 0; v < V; v++) {
            hubScore.put(v, startValue);
            authorityScore.put(v, startValue);
        }
        // run the algorithm for maxIteration steps
        for (int i = 0; i < maxIteration; i++) {
            // copy hubScore into copyHubScore and same for auth-score
            STFunction.copyIntDouble(hubScore, copyHubScore);
            STFunction.copyIntDouble(authorityScore, copyAuthorityScore);
            // update all authority values first. For all the nodes in the network do:
            for (int v = 0; v < V; v++) {
                authorityScore.put(v, 0.0);
                // get set of the incoming neighbors of node v
                for (int inNeighbor : nodeMap.get(v).inNeighbors()) {
                    // add hub-score of in-neigh to it current auth-score
                    authorityScore.put(v, authorityScore.get(v) + hubScore.get(inNeighbor));
                }
            }
            // normalize authority scores
            STFunction.normSqrt(authorityScore);
            // then update hub values
            for (int v = 0; v < V; v++) {
                hubScore.put(v, 0.0);
                // get the set of the outgoing neighbors of node v
                for (int outNeighbor : nodeMap.get(v).outNeighbors()) {
                    // add auth-score of out-neigh to it current hub-score
                    hubScore.put(v, hubScore.get(v) + authorityScore.get(outNeighbor));
                }
            }
            // normalize hub scores
            STFunction.normSqrt(hubScore);
            // normalizing each score vector (networkx)
            // STFunction.normMax(authorityScore);
            // STFunction.normMax(hubScore);
            // check if we converged then halt
            diffH = STFunction.diffIntDouble(hubScore, copyHubScore);
            diffA = STFunction.diffIntDouble(authorityScore, copyAuthorityScore);
            if (diffA < tolerance && diffH < tolerance) {
                StdOut.println("finishing the algorithm with " + i + " iteration and tolerance of " + diffA);
                break;
            }
        }
        // STFunction.normSum(hubScore); // networkx
        StdOut.println("hub-score:" + STFunction.sumIntDouble(hubScore));
        STFunction.print(hubScore);
        // STFunction.normSum(authorityScore); // networkx
        StdOut.println("authority-score:" + STFunction.sumIntDouble(authorityScore));
        STFunction.print(authorityScore);
    }

    public void hitsRandomized() {
        int maxIteration = 100;
        double tolerance = 1.0e-8;
        hitsRandomized(maxIteration, tolerance);
        saveHits("randomized");
    }

    public void hitsRandomized(int maxIteration, double tolerance) {
        // A(T)_row is transpose adjacency matrix of graph, normalized on its row
        // meaning the sum of each row is equal to 1, i.e., sum of indegree of each node is equal to 1
        // we call this new matrix weightRowNorm, and we call A_col, weightColNorm which used for hub score

        double epsilon = 0.15;
        double damping = 1 - epsilon;

        double rowNorm; // which will be indegree of each node
        double colNorm;

        // init
        double diffA;
        double diffH;

        hubScore = new ST<>();
        authorityScore = new ST<>();

        ST<Integer, Double> copyHubScore = new ST<>();
        ST<Integer, Double> copyAuthorityScore = new ST<>();

        // double startValue = 1.0;
        double startValue = 1.0 / V();

        // initializing the score maps with initial value of 1
        for (int v = 0; v < V; v++) {
            hubScore.put(v, startValue);
            authorityScore.put(v, startValue);
        }

        // run the algorithm for maxIteration steps
        for (int i = 0; i < maxIteration; i++) {
            // copy hubScore into copyHubScore and same for auth-score
            STFunction.copyIntDouble(hubScore, copyHubScore);
            STFunction.copyIntDouble(authorityScore, copyAuthorityScore);

            // update all authority values first. For all the nodes in the network do:
            for (int v = 0; v < V; v++) {
                // first set all of them as zero
                authorityScore.put(v, 0.0);

                // denominator of normalizer
                rowNorm = (double) nodeMap.get(v).indegree();

                // get the set of node v's incoming neighbors
                for (int inNeighbor : nodeMap.get(v).inNeighbors()) {
                    // add hub-score of in-neigh to it current auth-score
                    authorityScore.put(v, authorityScore.get(v) + hubScore.get(inNeighbor) / rowNorm);
                }

                // here we affect the reset (damping) factor
                authorityScore.put(v, (authorityScore.get(v) * damping) + epsilon);
            }

            // normalize authority scores
            // STFunction.normSqrt(authorityScore);

            // then update hub values
            for (int v = 0; v < V; v++) {
                // first set all of them as zero
                hubScore.put(v, 0.0);

                // get the set of the outgoing neighbors of node v
                for (int outNeighbor : nodeMap.get(v).outNeighbors()) {
                    // denominator of normalizer
                    colNorm = (double) nodeMap.get(outNeighbor).indegree();

                    // add auth-score of out-neigh to it current hub-score
                    hubScore.put(v, hubScore.get(v) + authorityScore.get(outNeighbor) / colNorm);
                }

                // here we affect the reset (damping) factor
                hubScore.put(v, (hubScore.get(v) * damping) + epsilon);

            }

            // normalize hub scores
            STFunction.normSqrt(hubScore);

            // normalizing each score vector (networkx)
            // STFunction.normMax(authorityScore);
            // STFunction.normMax(hubScore);

            // check if we converged then halt
            diffH = STFunction.diffIntDouble(hubScore, copyHubScore);
            diffA = STFunction.diffIntDouble(authorityScore, copyAuthorityScore);

            if (diffA < tolerance && diffH < tolerance) {
                StdOut.println("finishing the algorithm with " + i + " iteration and tolerance of " + diffA);
                // break out of the main loop
                break;
            }
        }

        // STFunction.normSum(hubScore); // networkx
        StdOut.println("hub-score:" + STFunction.sumIntDouble(hubScore));
        STFunction.print(hubScore);

        // STFunction.normSum(authorityScore); // networkx
        StdOut.println("authority-score:" + STFunction.sumIntDouble(authorityScore));
        STFunction.print(authorityScore);
    }

    public StaticNetwork convertUndirected() {
        StaticNetwork undirected = new StaticNetwork();
        // todo
        return undirected;
    }

    // STATISTICS

    // get histogram of degree distribution
    public void histogram() {
        // map of degree -> frequency
        Map<Integer, Integer> hist = new HashMap<>();
        for (int n : nodes()) {
            int s = edgeMap.get(n).size();
            // method 1
            // int count = hist.containsKey(s) ? hist.get(s) : 0;
            // hist.put(s, count + 1);
            // method 2
            // map.merge(key, 1, Integer::sum);
            hist.merge(s, 1, Integer::sum);
            // method 3 with lambda
            // map.merge(key, 1, (a,b) -> a + b);
        }
        System.out.println(Collections.singletonList(hist));
    }

    // PRINT & SAVE

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("|V|: " + V + "\t|E|: " + E + "\t|T|: " + timestampSet().size() + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(nodeMap.get(v) + ":\t");
            if (outdegree(v) > 0) {
                for (int outN : nodeMap.get(v).outNeighbors()) {
                    s.append(outN + "(" + nodeMap.get(outN).name() + ") ");
                }
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public void print() {
        StringBuilder s = new StringBuilder();
        s.append("|V|: " + V + "\t|E|: " + E + "\t|T|: " + timestampSet().size() + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(nodeMap.get(v) + ":\t");
            if (outdegree(v) > 0) {
                for (Edge e : edges(v)) {
                    s.append(e + "  ");
                }
            }
            s.append(NEWLINE);
        }

        // calculate frequency of founded time label from input file
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int v = 0; v < V; v++) {
            if (outdegree(v) > 0) {
                for (Edge e : edges(v)) {
                    if (frequencyMap.containsKey(e.time())) {
                        frequencyMap.put(e.time(), frequencyMap.get(e.time()) + 1);
                    } else {
                        frequencyMap.put(e.time(), 1);
                    }
                }
            }
        }

        System.out.print(s.toString());
        System.out.println("frequency of timestamps: " + frequencyMap + "\n");
    }

    // save the network as edge list
    public void save() {
        // todo
    }

    // ----------------------------------- SAVE ----------------------------------- //

    // save HITS scores
    public void saveHits(String appendix) {
        File f = new File("./data/hits-" + appendix + ".txt");
        FileWriter fr = null;
        BufferedWriter br = null;

        try {
            fr = new FileWriter(f);
            br = new BufferedWriter(fr);

            br.write(String.format("%s\t%s\t%s\n", "id", "auth", "hub"));
            for (int node : authorityScore.keys()) {
                br.write(String.format("%s\t%.3f\t%.3f\n",
                        node,
                        authorityScore.get(node),
                        hubScore.get(node)
                ));
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