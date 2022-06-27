package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The logic in this class is around computing the total weights of a specific route
 * through the graph.
 */
public class ComputationLogicForSummingWeights extends ComputationLogic {

    public ComputationLogicForSummingWeights(DirectedWeightedGraph graph) {
        super(graph);
    }

    public Answer computeTotalWeightOfSpecificRoute(
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
