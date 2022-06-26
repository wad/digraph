package org.wadhome.digraph;

public class DigraphExperiment {

    private final DirectedWeightedGraph directedWeightedGraph;

    public DigraphExperiment(DirectedWeightedGraph directedWeightedGraph) {
        this.directedWeightedGraph = directedWeightedGraph;
    }

    public void performOperation(OperationRequest operationRequest) {
        switch(operationRequest) {
            case LoadAndDisplay -> {
                directedWeightedGraph.showAll();
            }
            case ComputeTotalWeightOfSpecificRoute -> {
                // todo
            }
            case ComputeNumPathsBetweenTwoNodesWithLimitOfVisitedNodes -> {
                // todo
            }
            case ComputeNumPathsBetweenTwoNodesWithLimitOfTotalWeight -> {
                // todo
            }
            case ComputeTotalWeightOfPathBetweenTwoNodesWithLeastTotalWeight -> {
                // todo
            }
            default -> throw new IllegalStateException("Unexpected value: " + operationRequest);
        }
    }
}
