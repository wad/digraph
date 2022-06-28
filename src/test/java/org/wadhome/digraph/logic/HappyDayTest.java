package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HappyDayTest {

    static final String SAMPLE_DATA = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
    Graph graph = new Graph(SAMPLE_DATA);

    @Test
    public void testComputeTotalWeightOfSpecificRoute() {
        SolverForSummingWeights logic = new SolverForSummingWeights(graph);

        Route route = new Route();
        route.addNode(new Node("A"));
        route.addNode(new Node("B"));
        route.addNode(new Node("C"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                route,
                true);
        assertEquals(9, answer.getNumericResult(), answer.getRoutesChosenAsString());
    }

    @Test
    public void testComputeNumRoutesLimitedByVisitingNodes() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(graph);

        Answer answer = logic.computeNumRoutesLimitedByVisitingNodes(
                new Node("c"),
                new Node("c"),
                3);
        assertEquals(2, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testComputeNumRoutesLimitedByTotalWeight() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(graph);

        Answer answer = logic.computeNumRoutesLimitedByTotalWeight(
                new Node("c"),
                new Node("c"),
                29);
        assertEquals(7, answer.getNumericResult(), answer.getRoutesChosenAsString());
    }

    @Test
    public void testComputeRouteWithLeastTotalWeight() {
        SolverForFindingOptimumRoute logic = new SolverForFindingOptimumRoute(graph);

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("a"),
                new Node("c"));
        assertEquals(9, answer.getNumericResult(), answer.getRoutesChosenAsString());
    }
}
