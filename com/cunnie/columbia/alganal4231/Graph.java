package com.cunnie.columbia.alganal4231;

import java.util.*;

public class Graph {
    private ArrayList<Node> nodes = new ArrayList<>();
    private int[] mark;
    private int[] p;
    private Color[] color;
    private java.util.Stack<EdgeWeight> stack = new Stack<>();

    public class Node {
        private int nodeNumber;
        private List<Edge> adjacencyList = new ArrayList<>();

        private Node(int nodeNumber) {this.nodeNumber = nodeNumber;}

        void lt(Node to) {
            adjacencyList.add(new Edge(1, to.nodeNumber));
        }

        void lte(Node to) {
            adjacencyList.add(new Edge(0, to.nodeNumber));
        }
    }

    boolean isConsistent() {
        mark = new int[nodes.size()];
        p = new int[nodes.size()];
        color = new Color[nodes.size()];
        Arrays.fill(mark, 0);
        Arrays.fill(p, -1);
        Arrays.fill(color, Color.WHITE);
        for (int u = 0; u < nodes.size(); u++) {
            if (!DFS(u)) {
                return false;
            }
        }
        return true;
    }

    private boolean DFS(int u) {
        mark[u] = 1;
        color[u] = Color.GREY;
        for (Edge e : nodes.get(u).adjacencyList) {
            int v = e.to;
            stack.push(new EdgeWeight(u, e.weight));
            if (color[v] == Color.GREY) {
                // check if any weights of 1 in the cycle
                for (int i = stack.size() - 1; i >= v; i--) {
                    if (stack.get(i).weight == 1) {
                        return false;  // NOT consistent
                    }
                }
            }
            if (mark[v] == 0) {
                p[v] = u;
                if (DFS(v) == false) {
                    return false;
                }
            }
            stack.pop();
        }
        color[u] = Color.BLACK;
        return true;
    }

    private class Edge {
        private final int weight;
        private final int to;

        Edge(int weight, int to) {
            this.weight = weight;
            this.to = to;
        }
    }

    public Node node(int nodeNumber) {
        while (nodes.size() <= nodeNumber) {
            nodes.add(new Node(nodes.size()));
        }
        return nodes.get(nodeNumber);
    }

    private enum Color {
        WHITE, GREY, BLACK;
    }

    private class EdgeWeight {
        int from;
        int weight;

        EdgeWeight(int from, int weight) {
            this.from = from;
            this.weight = weight;
        }
    }
}
