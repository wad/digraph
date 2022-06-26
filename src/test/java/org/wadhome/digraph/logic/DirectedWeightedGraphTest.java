package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectedWeightedGraphTest {

    @Test
    public void testIgnoresDuplicateEntries() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph("ab1,ab1,ab1,ab2");
        assertEquals(1, graph.pathsByStartingNode.keySet().size());
        assertEquals(2, graph.pathsByStartingNode.get(new Node("A")).size());
    }
}
