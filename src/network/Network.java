package network;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;

public abstract class Network {

    static final String NEWLINE = System.getProperty("line.separator");
    static final int INFINITY = Integer.MAX_VALUE;

    // node number
    int V;

    // edge number
    int E;

    // node name -> node id
    ST<String, Integer> nodeNameMap;

    // node id -> node object
    ST<Integer, Vertex> nodeMap;

    // node id -> edges to neighbors
    // this edge map is sort of an adjacency list
    // where there can be multiple nodes s to d with different edge weights ( or here timeMap)
    ST<Integer, HashSet<Edge>> edgeMap;

    // array for various algorithm
    boolean[] marked;
    int[] edgeTo;
    int[] distTo;

    // CONSTRUCTOR

    Network() {
        this.V = 0;
        this.E = 0;
        this.nodeNameMap = new ST<>();
        this.nodeMap = new ST<>();
        this.edgeMap = new ST<>();
    }

    // NODE

    // |V| of network
    public int V() {
        return V;
    }

    // return number of nodes
    public int n() {
        return nodeMap.size();
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    // return node object (Vertex) by getting its ID
    public Vertex node(int id) {
        return nodeMap.get(id);
    }

    // return node object (Vertex) by getting its name
    public Vertex node(String name) {
        return nodeMap.get(nodeNameMap.get(name));
    }

    // return true if network has node ID
    public boolean hasNode(int id) {
        return nodeMap.contains(id);
    }

    // return true if network has node name
    public boolean hasNode(String name) {
        return nodeNameMap.contains(name);
    }

    // return list of all the nodes' ID, e.g., 1,2,3,...,n
    public Iterable<Integer> nodes() {
        return nodeMap.keys();
    }

    // return list of nodes objects or vertices
    public Iterable<Vertex> vertices() {
        Set<Vertex> vertices = new HashSet<>();
        for (int i : nodes()) {
            vertices.add(node(i));
        }
        return vertices;
    }

    // DEGREE

    // return in-degree of a node
    public int indegree(int id) {
        return node(id).indegree();
    }
    public int indegree(String name) {
        return node(nodeNameMap.get(name)).indegree();
    }

    // return out-degree of a node
    public int outdegree(int id) {
        return node(id).outdegree();
    }
    public int outdegree(String name) {
        return node(nodeNameMap.get(name)).outdegree();
    }

    // EDGE

    // |E| of network
    public int E() {
        return E;
    }

    // return number of edges, including repeated ones wih different weights
    public int m() {
        int count = 0;
        for (int node : edgeMap.keys()) {
            count += edgeMap.get(node).size();
        }
        return count;
    }

    // access an edge object by knowing its from(), to(), and time()
    public Edge edge(int from, int to, int time) {
        if (outdegree(from) > 0) {
            for (Edge edge : edges(from)) {
                if (edge.to() == to && edge.time() == time) {
                    return edge;
                }
            }
        }
        return null;
    }

    // another implementation
    /*
    public Edge getEdge(int from, int to, int time) {
        Edge result = null;
        for (Iterator<Edge> it = edgeMap.get(from).iterator(); it.hasNext(); ) {
            Edge e = it.next();
            if (e.to() == to && e.time() == time) {
                result = e;
            }
        }
        return result;
    }
    */

    // return iterable of all the edges in the network
    public Iterable<Edge> edges() {
        Set<Edge> edges = new HashSet<>();
        for (int i : edgeMap.keys()) {
            edges.addAll(edgeMap.get(i));
        }
        return edges;
    }

    // return iterable of edges of a node
    public Iterable<Edge> edges(int id) {
        return edgeMap.get(id);
    }

    // RANDOM

    // returns a random integer uniformly in [0, n)
    public int getRand(int max) {
        return StdRandom.uniform(max);
    }

    // return two random nodes
    public int[] randNodes(int max) {
        int v;
        int w;
        do {
            v = getRand(n());
            w = getRand(n());
        } while (w == v);
        return new int[]{v, w};
    }

    // ABSTRACT

    public void addNode(int nodeId) {

    }

    public void addNode(String nodeName) {

    }

    public void addEdge(Edge edge) {

    }

    // public abstract void removeNode(int nodeId);
    // public abstract void removeNode(String nodeName);
    // public abstract void removeEdge(Edge edge);


}
