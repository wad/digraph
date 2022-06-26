package org.wadhome.digraph;

import java.util.*;

import static org.wadhome.digraph.Application.show;

public class DirectedWeightedGraph {

    final Map<Node, Set<DestinationNodeWithWeight>> pathsByStartingNode = new HashMap<>();

    public static DirectedWeightedGraph setupViaCommaDelimitedString(String commaDelimitedWeightedEdges) {
        if (commaDelimitedWeightedEdges == null) {
            commaDelimitedWeightedEdges = "";
        }

        DirectedWeightedGraph directedWeightedGraph = new DirectedWeightedGraph();
        Arrays.stream(commaDelimitedWeightedEdges.split(","))
                .map(WeightedEdge::new)
                .forEach(directedWeightedGraph::addWeightedEdge);
        return directedWeightedGraph;
    }

    public void addWeightedEdge(WeightedEdge weightedEdge) {
        pathsByStartingNode
                .computeIfAbsent(
                        weightedEdge.getSourceNode(),
                        k -> new HashSet<>())
                .add(weightedEdge.getDestNodeWithWeight());
    }

    // This just dumps the data to the screen.
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
