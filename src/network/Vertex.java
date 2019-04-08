package network;

import edu.princeton.cs.algs4.SET;

public class Vertex {

    private final int id;
    private final String name;

    private int time = -1;
    private int parent = -1;

    private SET<Integer> indegree;
    private SET<Integer> outdegree;

    public Vertex(int id) {
        this.id = id;
        this.name = "v" + id;
        indegree = new SET<>();
        outdegree = new SET<>();
    }

    public Vertex(int id, String name) {
        this.id = id;
        this.name = name;
        indegree = new SET<>();
        outdegree = new SET<>();
    }

    public Vertex(int id, String name, int time) {
        this.id = id;
        this.name = name;
        this.time = time;
        indegree = new SET<>();
        outdegree = new SET<>();
    }

    public Vertex(int id, String name, int parent, int time) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.time = time;
        indegree = new SET<>();
        outdegree = new SET<>();
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int parent() {
        return parent;
    }

    public int timestamp() {
        return time;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setTime(int time) {
        this.time = time;
    }

    // in-degree,

    public int indegree() {
        return indegree.size();
    }

    // out-degree

    public int outdegree() {
        return outdegree.size();
    }

    // degree

    public int degree() {
        return indegree.size() + outdegree.size();
    }

    public void addInNeighbor(int nodeId) {
        indegree.add(nodeId);
    }

    public void addOutNeighbor(int nodeId) {
        outdegree.add(nodeId);
    }

    public SET<Integer> inNeighbors() {
        return indegree;
    }

    public SET<Integer> outNeighbors() {
        return outdegree;
    }

    public boolean isSink() {
        return outdegree.size() == 0;
    }

    public boolean isSource() {
        return indegree.size() == 0;
    }

    public String toString() {
        return String.format("%d(%s)", id, name);
    }
}
