package org.wadhome.digraph;

public record DestinationNodeWithWeight(
        Node destinationNode,
        int weight) {

    @Override
    public String toString() {
        return destinationNode.name() + weight;
    }
}
