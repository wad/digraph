package org.wadhome.digraph.logic;

import java.util.ArrayList;
import java.util.List;

public class Route implements Comparable<Route> {

    private List<Node> nodes;
    private String nodesAsString;

    public Route() {
        nodes = new ArrayList<>();
        nodesAsString = "";
    }

    // We need a copy constructor, because we save off instances of route objects along the way
    public Route(Route originalRoute) {
        nodes = originalRoute.getRouteNodes();
        nodesAsString = originalRoute.nodesAsString;
    }

    public Route(
            Route originalRoute,
            Node nodeToAddToEnd) {
        this(originalRoute);
        addNode(nodeToAddToEnd);
    }

    // Use this method if you don't care about the total weight
    public void addNode(Node node) {
        nodes.add(node);
        nodesAsString += node.name();
    }

    public List<Node> getRouteNodes() {
        return nodes;
    }

    public boolean isEmpty() {
        return nodes == null || nodes.isEmpty();
    }

    public int getNumNodesInRoute() {
        return nodes == null ? 0 : nodes.size();
    }

    public Node getFirstNodeInRoute() {
        return nodes == null ? null : nodes.get(0);
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

        if (myString == null && otherString == null) {
            return 0;
        }
        if (myString == null) {
            return -1;
        }
        if (otherString == null) {
            return 1;
        }

        if (myString.length() == otherString.length()) {
            return myString.compareTo(otherString);
        }

        // If they are different lengths, let's return the shorter routes before the longer routes.
        return otherString.length() - myString.length();
    }
}
