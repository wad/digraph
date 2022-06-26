package org.wadhome.digraph;

public record DestinationNodeWithWeight(
        Node destinationNode,
        int weight) implements Comparable<DestinationNodeWithWeight> {

    @Override
    public String toString() {
        return destinationNode.name() + weight;
    }

    @Override
    public int compareTo(DestinationNodeWithWeight o) {
        return toString().compareTo(o.toString());
    }
}
