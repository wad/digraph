package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverForCountingRoutesLimitedByMaxWeightTest {

    @Test
    public void testSimpleCase() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(
                new Graph("aa5,ab10,ba100,bb1000"));

        Answer answer = logic.computeNumRoutesLimitedByTotalWeight(
                new Node("a"),
                new Node("b"),
                18);
        assertEquals(2, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereNoWeightAllowed() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(
                new Graph("aa5,ab10,ba100,bb1000"));

        Answer answer = logic.computeNumRoutesLimitedByTotalWeight(
                new Node("a"),
                new Node("b"),
                0);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereDestinationNodeIsMissing() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(
                new Graph("aa5,ab10,ba100,bb1000"));

        Answer answer = logic.computeNumRoutesLimitedByTotalWeight(
                new Node("a"),
                new Node("c"),
                10000);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereNoGraph() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(
                new Graph(""));

        Answer answer = logic.computeNumRoutesLimitedByTotalWeight(
                new Node("a"),
                new Node("b"),
                10000);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereThereIsJustOne() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(
                new Graph("aa1"));

        Answer answer = logic.computeNumRoutesLimitedByTotalWeight(
                new Node("a"),
                new Node("a"),
                5);
        assertEquals(5, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }
}
