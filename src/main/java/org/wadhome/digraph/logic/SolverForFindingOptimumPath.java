package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;

/**
 * The logic in this class is around computing the optimum path through the graph,
 * from a starting node to an ending node.
 */
public class SolverForFindingOptimumPath extends Solver {

    public SolverForFindingOptimumPath(Graph graph) {
        super(graph);
    }

    public Answer computeRouteWithLeastTotalWeight(
            Node startNode,
            Node endNode) {
        // todo
        return Answer.numeric(-1);
    }
}
