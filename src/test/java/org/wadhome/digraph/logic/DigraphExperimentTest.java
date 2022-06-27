package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigraphExperimentTest {

    static final String SAMPLE_DATA = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
    DirectedWeightedGraph graph = new DirectedWeightedGraph(SAMPLE_DATA);

    @Test
    public void testComputeTotalWeightOfSpecificRoute() {
        ComputationLogicForSummingWeights logic = new ComputationLogicForSummingWeights(graph);

        List<Node> nodesInVisitOrder = new ArrayList<>();
        nodesInVisitOrder.add(new Node("A"));
        nodesInVisitOrder.add(new Node("B"));
        nodesInVisitOrder.add(new Node("C"));
        assertEquals(
                9,
                logic.computeTotalWeightOfSpecificRoute(
                        nodesInVisitOrder,
                        true).getNumericResult());
    }

    @Test
    public void testComputeNumPathsLimitedByVisitingNodes() {
        ComputationLogicForCountingPaths logic = new ComputationLogicForCountingPaths(graph);

        Answer answer = logic.computeNumPathsLimitedByVisitingNodes(
                new Node("c"),
                new Node("c"),
                3);
        assertEquals(2, answer.getNumericResult(), "Got this: " + answer.getComment());
    }

    @Test
    public void testComputeNumPathsLimitedByTotalWeight() {
        ComputationLogicForCountingPaths logic = new ComputationLogicForCountingPaths(graph);

        assertEquals(2, logic.computeNumPathsLimitedByTotalWeight(
                new Node("c"),
                new Node("c"),
                29).getNumericResult());
    }

    @Test
    public void testComputeRouteWithLeastTotalWeight() {
        ComputationLogicForFindingOptimumPath logic = new ComputationLogicForFindingOptimumPath(graph);

        assertEquals(9, logic.computeRouteWithLeastTotalWeight(
                new Node("a"),
                new Node("c")).getNumericResult());
    }
}
