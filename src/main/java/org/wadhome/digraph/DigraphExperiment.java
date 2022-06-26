package org.wadhome.digraph;

public class DigraphExperiment {

    DirectedWeightedGraph directedWeightedGraph;

    public DigraphExperiment(DirectedWeightedGraph directedWeightedGraph) {
        this.directedWeightedGraph = directedWeightedGraph;
    }

    public void performOperation(OperationRequest operationRequest) {
        switch(operationRequest) {
            case LoadAndDisplay -> {
                directedWeightedGraph.showAll();
                break;
            }
            default -> throw new IllegalStateException("Unexpected value: " + operationRequest);
        }
    }
}
