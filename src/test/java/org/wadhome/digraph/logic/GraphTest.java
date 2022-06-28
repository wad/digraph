package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphTest {

    @Test
    public void testIgnoresDuplicateEntries() {

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Graph graph = new Graph("ab1,ab1,ab1,ab2,ab2");

        assertEquals(
                1,
                graph.routesByStartingNode.keySet().size(),
                "There should be one source node.");
        assertEquals(
                1,
                graph.routesByStartingNode.get(nodeA).size(),
                "There should be one dest node.");
        assertEquals(
                2,
                graph.routesByStartingNode.get(nodeA).get(nodeB).size(),
                "There should be two unique weights.");
        assertTrue(
                graph.routesByStartingNode.get(nodeA).get(nodeB).contains(1),
                "Should contain the specified weight.");
        assertTrue(
                graph.routesByStartingNode.get(nodeA).get(nodeB).contains(2),
                "Should contain the specified weight.");
    }
}
