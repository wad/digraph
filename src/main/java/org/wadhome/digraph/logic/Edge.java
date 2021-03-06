package org.wadhome.digraph.logic;

// This class is actually only used when loading the weighted graph data into the program's internal representation.
public class Edge {

    final Node sourceNode;
    final Node destinationNode;
    final int weight;

    // This would be in the form of AB89 where the source node is A, the destination node is B, and the weight is 89.
    public Edge(String weightedEdgeAsString) {
        if (weightedEdgeAsString == null) {
            throw new RuntimeException("Got a null weighted edge string somehow, fix the bug.");
        }

        weightedEdgeAsString = weightedEdgeAsString.trim();
        if (weightedEdgeAsString.length() < 3) {
            throw new RuntimeException("There should be more than 3 characters in a weighted edge! Got this: '" + weightedEdgeAsString + "'");
        }

        sourceNode = new Node(String.valueOf(weightedEdgeAsString.charAt(0)));

        try {
            destinationNode = new Node(String.valueOf(weightedEdgeAsString.charAt(1)));
            weight = Integer.parseInt(weightedEdgeAsString.substring(2));
            if (weight <= 0) {
                throw new RuntimeException("Not permitting weight of zero or less, as that allows for infinite solutions to some problems.");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid weight found in weighted edge '" + weightedEdgeAsString + "'.", e);
        }
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public int getWeight() {
        return weight;
    }
}
