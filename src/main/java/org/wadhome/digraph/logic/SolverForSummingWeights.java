package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * The logic in this class is around computing the total weights of a specific route
 * through the graph.
 */
public class SolverForSummingWeights extends Solver {

    public SolverForSummingWeights(Graph graph) {
        super(graph);
    }

    public Answer computeTotalWeightOfSpecificRoute(
            Route nodesInVisitOrder,
            boolean preferringLessWeightOptions) {
        Answer answer = Answer.numeric(0);
        computeTotalWeightOfSpecificRouteHelper(
                new Route(nodesInVisitOrder), // use the copy constructor, because our method is destructive
                answer,
                preferringLessWeightOptions);
        return answer;
    }

    void computeTotalWeightOfSpecificRouteHelper(
            Route remainingNodesToVisit,
            Answer answerSoFar,
            boolean preferringLessWeightOptions) {

        // If, for some reason, there is nothing, just return.
        if (remainingNodesToVisit.isEmpty()) {
            return;
        }

        Node currentNode = remainingNodesToVisit.getFirstNodeInRoute();
        Map<Node, Set<Integer>> allDestinationsFromCurrentNode = graph.getAllRoutesFromNode(currentNode);

        // We need to make sure that the current node actually exists.
        // Need to consider nodes that only exist as destinations, not only sources.
        if (!graph.doesNodeExist(currentNode)) {
            answerSoFar.updateToNotFound();
            return;
        }

        boolean thisIsTheFinalNode = remainingNodesToVisit.getNumNodesInRoute() == 1;
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
        remainingNodesToVisit.removeFirstNodeInRoute();
        Node nextNode = remainingNodesToVisit.getFirstNodeInRoute();
        Set<Integer> weightsAvailable = allDestinationsFromCurrentNode.get(nextNode);
        if (weightsAvailable == null) {
            // The next node isn't reachable directly from this node.
            answerSoFar.updateToNotFound();
            return;
        }

        // Add the weight to our sum.
        int weightToNextNode = preferringLessWeightOptions
                ? Collections.min(weightsAvailable)
                : Collections.max(weightsAvailable);
        answerSoFar.addToNumericResult(weightToNextNode);

        // Recursive call, let's do this again.
        computeTotalWeightOfSpecificRouteHelper(
                remainingNodesToVisit,
                answerSoFar,
                preferringLessWeightOptions);
    }
}
