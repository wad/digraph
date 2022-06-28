package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;

/**
 * The logic in this class is around computing the optimum route through the graph,
 * from a starting node to an ending node.
 */
public class SolverForFindingOptimumRoute extends Solver {

    public SolverForFindingOptimumRoute(Graph graph) {
        super(graph);
    }

    public Answer computeRouteWithLeastTotalWeight(
            Node startNode,
            Node endNode) {

        // If either the start or ending node doesn't exist, there are zero routes.
        if (!graph.doesNodeExist(startNode) || !graph.doesNodeExist(endNode)) {
            return Answer.notFound();
        }

        Route route = new Route();
        route.addNode(startNode);

        return computeRouteWithLeastTotalWeightHelper(
                startNode,
                endNode,
                new HashSet<>(),
                route,
                0);
    }

    Answer computeRouteWithLeastTotalWeightHelper(
            Node currentNode,
            Node destinationNode,
            Set<Node> visitedNodes,
            Route routeSoFar,
            int weightSoFar) {

        if (currentNode.equals(destinationNode)) {
            Answer answer = Answer.numeric(weightSoFar);
            answer.setRoutesChosen(singleton(new Route(routeSoFar)));
            return  answer;
        }

        visitedNodes.add(currentNode);

        Map<Node, Set<Integer>> allRoutesFromNode = graph.getAllRoutesFromNode(currentNode);
        if (allRoutesFromNode == null) {
            return Answer.notFound();
        }

        Set<Node> nodesToTry = allRoutesFromNode
                .keySet()
                .stream()
                .filter(node -> !visitedNodes.contains(node))
                .collect(toSet());
        if (nodesToTry.isEmpty()) {
            return Answer.notFound();
        }

        int leastWeightFound = Integer.MAX_VALUE;
        Answer bestAnswer = Answer.notFound();
        for (Node node : nodesToTry) {
            int smallestWeightToThatNode = Collections.min(allRoutesFromNode.get(node));
            Answer answer = computeRouteWithLeastTotalWeightHelper(
                    node,
                    destinationNode,
                    new HashSet<>(visitedNodes),
                    new Route(routeSoFar, node),
                    weightSoFar + smallestWeightToThatNode);
            if (answer.getWasAnswerFound()) {
                int weightFound = answer.getNumericResult();
                if (weightFound < leastWeightFound) {
                    leastWeightFound = weightFound;
                    bestAnswer = answer;
                }
            }
        }
        return bestAnswer;
    }
}
