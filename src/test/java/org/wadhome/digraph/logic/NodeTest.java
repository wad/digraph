package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NodeTest {

    @Test
    public void testHappyDay() {
        Node node = new Node("z");
        Assertions.assertEquals("Z", node.name());
    }

    @Test
    public void testWithNullName() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new Node(null));
        assertTrue(e.getMessage().toLowerCase().contains("missing"));
    }

    @Test
    public void testWithEmptyName() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new Node(""));
        assertTrue(e.getMessage().contains("Invalid"));
    }

    @Test
    public void testWithWrongLengthName() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new Node("aa"));
        assertTrue(e.getMessage().contains("Invalid"));
    }

    @Test
    public void testWithInvalidName() {
        RuntimeException e = Assertions.assertThrows(
                RuntimeException.class,
                () -> new Node("?"));
        assertTrue(e.getMessage().contains("Only accepting A-Z as valid node names."));
    }
}
