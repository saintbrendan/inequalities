package com.cunnie.columbia.alganal4231;

import com.cunnie.columbia.alganal4231.Graph.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {
    Graph g;

    @Test
    void oneNodeSolutionIs1() {
        g.node(0);

        assertTrue(g.getMinSolution()[0] == 1);
    }

    @Test
    void weakInequalityLoopSolutionIs1s() {
        Node n0 = g.node(0);
        Node n1 = g.node(1);
        n0.lte(n1);
        n1.lte(n0);

        assertTrue(g.getMinSolution()[0] == 1);
        assertTrue(g.getMinSolution()[1] == 1);
    }

    @Test
    void loopWithStrongInequalityThrowsException() {
        Node n0 = g.node(0);
        Node n1 = g.node(1);
        n0.lt(n1);
        n1.lte(n0);

        assertThrows(IllegalStateException.class, () -> g.getMinSolution());
    }

    @Test
    void longWeakInequalityHasSameMinvalue() {
        Node n0 = g.node(0);
        Node n1 = g.node(1);
        Node n2 = g.node(2);
        Node n3 = g.node(3);
        Node n4 = g.node(4);
        n0.lte(n1);
        n1.lte(n2);
        n2.lte(n3);
        n3.lte(n4);
        n4.lte(n0);

        assertTrue(g.getMinSolution()[0] == 1);
        assertTrue(g.getMinSolution()[1] == 1);
        assertTrue(g.getMinSolution()[2] == 1);
        assertTrue(g.getMinSolution()[3] == 1);
        assertTrue(g.getMinSolution()[4] == 1);
    }

    @Test
    void longStrongInequalityLoopThrowsException() {
        Node n0 = g.node(0);
        Node n1 = g.node(1);
        Node n2 = g.node(2);
        Node n3 = g.node(3);
        Node n4 = g.node(4);
        n0.lte(n1);
        n1.lte(n2);
        n2.lt(n3);
        n3.lte(n4);
        n4.lte(n0);

        assertThrows(IllegalStateException.class, () -> g.getMinSolution());
    }

    @Test
    void strongInequalitesAcrossShortSubtreesHasSolution() {
        Node root = g.node(0);
        Node leftNode = g.node(1);
        Node rightNode = g.node(2);
        root.lt(leftNode);
        root.lt(rightNode);
        leftNode.lt(rightNode);

        assertTrue(g.getMinSolution()[0] == 1); // root
        assertTrue(g.getMinSolution()[1] == 2); // leftNode ( < rightNode)
        assertTrue(g.getMinSolution()[2] == 3); // rightNode ( > leftNode)
    }

    @Test
    void strongInequalitesAcrossSubtreesHasSolution() {
        Node root = g.node(0);
        Node leftSubtree = g.node(1);
        Node leftLeaf = g.node(2);
        Node rightSubtree = g.node(3);
        Node rightLeaf = g.node(4);
        root.lt(leftSubtree);
        leftSubtree.lt(leftLeaf);
        root.lt(rightSubtree);
        rightSubtree.lt(rightLeaf);
        rightLeaf.lt(leftLeaf);

        assertTrue(g.getMinSolution()[0] == 1); // root
        assertTrue(g.getMinSolution()[1] == 2); // leftSubtree
        assertTrue(g.getMinSolution()[2] == 4); // leftLeaf ( > rightLeaf)
        assertTrue(g.getMinSolution()[3] == 2); // rightSubtree
        assertTrue(g.getMinSolution()[4] == 3); // rightLeaf ( < leftLeaf)
    }

    @BeforeEach
    void setUp() {
        g = new Graph();
    }
}
