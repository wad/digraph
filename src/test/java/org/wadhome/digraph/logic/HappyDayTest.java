package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HappyDayTest {

    static final String SAMPLE_DATA = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
    Graph graph = new Graph(SAMPLE_DATA);

    @Test
    public void testComputeTotalWeightOfSpecificRoute() {
        SolverForSummingWeights logic = new SolverForSummingWeights(graph);

        List<Node> nodesInVisitOrder = new ArrayList<>();
        nodesInVisitOrder.add(new Node("A"));
        nodesInVisitOrder.add(new Node("B"));
        nodesInVisitOrder.add(new Node("C"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                nodesInVisitOrder,
                true);
        assertEquals(9, answer.getNumericResult(), answer.getComment());
    }

    @Test
    public void testComputeNumPathsLimitedByVisitingNodes() {
        SolverForCountingPaths logic = new SolverForCountingPaths(graph);

        Answer answer = logic.computeNumPathsLimitedByVisitingNodes(
                new Node("c"),
                new Node("c"),
                3);
        assertEquals(2, answer.getNumericResult(), "Got this: " + answer.getComment());
    }

    @Test
    public void testComputeNumPathsLimitedByTotalWeight() {
        SolverForCountingPaths logic = new SolverForCountingPaths(graph);

        Answer answer = logic.computeNumPathsLimitedByTotalWeight(
                new Node("c"),
                new Node("c"),
                29);
        assertEquals(2, answer.getNumericResult(), answer.getComment());
    }

    @Test
    public void testComputeRouteWithLeastTotalWeight() {
        SolverForFindingOptimumPath logic = new SolverForFindingOptimumPath(graph);

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("a"),
                new Node("c"));
        assertEquals(9, answer.getNumericResult(), answer.getComment());
    }
}
