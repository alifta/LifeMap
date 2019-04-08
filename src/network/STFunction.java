package network;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class STFunction {

    public static void copyIntDouble(ST<Integer, Double> src, ST<Integer, Double> dest) {
        int s = src.size();
        if (s > 0) {
            for (Integer key : src.keys()) {
                dest.put(key, src.get(key));
            }
        }
    }

    // return the element-wise difference of two table
    // we assume that both STs have same size and same set of keys
    // another assumption is that keys are 0 ... n (or size)
    public static double diffIntDouble(ST<Integer, Double> src, ST<Integer, Double> dest) {
        int s = src.size();
        double[] diffArray = new double[s];
        if (s > 0) {
            for (Integer key : src.keys()) {
                diffArray[key] = Math.abs(src.get(key) - dest.get(key));
            }
        }
        Arrays.sort(diffArray);
        return diffArray[s - 1];
    }

    // return normalized ST regarding its values and their maximum
    public static ST<Integer, Double> normMax(ST<Integer, Double> st) {
        int s = st.size();
        double[] values = new double[s];
        for (Integer key : st.keys()) {
            values[key] = Math.abs(st.get(key));
        }
        Arrays.sort(values);
        double max = values[s - 1];
        for (Integer key : st.keys()) {
            st.put(key, st.get(key) / max);
        }
        return st;
    }

    // return normalized ST regarding its values and their summation
    public static ST<Integer, Double> normSum(ST<Integer, Double> st) {
        int s = st.size();
        double sum = 0.0;
        for (Integer key : st.keys()) {
            sum += Math.abs(st.get(key));
        }
        for (Integer key : st.keys()) {
            st.put(key, st.get(key) / sum);
        }
        return st;
    }

    // return normalized ST regarding its values and square root of their square summation
    public static ST<Integer, Double> normSqrt(ST<Integer, Double> st) {
        double norm = 0.0;
        for (Integer key : st.keys()) {
            norm += Math.pow(st.get(key), 2);
        }
        norm = Math.sqrt(norm);
        if (norm > 0) {
            for (Integer key : st.keys()) {
                st.put(key, st.get(key) / norm);
            }
        }
        return st;
    }

    public static <Key extends Comparable<Key>, Value> void copy(ST<Key, Value> src, ST<Key, Value> dest) {
        int s = src.size();
        if (s > 0) {
            for (Key key : src.keys()) {
                dest.put(key, src.get(key));
            }
        }
    }

    public static <Key extends Comparable<Key>, Value> Bag values(ST<Key, Value> st) {
        Bag<Value> values = new Bag<>();
        int s = st.size();
        if (s > 0) {
            for (Key key : st.keys()) {
                values.add(st.get(key));
            }
        }
        return values;
    }

    public static <Key extends Comparable<Key>, Value> void print(ST<Key, Value> st) {
        int i = 0;
        int s = st.size();
        if (s > 0) {
            StdOut.print("{");
            for (Key key : st.keys()) {
                if (i++ == s - 1) {
                    // last iteration
                    StdOut.print(key.toString() + " :" + st.get(key).toString() + "}\n");
                } else {
                    StdOut.print(key.toString() + " :" + st.get(key).toString() + ", ");
                }
            }
        }
    }

}
