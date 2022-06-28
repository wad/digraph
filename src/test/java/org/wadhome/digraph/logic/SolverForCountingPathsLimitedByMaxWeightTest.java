package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverForCountingPathsLimitedByMaxWeightTest {

    @Test
    public void testSimpleCase() {
        SolverForCountingPaths logic = new SolverForCountingPaths(
                new Graph("aa5,ab10,ba100,bb1000"));

        Answer answer = logic.computeNumPathsLimitedByTotalWeight(
                new Node("a"),
                new Node("b"),
                18);
        assertEquals(2, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereNoWeightAllowed() {
        SolverForCountingPaths logic = new SolverForCountingPaths(
                new Graph("aa5,ab10,ba100,bb1000"));

        Answer answer = logic.computeNumPathsLimitedByTotalWeight(
                new Node("a"),
                new Node("b"),
                0);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereDestinationNodeIsMissing() {
        SolverForCountingPaths logic = new SolverForCountingPaths(
                new Graph("aa5,ab10,ba100,bb1000"));

        Answer answer = logic.computeNumPathsLimitedByTotalWeight(
                new Node("a"),
                new Node("c"),
                10000);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereNoGraph() {
        SolverForCountingPaths logic = new SolverForCountingPaths(
                new Graph(""));

        Answer answer = logic.computeNumPathsLimitedByTotalWeight(
                new Node("a"),
                new Node("b"),
                10000);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereThereIsJustOne() {
        SolverForCountingPaths logic = new SolverForCountingPaths(
                new Graph("aa1"));

        Answer answer = logic.computeNumPathsLimitedByTotalWeight(
                new Node("a"),
                new Node("a"),
                5);
        assertEquals(5, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }
}
