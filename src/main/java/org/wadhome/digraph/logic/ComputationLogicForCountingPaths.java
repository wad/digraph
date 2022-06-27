package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;

import java.util.HashSet;
import java.util.Set;

/**
 * The logic in this class is around computing the number of paths found from
 * a starting node to an ending node, with various types of limits.
 */
public class ComputationLogicForCountingPaths extends ComputationLogic {

    public ComputationLogicForCountingPaths(Graph graph) {
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
        findAllPathsToDestinationFromThisNode(
                startNode,
                endNode,
                pathsFound,
                startNode.name(),
                maxNumVisitedNodes); // The starting node isn't counted as a node visited.
        Answer answer = Answer.numeric(pathsFound.size());
        answer.setComment(String.join(",", pathsFound));
        return answer;
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

    public Answer computeNumPathsLimitedByTotalWeight(
            Node startNode,
            Node endNode,
            int maxTotalWeight) {
        // todo
        return Answer.numeric(-1);
    }
}
