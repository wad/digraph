package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * The logic in this class is around computing the number of routes found from
 * a starting node to an ending node, with various types of limits.
 */
public class SolverForCountingRoutes extends Solver {

    public SolverForCountingRoutes(Graph graph) {
        super(graph);
    }

    public Answer computeNumRoutesLimitedByVisitingNodes(
            Node startNode,
            Node endNode,
            int maxNumVisitedNodes) {
        // Compute number of routes between two nodes, up to the specified maximum number of nodes visited.
        // Do we consider AB4 and AB7 as different routes? No, the route is AB, so those are the same,
        // even though they have different weights.

        // If either the start or ending node doesn't exist, there are zero routes.
        if (!graph.doesNodeExist(startNode) || !graph.doesNodeExist(endNode)) {
            return Answer.numeric(0);
        }

        Route newRoute = new Route();
        newRoute.addNode(startNode);

        Set<Route> routesFound = new HashSet<>();
        findAllRoutesToDestinationFromThisNodeUpToMaxNodesVisited(
                startNode,
                endNode,
                routesFound,
                newRoute,
                maxNumVisitedNodes); // The starting node isn't counted as a node visited.
        Answer answer = Answer.numeric(routesFound.size());
        answer.setRoutesChosen(routesFound);
        return answer;
    }

    void findAllRoutesToDestinationFromThisNodeUpToMaxNodesVisited(
            Node currentNode,
            Node destinationNode,
            Set<Route> routesFound,
            Route myRouteSoFar,
            int maxNumVisitedNodesRemaining) {

        // Exit condition, we're out of visits to consider.
        if (maxNumVisitedNodesRemaining <= 0) {
            return;
        }

        Set<Node> nodesWeCanVisitNext = graph.getAllRoutesFromNode(currentNode).keySet();
        for (Node potentialNextNode : nodesWeCanVisitNext) {

            // We found the destination node, we need to save this as a solution route.
            if (potentialNextNode.equals(destinationNode)) {
                routesFound.add(new Route(myRouteSoFar, potentialNextNode));
                // Don't return, though, there may be more visits we can make, and come back here again later.
            }

            // Go wide, for each connected node, make a recursive call.
            findAllRoutesToDestinationFromThisNodeUpToMaxNodesVisited(
                    potentialNextNode,
                    destinationNode,
                    routesFound,
                    new Route(myRouteSoFar, potentialNextNode),
                    maxNumVisitedNodesRemaining - 1);
        }
    }

    public Answer computeNumRoutesLimitedByTotalWeight(
            Node startNode,
            Node endNode,
            int maxTotalWeight) {

        // If either the start or ending node doesn't exist, there are zero routes.
        if (!graph.doesNodeExist(startNode) || !graph.doesNodeExist(endNode)) {
            return Answer.numeric(0);
        }

        Route newRoute = new Route();
        newRoute.addNode(startNode);

        Set<Route> routesFound = new HashSet<>();
        findAllRoutesToDestinationFromThisNodeNotExceedingMaxWeight(
                startNode,
                endNode,
                routesFound,
                newRoute,
                maxTotalWeight);
        Answer answer = Answer.numeric(routesFound.size());
        answer.setRoutesChosen(routesFound);
        return answer;
    }

    void findAllRoutesToDestinationFromThisNodeNotExceedingMaxWeight(
            Node currentNode,
            Node destinationNode,
            Set<Route> routesFound,
            Route myRouteSoFar,
            int maxTotalWeightRemaining) {

        // Exit condition, we've used up all our allocated weight.
        if (maxTotalWeightRemaining <= 0) {
            return;
        }

        Map<Node, Set<Integer>> allRoutesFromCurrentNode = graph.getAllRoutesFromNode(currentNode);
        if (allRoutesFromCurrentNode == null) {
            // there is no way to move forward
            return;
        }

        Set<Node> nodesWeCanVisitNext = allRoutesFromCurrentNode.keySet();
        for (Node potentialNextNode : nodesWeCanVisitNext) {

            Set<Integer> weightsAvailableToThereFromHere = allRoutesFromCurrentNode
                    .get(potentialNextNode)
                    .stream()

                    // discard all options that would be too much weight
                    .filter(weight -> weight <= maxTotalWeightRemaining)

                    .collect(toSet());

            for (Integer weight : weightsAvailableToThereFromHere) {

                // We found the destination node, we need to save this as a solution route.
                if (potentialNextNode.equals(destinationNode)) {
                    routesFound.add(new Route(myRouteSoFar, potentialNextNode));
                    // Don't return, though, there may be more visits we can make,
                    // and we might come back here again later.
                }

                int weightThatWouldBeRemaining = maxTotalWeightRemaining - weight;

                // Go wide, for each connected node, make a recursive call.
                findAllRoutesToDestinationFromThisNodeNotExceedingMaxWeight(
                        potentialNextNode,
                        destinationNode,
                        routesFound,
                        new Route(myRouteSoFar, potentialNextNode),
                        weightThatWouldBeRemaining);
            }
        }
    }
}
