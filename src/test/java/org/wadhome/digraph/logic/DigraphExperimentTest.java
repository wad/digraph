package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigraphExperimentTest {

    static final String SAMPLE_DATA = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    @Test
    public void testComputeTotalWeightOfSpecificRoute() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(SAMPLE_DATA);
        DigraphExperiment experiment = new DigraphExperiment(graph);

        List<Node> nodesInVisitOrder = new ArrayList<>();
        nodesInVisitOrder.add(new Node("A"));
        nodesInVisitOrder.add(new Node("B"));
        nodesInVisitOrder.add(new Node("C"));
        assertEquals(
                9,
                experiment.computeTotalWeightOfSpecificRoute(
                        nodesInVisitOrder,
                        true).getNumericResult());
    }

    @Test
    public void testComputeNumPathsLimitedByVisitingNodes() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(SAMPLE_DATA);
        DigraphExperiment experiment = new DigraphExperiment(graph);

        assertEquals(2, experiment.computeNumPathsLimitedByVisitingNodes(
                new Node("c"),
                new Node("c"),
                3).getNumericResult());
    }

    @Test
    public void testComputeNumPathsLimitedByTotalWeight() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(SAMPLE_DATA);
        DigraphExperiment experiment = new DigraphExperiment(graph);

        assertEquals(2, experiment.computeNumPathsLimitedByTotalWeight(
                new Node("c"),
                new Node("c"),
                29).getNumericResult());
    }

    @Test
    public void testCompute() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(SAMPLE_DATA);
        DigraphExperiment experiment = new DigraphExperiment(graph);

        assertEquals(9, experiment.computeRouteWithLeastTotalWeight(
                new Node("a"),
                new Node("c")).getNumericResult());
    }
}
