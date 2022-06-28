package org.wadhome.digraph.logic;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Route implements Comparable<Route> {

    private List<Node> nodes = new ArrayList<>();
    private int totalWeight = 0;
    private boolean totalWeightIsValid = true;

    // Use this method if you don't care about the total weight
    public void add(Node node) {
        nodes.add(node);
        totalWeightIsValid = false;
    }

    public void add(
            Node node,
            int weight) {
        add(node);
        this.totalWeight += weight;
    }

    public int getTotalWeight() {
        if (!totalWeightIsValid) {
            throw new RuntimeException("Bug in code, total weight not valid for route.");
        }
        return totalWeight;
    }

    @Override
    public String toString() {
        return nodes
                .stream()
                .map(Node::name)
                .collect(joining());
    }

    @Override
    public int compareTo(Route o) {
        if (o == this) {
            return 0;
        }
        if (totalWeightIsValid != o.totalWeightIsValid) {
            return 1;
        }
        return toString().compareTo(o.toString()); // slow, but not caring here.
    }
}
