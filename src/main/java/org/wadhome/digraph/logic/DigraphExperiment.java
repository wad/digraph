package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.ArgumentValues;
import org.wadhome.digraph.setup.Request;

import java.util.List;

import static org.wadhome.digraph.setup.Argument.*;

public class DigraphExperiment {

    private final DirectedWeightedGraph directedWeightedGraph;

    public DigraphExperiment(DirectedWeightedGraph directedWeightedGraph) {
        this.directedWeightedGraph = directedWeightedGraph;
    }

    public void enterInteractiveMode() {
        // todo
    }

    public void answerRequest(
            Request request,
            ArgumentValues argumentValues) {
        switch (request) {
            case LoadAndDisplay -> {
                directedWeightedGraph.showAll();
            }
            case ComputeTotalWeightOfSpecificRoute -> computeTotalWeightOfSpecificRoute(
                    argumentValues.getValueAsListOfNodes(ListOfNodes));
            case ComputeNumPathsBetweenTwoNodesWithLimitOfVisitedNodes -> computeNumPathsLimitedByVisitingNodes(
                    argumentValues.getValueAsNode(StartNodeName),
                    argumentValues.getValueAsNode(EndNodeName),
                    argumentValues.getValueAsInt(MaxNumNodesVisited));
            case ComputeNumPathsBetweenTwoNodesWithLimitOfTotalWeight -> computeNumPathsLimitedByTotalWeight(
                    argumentValues.getValueAsNode(StartNodeName),
                    argumentValues.getValueAsNode(EndNodeName),
                    argumentValues.getValueAsInt(MaxTotalWeight));
            case ComputeTotalWeightOfPathBetweenTwoNodesWithLeastTotalWeight -> computeRouteWithLeastTotalWeight(
                    argumentValues.getValueAsNode(StartNodeName),
                    argumentValues.getValueAsNode(EndNodeName));
            default -> throw new IllegalStateException("Bug in code, unexpected value: " + request);
        }
    }

    int computeTotalWeightOfSpecificRoute(
            List<Node> nodesInVisitOrder) {
        // todo
        return -1;
    }

    int computeNumPathsLimitedByVisitingNodes(
            Node startNode,
            Node endNode,
            int numVisitedNodes) {
        // todo
        return -1;
    }

    int computeNumPathsLimitedByTotalWeight(
            Node startNode,
            Node endNode,
            int maxTotalWeight) {
        // todo
        return -1;
    }

    int computeRouteWithLeastTotalWeight(
            Node startNode,
            Node endNode) {
        // todo
        return -1;
    }
}
