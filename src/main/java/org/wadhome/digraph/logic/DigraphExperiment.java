package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;
import org.wadhome.digraph.setup.ArgumentValues;
import org.wadhome.digraph.setup.Request;

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
                return new ComputationLogic1(graph)
                        .computeTotalWeightOfSpecificRoute(
                                argumentValues.getValueAsListOfNodes(ListOfNodes),
                                true);
            case ComputeTotalWeightOfSpecificRoutePreferringMoreWeight:
                return new ComputationLogic1(graph)
                        .computeTotalWeightOfSpecificRoute(
                                argumentValues.getValueAsListOfNodes(ListOfNodes),
                                false);
            case ComputeNumPathsBetweenTwoNodesWithLimitOfVisitedNodes:
                return new ComputationLogic2(graph)
                        .computeNumPathsLimitedByVisitingNodes(
                                argumentValues.getValueAsNode(StartNodeName),
                                argumentValues.getValueAsNode(EndNodeName),
                                argumentValues.getValueAsInt(MaxNumNodesVisited));
            case ComputeNumPathsBetweenTwoNodesWithLimitOfTotalWeight:
                return new ComputationLogic2(graph)
                        .computeNumPathsLimitedByTotalWeight(
                                argumentValues.getValueAsNode(StartNodeName),
                                argumentValues.getValueAsNode(EndNodeName),
                                argumentValues.getValueAsInt(MaxTotalWeight));
            case ComputeTotalWeightOfPathBetweenTwoNodesWithLeastTotalWeight:
                return new ComputationLogic3(graph)
                        .computeRouteWithLeastTotalWeight(
                                argumentValues.getValueAsNode(StartNodeName),
                                argumentValues.getValueAsNode(EndNodeName));
            default:
                throw new IllegalStateException("Bug in code, unexpected value: " + request);
        }
    }
}
