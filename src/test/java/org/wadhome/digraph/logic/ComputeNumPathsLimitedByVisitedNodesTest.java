package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputeNumPathsLimitedByVisitedNodesTest {

    @Test
    public void testSimpleCase() {
        DigraphExperiment experiment = new DigraphExperiment(
                new DirectedWeightedGraph("aa1,ab1,ba1,bb1"));

        Answer answer = experiment.computeNumPathsLimitedByVisitingNodes(
                new Node("a"),
                new Node("b"),
                1);
        assertEquals(1, answer.getNumericResult());
    }

    @Test
    public void testCaseWhereNoVisitedNodes() {
        DigraphExperiment experiment = new DigraphExperiment(
                new DirectedWeightedGraph("aa1,ab1,ba1,bb1"));

        Answer answer = experiment.computeNumPathsLimitedByVisitingNodes(
                new Node("a"),
                new Node("b"),
                0);
        assertEquals(0, answer.getNumericResult());
    }

    @Test
    public void testCaseWhereDestinationNodeIsMissing() {
        DigraphExperiment experiment = new DigraphExperiment(
                new DirectedWeightedGraph("aa1,ab1,ba1,bb1"));

        Answer answer = experiment.computeNumPathsLimitedByVisitingNodes(
                new Node("a"),
                new Node("c"),
                10);
        assertEquals(0, answer.getNumericResult());
    }

    @Test
    public void testCaseWhereNoGraph() {
        DigraphExperiment experiment = new DigraphExperiment(
                new DirectedWeightedGraph(""));

        Answer answer = experiment.computeNumPathsLimitedByVisitingNodes(
                new Node("a"),
                new Node("b"),
                3);
        assertEquals(0, answer.getNumericResult());
    }

    @Test
    public void testMoreComplicatedCase() {
        DigraphExperiment experiment = new DigraphExperiment(
                new DirectedWeightedGraph("aa1,ab1,ba1,bb1"));

        Answer answer = experiment.computeNumPathsLimitedByVisitingNodes(
                new Node("a"),
                new Node("b"),
                3);
        // ab, aab, abb
        System.out.println(answer.getComment());
        assertEquals(3, answer.getNumericResult());
    }
}
