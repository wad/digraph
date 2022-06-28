package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {

    @Test
    public void testBasicUse() {
        Route route = new Route();
        assertTrue(route.isEmpty());
        assertEquals(0, route.getNumNodesInRoute());
        assertEquals("", route.toString());
        assertEquals("", route.getRouteNodes().stream().map(Node::name).collect(joining()));

        route.addNode(new Node("a"));
        assertFalse(route.isEmpty());
        assertEquals(1, route.getNumNodesInRoute());
        assertEquals("A", route.toString());
        assertEquals("A", route.getRouteNodes().stream().map(Node::name).collect(joining()));

        route.removeFirstNodeInRoute();
        assertTrue(route.isEmpty());
        assertEquals(0, route.getNumNodesInRoute());
        assertEquals("", route.toString());
        assertEquals("", route.getRouteNodes().stream().map(Node::name).collect(joining()));

        route.addNode(new Node("a"));
        route.addNode(new Node("b"));
        assertFalse(route.isEmpty());
        assertEquals(2, route.getNumNodesInRoute());
        assertEquals("AB", route.toString());
        assertEquals("AB", route.getRouteNodes().stream().map(Node::name).collect(joining()));

        route.removeFirstNodeInRoute();
        assertFalse(route.isEmpty());
        assertEquals(1, route.getNumNodesInRoute());
        assertEquals("B", route.toString());
        assertEquals("B", route.getRouteNodes().stream().map(Node::name).collect(joining()));
    }

    @Test
    public void testCopyConstructor() {
        Route r1 = new Route();
        r1.addNode(new Node("a"));
        r1.addNode(new Node("b"));
        r1.addNode(new Node("c"));
        r1.addNode(new Node("d"));
        assertEquals("ABCD", r1.toString());
        assertEquals(4, r1.getNumNodesInRoute());
        assertEquals("ABCD", r1.getRouteNodes().stream().map(Node::name).collect(joining()));

        Route r2 = new Route(r1);
        assertEquals("ABCD", r2.toString());
        assertEquals(4, r2.getNumNodesInRoute());
        assertEquals("ABCD", r2.getRouteNodes().stream().map(Node::name).collect(joining()));

        r1.removeFirstNodeInRoute();

        assertEquals("BCD", r1.toString());
        assertEquals(3, r1.getNumNodesInRoute());
        assertEquals("BCD", r1.getRouteNodes().stream().map(Node::name).collect(joining()));

        assertEquals("ABCD", r2.toString(), "It should not have modified the cloned object's list.");
        assertEquals(4, r2.getNumNodesInRoute());
        assertEquals("ABCD", r2.getRouteNodes().stream().map(Node::name).collect(joining()));
    }

    @Test
    public void testAddingNodeAndCopying() {
        Route r1 = new Route();
        r1.addNode(new Node("a"));
        r1.addNode(new Node("b"));
        r1.addNode(new Node("c"));

        Route r2 = new Route(
                r1,
                new Node("d"));

        assertEquals("ABC", r1.toString());
        assertEquals(3, r1.getNumNodesInRoute());
        assertEquals("ABC", r1.getRouteNodes().stream().map(Node::name).collect(joining()));

        assertEquals("ABCD", r2.toString());
        assertEquals(4, r2.getNumNodesInRoute());
        assertEquals("ABCD", r2.getRouteNodes().stream().map(Node::name).collect(joining()));
    }
}
