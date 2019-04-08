package network;

import edu.princeton.cs.algs4.StdOut;

public class Edge {

    private final int v;
    private final int w;
    private int t;

    public Edge(int v, int w, int t) {
        if (v < 0) throw new IllegalArgumentException("vertex id should be >= 0");
        if (w < 0) throw new IllegalArgumentException("vertex id should be >= 0");
        if (t < 0) throw new IllegalArgumentException("t id should be >= 0");
        this.v = v;
        this.w = w;
        this.t = t;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public int time() {
        return t;
    }

    public void incrementTime() {
        this.t += 1;
    }

    public void setTime(int time) {
        this.t = time;
    }

    public String toString() {
        return String.format("%d-(%d)->%d", v, t, w);
    }

    public static void main(String[] args) {
        Edge e = new Edge(3, 7, 55);
        StdOut.println(e);
    }
}