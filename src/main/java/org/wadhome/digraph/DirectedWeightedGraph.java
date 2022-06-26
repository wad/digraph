package org.wadhome.digraph;

import java.util.*;

import static org.wadhome.digraph.Application.show;

public class DirectedWeightedGraph {

    private final Map<Node, Set<DestinationNodeWithWeight>> pathsByStartingNode = new HashMap<>();

    public DirectedWeightedGraph(String commaDelimitedWeightedEdges) {
        if (commaDelimitedWeightedEdges == null) {
            commaDelimitedWeightedEdges = "";
        }

        Arrays.stream(commaDelimitedWeightedEdges.split(","))
                .map(WeightedEdge::new)
                .forEach(this::addWeightedEdge);
    }

    public void addWeightedEdge(WeightedEdge weightedEdge) {
        pathsByStartingNode
                .computeIfAbsent(
                        weightedEdge.getSourceNode(),
                        k -> new HashSet<>())
                .add(weightedEdge.getDestNodeWithWeight());
    }

    // This just dumps the data to the screen, in a consistent order, so that test code can validate.
    public void showAll() {
        StringBuilder builder = new StringBuilder();
        List<Node> startingNodes = pathsByStartingNode
                .keySet()
                .stream()
                .sorted()
                .toList();
        for (Node startingNode : startingNodes) {
            builder.append(startingNode.name()).append(":");
            List<String> destinationNodeWithWeights = pathsByStartingNode.get(startingNode)
                    .stream()
                    .sorted()
                    .map(Object::toString)
                    .toList();
            builder.append(String.join(",", destinationNodeWithWeights)).append("\n");
        }
        show(builder.toString());
    }
}
