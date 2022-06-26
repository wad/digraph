package org.wadhome.digraph.logic;

import java.util.*;

import static org.wadhome.digraph.setup.Output.show;

public class DirectedWeightedGraph {

    // The outer key is a given starting node.
    // For each of those, there is a map of the paths leading from it.
    // Each of those paths is in the form of a map (key being the destination node)
    // to a set of weights.
    // This is because there can be multiple paths between A and B, with different weights.
    // It's a set, because I'm pretty sure we don't want duplicate paths with the exact same source,
    // destination, and weight.
    final Map<Node, Map<Node, Set<Integer>>> pathsByStartingNode = new HashMap<>();

    public DirectedWeightedGraph(String commaDelimitedWeightedEdges) {
        if (commaDelimitedWeightedEdges == null) {
            commaDelimitedWeightedEdges = "";
        }

        Arrays.stream(commaDelimitedWeightedEdges.split(","))
                .map(WeightedEdge::new)
                .forEach(this::addWeightedEdge);
    }

    public void addWeightedEdge(WeightedEdge weightedEdge) {

        Node sourceNode = weightedEdge.getSourceNode();
        Node destinationNode = weightedEdge.getDestNodeWithWeight().destinationNode();
        int weight = weightedEdge.getDestNodeWithWeight().weight();

        Map<Node, Set<Integer>> weightsByDestinationNode = pathsByStartingNode.computeIfAbsent(
                sourceNode,
                k -> new HashMap<>());
        Set<Integer> weightsToThisDestination = weightsByDestinationNode.computeIfAbsent(
                destinationNode,
                k -> new HashSet<>());
        weightsToThisDestination.add(weight);
    }

    public Map<Node, Set<Integer>> getAllPathsFromNode(Node sourceNode) {
        return pathsByStartingNode.get(sourceNode);
    }

    // This just dumps the data to the screen, in a consistent order, so that test code can validate.
    public void showAll() {
        StringBuilder builder = new StringBuilder();
        List<Node> startingNodes = pathsByStartingNode.keySet().stream().sorted().toList();
        for (Node startingNode : startingNodes) {
            builder.append(startingNode.name()).append(":");

            boolean isFirstInList = true;
            Map<Node, Set<Integer>> pathsFromHere = pathsByStartingNode.get(startingNode);
            List<Node> destinationNodes = pathsFromHere.keySet().stream().sorted().toList();
            for (Node destNode : destinationNodes) {
                List<Integer> weights = pathsFromHere.get(destNode).stream().sorted().toList();
                for (Integer weight : weights) {
                    if (isFirstInList) {
                        isFirstInList = false;
                    } else {
                        builder.append(",");
                    }
                    builder.append(destNode.name()).append(weight);
                }
            }
            builder.append("\n");
        }
        show(builder.toString(), true);
    }
}
