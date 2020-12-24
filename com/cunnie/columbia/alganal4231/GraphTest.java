package com.cunnie.columbia.alganal4231;

import com.cunnie.columbia.alganal4231.Graph.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    Graph g;

    @BeforeEach
    void setUp() {
        g = new Graph();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void oneNodeisConsistent() {
        g.node(0);

        assertTrue(g.isConsistent());
    }

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
    void weakStrongInequalityLoopSolutionThrowsException() {
        Node n0 = g.node(0);
        Node n1 = g.node(1);
        n0.lt(n1);
        n1.lte(n0);

        assertThrows(IllegalStateException.class, () -> g.getMinSolution());
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
    void strongInequalitesAcrossShortSubtreesHasSolution() {
        Node root = g.node(0);
        Node leftNode = g.node(1);
        Node rightNode = g.node(2);
        root.lt(leftNode);
        root.lt(rightNode);
        leftNode.lt(rightNode);

        assertTrue(g.getMinSolution()[0] == 1);
        assertTrue(g.getMinSolution()[1] == 2); // leftNode
        assertTrue(g.getMinSolution()[2] == 3); // rightNode
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

        assertTrue(g.getMinSolution()[0] == 1);
        assertTrue(g.getMinSolution()[1] == 2); // leftSubtree
        assertTrue(g.getMinSolution()[2] == 4); // leftLeaf
        assertTrue(g.getMinSolution()[3] == 2); // rightSubtree
        assertTrue(g.getMinSolution()[4] == 3); // rightLeaf ( < leftLeaf)
    }


    @Test
    void weakInequalityLoopIsConsistent() {
        Node n0 = g.node(0);
        Node n1 = g.node(1);
        n0.lte(n1);
        n1.lte(n0);

        assertTrue(g.isConsistent());
    }

    @Test
    void strongInequalityLoopIsInconsistent0() {
        Node n0 = g.node(0);
        Node n1 = g.node(1);
        n0.lte(n1);
        n1.lt(n0);

        assertFalse(g.isConsistent());
    }

    @Test
    void strongInequalityLoopisInconsistent1() {
        Node n0 = g.node(0);
        Node n1 = g.node(1);
        n0.lt(n1);
        n1.lte(n0);

        assertFalse(g.isConsistent());
    }

    @Test
    void longWeakInequalityLoopIsConsistent() {
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

        assertTrue(g.isConsistent());
    }

    @Test
    void longStrongInequalityLoopIsInconsistent() {
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

        assertFalse(g.isConsistent());
    }

    @Test
    void strongInequalitesAcrossShortSubtreesIsConsistent() {
        Node n0 = g.node(0);
        Node n1 = g.node(1);
        Node n2 = g.node(2);
        n0.lt(n1);
        n0.lt(n2);
        n2.lt(n1);

        assertTrue(g.isConsistent());
    }

    @Test
    void strongInequalitesAcrossSubtreesIsConsistent() {
        Node n0 = g.node(0);
        Node n1 = g.node(1);
        Node n2 = g.node(2);
        Node n3 = g.node(3);
        Node n4 = g.node(4);
        n0.lt(n1);
        n1.lt(n2);
        n0.lt(n3);
        n3.lt(n4);
        n4.lt(n2);

        assertTrue(g.isConsistent());
    }
}