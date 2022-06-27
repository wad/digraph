package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;
import org.wadhome.digraph.setup.ArgumentValues;
import org.wadhome.digraph.setup.Request;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.wadhome.digraph.setup.Argument.*;

public class DigraphExperiment {

    private final DirectedWeightedGraph graph;

    public DigraphExperiment(DirectedWeightedGraph directedWeightedGraph) {
        this.graph = directedWeightedGraph;
    }

    public Answer answerRequest(
            Request request,
            ArgumentValues argumentValues) {
        switch (request) {
            case LoadAndDisplay:
                graph.showAll();
                return Answer.noAnswerExpected();
            case ComputeTotalWeightOfSpecificRoutePreferringLessWeight:
                return computeTotalWeightOfSpecificRoute(
                        argumentValues.getValueAsListOfNodes(ListOfNodes),
                        true);
            case ComputeTotalWeightOfSpecificRoutePreferringMoreWeight:
                return computeTotalWeightOfSpecificRoute(
                        argumentValues.getValueAsListOfNodes(ListOfNodes),
                        false);
            case ComputeNumPathsBetweenTwoNodesWithLimitOfVisitedNodes:
                return computeNumPathsLimitedByVisitingNodes(
                        argumentValues.getValueAsNode(StartNodeName),
                        argumentValues.getValueAsNode(EndNodeName),
                        argumentValues.getValueAsInt(MaxNumNodesVisited));
            case ComputeNumPathsBetweenTwoNodesWithLimitOfTotalWeight:
                return computeNumPathsLimitedByTotalWeight(
                        argumentValues.getValueAsNode(StartNodeName),
                        argumentValues.getValueAsNode(EndNodeName),
                        argumentValues.getValueAsInt(MaxTotalWeight));
            case ComputeTotalWeightOfPathBetweenTwoNodesWithLeastTotalWeight:
                return computeRouteWithLeastTotalWeight(
                        argumentValues.getValueAsNode(StartNodeName),
                        argumentValues.getValueAsNode(EndNodeName));
            default:
                throw new IllegalStateException("Bug in code, unexpected value: " + request);
        }
    }

    Answer computeTotalWeightOfSpecificRoute(
            List<Node> nodesInVisitOrder,
            boolean preferringLessWeightOptions) {
        Answer answer = Answer.numeric(0);
        computeTotalWeightOfSpecificRouteHelper(
                nodesInVisitOrder,
                answer,
                preferringLessWeightOptions);
        return answer;
    }

    void computeTotalWeightOfSpecificRouteHelper(
            List<Node> remainingNodesToVisit,
            Answer answerSoFar,
            boolean preferringLessWeightOptions) {

        // If, for some reason, there is nothing, just return.
        if (remainingNodesToVisit.isEmpty()) {
            return;
        }

        Node currentNode = remainingNodesToVisit.get(0);
        Map<Node, Set<Integer>> allDestinationsFromCurrentNode = graph.getAllPathsFromNode(currentNode);

        // We need to make sure that the current node actually exists.
        // Need to consider nodes that only exist as destinations, not only sources.
        if (!graph.doesNodeExist(currentNode)) {
            answerSoFar.updateToNotFound();
            return;
        }

        boolean thisIsTheFinalNode = remainingNodesToVisit.size() == 1;
        if (thisIsTheFinalNode) {
            // We have arrived, and the weights in the answer are correct.
            return;
        }

        // If this is a dead end, we can't go any further.
        if (allDestinationsFromCurrentNode == null) {
            answerSoFar.updateToNotFound();
            return;
        }

        // Now consider the next node to visit.
        remainingNodesToVisit.remove(0);
        Node nextNode = remainingNodesToVisit.get(0);
        Set<Integer> weightsAvailable = allDestinationsFromCurrentNode.get(nextNode);
        if (weightsAvailable == null) {
            // The next node isn't reachable directly from this node.
            answerSoFar.updateToNotFound();
            return;
        }

        // Add the weight to our sum.
        int weightToNextNode = preferringLessWeightOptions
                ? findSmallestWeight(weightsAvailable)
                : findLargestWeight(weightsAvailable);
        answerSoFar.addToNumericResult(weightToNextNode);

        // Recursive call, let's do this again.
        computeTotalWeightOfSpecificRouteHelper(
                remainingNodesToVisit,
                answerSoFar,
                preferringLessWeightOptions);
    }

    Answer computeNumPathsLimitedByVisitingNodes(
            Node startNode,
            Node endNode,
            int maxNumVisitedNodes) {
        // Compute number of paths between two nodes, up to the specified maximum number of nodes visited.
        // Do we consider AB4 and AB7 as different paths? No, the path is AB, so those are the same.

        // If either the start or ending node doesn't exist, there are zero paths.
        if (!graph.doesNodeExist(startNode) || !graph.doesNodeExist(endNode)) {
            return Answer.numeric(0);
        }

        // If you can't visit any nodes, there are zero paths.
        if (maxNumVisitedNodes <= 0) {
            return Answer.numeric(0);
        }

        Set<String> pathsFound = new HashSet<>();
        findAllPathsToDestinationFromThisNode(
                startNode,
                endNode,
                pathsFound,
                "",
                maxNumVisitedNodes);
        return Answer.numeric(pathsFound.size());
    }

    void findAllPathsToDestinationFromThisNode(
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

            // We found the destination node, we need to save this as a solution path!
            if (potentialNextNode.equals(destinationNode)) {
                pathsFound.add(myPathSoFar + potentialNextNode.name());
                // Don't return, though, there may be more visits we can make, and come back here again later.
            }

            // Go wide, for each connected node, make a recursive call.
            findAllPathsToDestinationFromThisNode(
                    potentialNextNode,
                    destinationNode,
                    pathsFound,
                    myPathSoFar + potentialNextNode.name(),
                    maxNumVisitedNodesRemaining - 1);
        }
    }

    Answer computeNumPathsLimitedByTotalWeight(
            Node startNode,
            Node endNode,
            int maxTotalWeight) {
        // todo
        return Answer.numeric(-1);
    }

    Answer computeRouteWithLeastTotalWeight(
            Node startNode,
            Node endNode) {
        // todo
        return Answer.numeric(-1);
    }

    int findSmallestWeight(Set<Integer> weights) {
        int smallest = Integer.MAX_VALUE;
        for (Integer weight : weights) {
            if (weight < smallest) {
                smallest = weight;
            }
        }
        return smallest;
    }

    int findLargestWeight(Set<Integer> weights) {
        int largest = -1;
        for (Integer weight : weights) {
            if (weight > largest) {
                largest = weight;
            }
        }
        return largest;
    }
}
