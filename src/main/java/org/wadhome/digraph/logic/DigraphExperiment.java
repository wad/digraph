package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;
import org.wadhome.digraph.setup.ArgumentValues;
import org.wadhome.digraph.setup.Request;

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
        goToNextNode(nodesInVisitOrder, answer, preferringLessWeightOptions);
        return answer;
    }

    void goToNextNode(
            List<Node> remainingNodesToVisit,
            Answer weightToGetHere,
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
            weightToGetHere.updateToNotFound();
            return;
        }

        boolean thisIsTheFinalNode = remainingNodesToVisit.size() == 1;
        if (thisIsTheFinalNode) {
            // We have arrived, and the weights in the answer are correct.
            return;
        }

        // If this is a dead end, we can't go any further.
        if (allDestinationsFromCurrentNode == null) {
            weightToGetHere.updateToNotFound();
            return;
        }

        // Now consider the next node to visit.
        remainingNodesToVisit.remove(0);
        Node nextNode = remainingNodesToVisit.get(0);
        Set<Integer> weightsAvailable = allDestinationsFromCurrentNode.get(nextNode);
        if (weightsAvailable == null) {
            // The next node isn't reachable directly from this node.
            weightToGetHere.updateToNotFound();
            return;
        }

        // Add the weight to our sum.
        int weightToNextNode = preferringLessWeightOptions
                ? findSmallestWeight(weightsAvailable)
                : findLargestWeight(weightsAvailable);
        weightToGetHere.addToNumericResult(weightToNextNode);

        // Recursive call, let's do this again.
        goToNextNode(
                remainingNodesToVisit,
                weightToGetHere,
                preferringLessWeightOptions);
    }

    Answer computeNumPathsLimitedByVisitingNodes(
            Node startNode,
            Node endNode,
            int numVisitedNodes) {
        // todo
        return Answer.numeric(-1);
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
