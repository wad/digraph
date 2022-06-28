package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * The logic in this class is around computing the number of paths found from
 * a starting node to an ending node, with various types of limits.
 */
public class SolverForCountingPaths extends Solver {

    public SolverForCountingPaths(Graph graph) {
        super(graph);
    }

    public Answer computeNumPathsLimitedByVisitingNodes(
            Node startNode,
            Node endNode,
            int maxNumVisitedNodes) {
        // Compute number of paths between two nodes, up to the specified maximum number of nodes visited.
        // Do we consider AB4 and AB7 as different paths? No, the path is AB, so those are the same.

        // If either the start or ending node doesn't exist, there are zero paths.
        if (!graph.doesNodeExist(startNode) || !graph.doesNodeExist(endNode)) {
            return Answer.numeric(0);
        }

        Set<String> pathsFound = new HashSet<>();
        findAllPathsToDestinationFromThisNodeUpToMaxNodesVisited(
                startNode,
                endNode,
                pathsFound,
                startNode.name(),
                maxNumVisitedNodes); // The starting node isn't counted as a node visited.
        Answer answer = Answer.numeric(pathsFound.size());
        answer.setComment(String.join(",", pathsFound));
        return answer;
    }

    void findAllPathsToDestinationFromThisNodeUpToMaxNodesVisited(
            Node currentNode,
            Node destinationNode,
            Set<String> pathsFound,
            String myPathSoFar,
            int maxNumVisitedNodesRemaining) {

        // Exit condition, we're out of visits to consider.
        if (maxNumVisitedNodesRemaining <= 0) {
            return;
        }

        Set<Node> nodesWeCanVisitNext = graph.getAllPathsFromNode(currentNode).keySet();
        for (Node potentialNextNode : nodesWeCanVisitNext) {

            // We found the destination node, we need to save this as a solution path.
            if (potentialNextNode.equals(destinationNode)) {
                pathsFound.add(myPathSoFar + potentialNextNode.name());
                // Don't return, though, there may be more visits we can make, and come back here again later.
            }

            // Go wide, for each connected node, make a recursive call.
            findAllPathsToDestinationFromThisNodeUpToMaxNodesVisited(
                    potentialNextNode,
                    destinationNode,
                    pathsFound,
                    myPathSoFar + potentialNextNode.name(),
                    maxNumVisitedNodesRemaining - 1);
        }
    }

    public Answer computeNumPathsLimitedByTotalWeight(
            Node startNode,
            Node endNode,
            int maxTotalWeight) {

        // If either the start or ending node doesn't exist, there are zero paths.
        if (!graph.doesNodeExist(startNode) || !graph.doesNodeExist(endNode)) {
            return Answer.numeric(0);
        }

        Set<String> pathsFound = new HashSet<>();
        findAllPathsToDestinationFromThisNodeNotExceedingMaxWeight(
                startNode,
                endNode,
                pathsFound,
                startNode.name(),
                maxTotalWeight);
        Answer answer = Answer.numeric(pathsFound.size());
        answer.setComment(String.join(",", pathsFound));
        return answer;
    }

    void findAllPathsToDestinationFromThisNodeNotExceedingMaxWeight(
            Node currentNode,
            Node destinationNode,
            Set<String> pathsFound,
            String myPathSoFar,
            int maxTotalWeightRemaining) {

        // Exit condition, we've used up all our allocated weight.
        if (maxTotalWeightRemaining <= 0) {
            return;
        }

        Map<Node, Set<Integer>> allPathsFromCurrentNode = graph.getAllPathsFromNode(currentNode);
        Set<Node> nodesWeCanVisitNext = allPathsFromCurrentNode.keySet();
        for (Node potentialNextNode : nodesWeCanVisitNext) {

            Set<Integer> weightsAvailableToThereFromHere = allPathsFromCurrentNode
                    .get(potentialNextNode)
                    .stream()

                    // discard all options that would be too much weight
                    .filter(weight -> weight <= maxTotalWeightRemaining)

                    .collect(toSet());

            for (Integer weight : weightsAvailableToThereFromHere) {

                // We found the destination node, we need to save this as a solution path.
                if (potentialNextNode.equals(destinationNode)) {
                    pathsFound.add(myPathSoFar + potentialNextNode.name());
                    // Don't return, though, there may be more visits we can make,
                    // and we might come back here again later.
                }

                int weightThatWouldBeRemaining = maxTotalWeightRemaining - weight;

                // Go wide, for each connected node, make a recursive call.
                findAllPathsToDestinationFromThisNodeUpToMaxNodesVisited(
                        potentialNextNode,
                        destinationNode,
                        pathsFound,
                        myPathSoFar + potentialNextNode.name(),
                        weightThatWouldBeRemaining);
            }
        }
    }
}
