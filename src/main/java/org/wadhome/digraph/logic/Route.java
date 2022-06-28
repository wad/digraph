package org.wadhome.digraph.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * This is currently just a wrapper around a list, with a String that is kept in sync with it.
 * If we were to want to replace the name field of the Node class with something other than
 * just a single character, this class would let that happen without changing anything else in the logic of the code,
 * as that nodesAsString member variable is only used for debugging tests.
 */
public class Route implements Comparable<Route> {

    private List<Node> nodes = new ArrayList<>();

    // This is just for debugging. If we use something other than single characters for node names,
    // we'll probably want to stick some delimiters into this string.
    private String nodesAsString = "";

    public Route() {
    }

    // We need a copy constructor, because we save off instances of route objects along the way
    public Route(Route originalRoute) {
        nodes = new ArrayList<>(originalRoute.getRouteNodes());
        nodesAsString = originalRoute.nodesAsString;
    }

    public Route(
            Route originalRoute,
            Node nodeToAddToEnd) {
        this(originalRoute);
        addNode(nodeToAddToEnd);
    }

    public void addNode(Node node) {
        nodes.add(node);
        nodesAsString += node.name();
    }

    public List<Node> getRouteNodes() {
        // Let's not leak the internals! Can give out a copy is all.
        return new ArrayList<>(nodes);
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public int getNumNodesInRoute() {
        return nodes.size();
    }

    public Node getFirstNodeInRoute() {
        return nodes.get(0);
    }

    public void removeFirstNodeInRoute() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot remove first node from an empty route.");
        }
        nodes.remove(0);
        nodesAsString = nodesAsString.substring(1);
    }

    @Override
    public String toString() {
        return nodesAsString;
    }

    @Override
    public int compareTo(Route o) {
        if (o == this) {
            return 0;
        }

        String myString = toString();
        String otherString = o.toString();
        if (myString.length() == otherString.length()) {
            return myString.compareTo(otherString);
        }

        // If they are different lengths, let's return the shorter routes before the longer routes.
        return otherString.length() - myString.length();
    }
}
