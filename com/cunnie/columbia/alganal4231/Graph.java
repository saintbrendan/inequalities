package com.cunnie.columbia.alganal4231;

import java.util.*;

public class Graph {
    private ArrayList<Node> nodes = new ArrayList<>();
    private int[] mark;
    private int[] p;
    private Color[] color;
    private java.util.Stack<EdgeWeight> stack = new Stack<>();
    private int[] minValues;

    int[] getMinSolution() {
        minValues = new int[nodes.size()];
        color = new Color[nodes.size()];
        Arrays.fill(minValues, -1);
        Arrays.fill(color, Color.WHITE);
        for (int u = 0; u < nodes.size(); u++) {
            if (minValues[u] == -1) {
                dfsSolution(u, 1);
            }
        }
        return minValues;
    }

    private void dfsSolution(int u, int i) {
        minValues[u] = i;
        color[u] = Color.GREY;
        for (Edge e : nodes.get(u).adjacencyList) {
            int v = e.to;
            int solution = i + e.weight;
            if (minValues[v] < solution) {
                if (color[v] == Color.GREY) {
                    throw new IllegalStateException("Inconsistent inequalities");
                }
                dfsSolution(v, solution);
            }
        }
        color[u] = Color.BLACK;
    }

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
