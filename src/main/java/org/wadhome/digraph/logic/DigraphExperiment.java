package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.ArgumentValues;
import org.wadhome.digraph.setup.Request;

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
                    new Node(argumentValues.getValueAsString(StartNodeName)),
                    new Node(argumentValues.getValueAsString(EndNodeName)));
            case ComputeNumPathsBetweenTwoNodesWithLimitOfVisitedNodes -> computeNumPathsLimitedByVisitingNodes(
                    new Node(argumentValues.getValueAsString(StartNodeName)),
                    new Node(argumentValues.getValueAsString(EndNodeName)),
                    argumentValues.getValueAsInt(MaxNumNodesVisited));
            case ComputeNumPathsBetweenTwoNodesWithLimitOfTotalWeight -> computeNumPathsLimitedByTotalWeight(
                    new Node(argumentValues.getValueAsString(StartNodeName)),
                    new Node(argumentValues.getValueAsString(EndNodeName)),
                    argumentValues.getValueAsInt(MaxTotalWeight));
            case ComputeTotalWeightOfPathBetweenTwoNodesWithLeastTotalWeight -> computeRouteWithLeastTotalWeight(
                    new Node(argumentValues.getValueAsString(StartNodeName)),
                    new Node(argumentValues.getValueAsString(EndNodeName)));
            default -> throw new IllegalStateException("Unexpected value: " + request);
        }
    }

    void computeTotalWeightOfSpecificRoute(
            Node startNode,
            Node endNode) {
        // todo
    }

    void computeNumPathsLimitedByVisitingNodes(
            Node startNode,
            Node endNode,
            int numVisitedNodes) {
        // todo
    }

    void computeNumPathsLimitedByTotalWeight(
            Node startNode,
            Node endNode,
            int maxTotalWeight) {
        // todo
    }

    void computeRouteWithLeastTotalWeight(
            Node startNode,
            Node endNode) {
        // todo
    }
}
