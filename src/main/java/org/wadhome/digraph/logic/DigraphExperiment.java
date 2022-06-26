package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.ArgumentValues;
import org.wadhome.digraph.setup.Request;
import org.wadhome.digraph.setup.UserInteractor;

import java.util.List;

import static org.wadhome.digraph.setup.Argument.*;
import static org.wadhome.digraph.setup.Output.show;

public class DigraphExperiment {

    private final DirectedWeightedGraph directedWeightedGraph;

    public DigraphExperiment(DirectedWeightedGraph directedWeightedGraph) {
        this.directedWeightedGraph = directedWeightedGraph;
    }

    public void enterInteractiveMode() {
        while (true) {
            Request request = UserInteractor.getRequest();
            if (request == Request.Quit) {
                show("Goodbye!");
                return;
            }
            answerRequest(request, UserInteractor.requestArgumentValues(request));
        }
    }

    public void answerRequest(
            Request request,
            ArgumentValues argumentValues) {
        switch (request) {
            case ShowMenu -> Request.showMenu();
            case LoadAndDisplay -> showGraph();
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

    void showGraph() {
        show("Displaying the entire directed graph, in a consistent order:");
        directedWeightedGraph.showAll();
    }

    Integer computeTotalWeightOfSpecificRoute(
            List<Node> nodesInVisitOrder) {
        // todo
        int answer = -1;
        show("Total weight of the specified route:");
        show(String.valueOf(answer), true);
        return answer;
    }

    Integer computeNumPathsLimitedByVisitingNodes(
            Node startNode,
            Node endNode,
            int numVisitedNodes) {
        // todo
        int answer = -1;
        show("Number of paths, limited by a maximum number of visited nodes, between selected nodes:");
        show(String.valueOf(answer), true);
        return answer;
    }

    Integer computeNumPathsLimitedByTotalWeight(
            Node startNode,
            Node endNode,
            int maxTotalWeight) {
        // todo
        int answer = -1;
        show("Number of paths, limited by a maximum total weight, between selected nodes:");
        show(String.valueOf(answer), true);
        return answer;
    }

    Integer computeRouteWithLeastTotalWeight(
            Node startNode,
            Node endNode) {
        // todo
        int answer = -1;
        show("Total weight of the route with the least total weight between selected nodes:");
        show(String.valueOf(answer), true);
        return answer;
    }
}
