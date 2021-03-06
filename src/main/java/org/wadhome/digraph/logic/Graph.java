package org.wadhome.digraph.logic;

import java.util.*;

import static org.wadhome.digraph.setup.Output.show;

public class Graph {

    // The outer key is a given starting node.
    // For each of those, there is a map of the routes leading from it.
    // Each of those routes is in the form of a map (key being the destination node)
    // to a set of weights.
    // This is because there can be multiple routes between A and B, with different weights.
    // It's a set, because I'm pretty sure we don't want duplicate routes with the exact same source,
    // destination, and weight.
    final Map<Node, Map<Node, Set<Integer>>> routesByStartingNode = new HashMap<>();

    // We cannot use the keyset of the map to routes, because that may not include destination nodes.
    final Set<Node> nodesThatExist = new HashSet<>();

    public Graph(String commaDelimitedWeightedEdges) {
        if (commaDelimitedWeightedEdges == null) {
            commaDelimitedWeightedEdges = "";
        }

        Arrays.stream(commaDelimitedWeightedEdges.split(","))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .map(Edge::new)
                .forEach(this::addWeightedEdge);
    }

    public void addWeightedEdge(Edge edge) {
        Node sourceNode = edge.getSourceNode();
        nodesThatExist.add(sourceNode);

        Node destinationNode = edge.getDestinationNode();
        nodesThatExist.add(destinationNode);

        int weight = edge.getWeight();

        Map<Node, Set<Integer>> weightsByDestinationNode = routesByStartingNode.computeIfAbsent(
                sourceNode,
                k -> new HashMap<>());
        Set<Integer> weightsToThisDestination = weightsByDestinationNode.computeIfAbsent(
                destinationNode,
                k -> new HashSet<>());
        weightsToThisDestination.add(weight);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted") // just makes more sense this way
    public boolean doesNodeExist(Node node) {
        return nodesThatExist.contains(node);
    }

    public Map<Node, Set<Integer>> getAllRoutesFromNode(Node sourceNode) {
        return routesByStartingNode.get(sourceNode);
    }

    // This just dumps the data to the screen, in a consistent order, so that test code can validate.
    public void showAll() {
        StringBuilder builder = new StringBuilder();
        List<Node> startingNodes = routesByStartingNode.keySet().stream().sorted().toList();
        for (Node startingNode : startingNodes) {
            builder.append(startingNode.name()).append(":");
            boolean isFirstInList = true;
            Map<Node, Set<Integer>> routesFromHere = routesByStartingNode.get(startingNode);
            List<Node> destinationNodes = routesFromHere.keySet().stream().sorted().toList();
            for (Node destNode : destinationNodes) {
                List<Integer> weights = routesFromHere.get(destNode).stream().sorted().toList();
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
