package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EdgeTest {

    @Test
    public void testWithValidInput() {
        Edge edge = new Edge("ab33");
        assertEquals("A", edge.getSourceNode().name());
        assertEquals("B", edge.getDestinationNode().name());
        assertEquals(33, edge.getWeight());
    }

    @Test
    public void testWithNull() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new Edge(null));
        assertTrue(e.getMessage().contains("null"));
    }

    @Test
    public void testWithNotEnoughCharacters() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new Edge(""));
        assertTrue(e.getMessage().contains("should be more than 3 characters"));
    }

    @Test
    public void testWithInvalidNodeNames() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new Edge("a!3"));
        assertTrue(e.getMessage().contains("Only accepting A-Z as valid node names."));
    }

    @Test
    public void testWithInvalidWeight() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new Edge("abx"));
        assertTrue(e.getMessage().contains("Invalid weight"));
    }
}
