package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeightedEdgeTest {

    @Test
    public void testWithValidInput() {
        WeightedEdge weightedEdge = new WeightedEdge("ab33");
        assertEquals("A", weightedEdge.getSourceNode().name());
        assertEquals("B", weightedEdge.getDestinationNode().name());
        assertEquals(33, weightedEdge.getWeight());
    }

    @Test
    public void testWithNull() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new WeightedEdge(null));
        assertTrue(e.getMessage().contains("null"));
    }

    @Test
    public void testWithNotEnoughCharacters() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new WeightedEdge(""));
        assertTrue(e.getMessage().contains("should be more than 3 characters"));
    }

    @Test
    public void testWithInvalidNodeNames() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new WeightedEdge("a!3"));
        assertTrue(e.getMessage().contains("Only accepting A-Z as valid node names."));
    }

    @Test
    public void testWithInvalidWeight() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new WeightedEdge("abx"));
        assertTrue(e.getMessage().contains("Invalid weight"));
    }
}
