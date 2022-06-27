package org.wadhome.digraph.logic;

public abstract class ComputationLogic {

    protected final DirectedWeightedGraph graph;

    public ComputationLogic(DirectedWeightedGraph graph) {
        this.graph = graph;
    }
}
