package org.wadhome.digraph.logic;

// This class is actually only used when loading the weighted graph data into the program's internal representation.
public class WeightedEdge {

    final Node sourceNode;
    final DestinationNodeWithWeight destNodeWithWeight;

    // This would be in the form of AB89 where the source node is A, the destination node is B, and the weight is 89.
    public WeightedEdge(String weightedEdgeAsString) {
        if (weightedEdgeAsString == null) {
            throw new RuntimeException("Got a null weighted edge string somehow, fix the bug.");
        }

        weightedEdgeAsString = weightedEdgeAsString.trim();
        if (weightedEdgeAsString.length() < 3) {
            throw new RuntimeException("There should be more than 3 characters in a weighted edge! Got this: '" + weightedEdgeAsString + "'");
        }

        sourceNode = new Node(String.valueOf(weightedEdgeAsString.charAt(0)));

        try {
            destNodeWithWeight = new DestinationNodeWithWeight(
                    new Node(String.valueOf(weightedEdgeAsString.charAt(1))),
                    Integer.parseInt(weightedEdgeAsString.substring(2)));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid weight found in weighted edge '" + weightedEdgeAsString + "'.", e);
        }
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public DestinationNodeWithWeight getDestNodeWithWeight() {
        return destNodeWithWeight;
    }
}
