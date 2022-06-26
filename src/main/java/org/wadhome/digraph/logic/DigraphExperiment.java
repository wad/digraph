package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;
import org.wadhome.digraph.setup.ArgumentValues;
import org.wadhome.digraph.setup.Request;

import java.util.List;

import static org.wadhome.digraph.setup.Argument.*;

public class DigraphExperiment {

    private final DirectedWeightedGraph directedWeightedGraph;

    public DigraphExperiment(DirectedWeightedGraph directedWeightedGraph) {
        this.directedWeightedGraph = directedWeightedGraph;
    }

    public Answer answerRequest(
            Request request,
            ArgumentValues argumentValues) {
        switch (request) {
            case LoadAndDisplay:
                directedWeightedGraph.showAll();
                return Answer.noAnswerExpected();
            case ComputeTotalWeightOfSpecificRoute:
                return computeTotalWeightOfSpecificRoute(
                        argumentValues.getValueAsListOfNodes(ListOfNodes));
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
            List<Node> nodesInVisitOrder) {
        // todo
        return Answer.numeric(-1);
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
}
