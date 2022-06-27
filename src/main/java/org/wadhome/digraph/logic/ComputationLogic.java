package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;
import org.wadhome.digraph.setup.ArgumentValues;
import org.wadhome.digraph.setup.Request;

import static org.wadhome.digraph.setup.Argument.*;

public abstract class ComputationLogic {

    protected final Graph graph;

    public ComputationLogic(Graph graph) {
        this.graph = graph;
    }

    public static Answer answerRequest(
            Request request,
            ArgumentValues argumentValues,
            Graph graph) {
        switch (request) {
            case LoadAndDisplay:
                graph.showAll();
                return Answer.noAnswerExpected();
            case ComputeTotalWeightOfSpecificRoutePreferringLessWeight:
                return new ComputationLogicForSummingWeights(graph)
                        .computeTotalWeightOfSpecificRoute(
                                argumentValues.getValueAsListOfNodes(ListOfNodes),
                                true);
            case ComputeTotalWeightOfSpecificRoutePreferringMoreWeight:
                return new ComputationLogicForSummingWeights(graph)
                        .computeTotalWeightOfSpecificRoute(
                                argumentValues.getValueAsListOfNodes(ListOfNodes),
                                false);
            case ComputeNumPathsBetweenTwoNodesWithLimitOfVisitedNodes:
                return new ComputationLogicForCountingPaths(graph)
                        .computeNumPathsLimitedByVisitingNodes(
                                argumentValues.getValueAsNode(StartNodeName),
                                argumentValues.getValueAsNode(EndNodeName),
                                argumentValues.getValueAsInt(MaxNumNodesVisited));
            case ComputeNumPathsBetweenTwoNodesWithLimitOfTotalWeight:
                return new ComputationLogicForCountingPaths(graph)
                        .computeNumPathsLimitedByTotalWeight(
                                argumentValues.getValueAsNode(StartNodeName),
                                argumentValues.getValueAsNode(EndNodeName),
                                argumentValues.getValueAsInt(MaxTotalWeight));
            case ComputeTotalWeightOfPathBetweenTwoNodesWithLeastTotalWeight:
                return new ComputationLogicForFindingOptimumPath(graph)
                        .computeRouteWithLeastTotalWeight(
                                argumentValues.getValueAsNode(StartNodeName),
                                argumentValues.getValueAsNode(EndNodeName));
            default:
                throw new IllegalStateException("Bug in code, unexpected value: " + request);
        }
    }
}
