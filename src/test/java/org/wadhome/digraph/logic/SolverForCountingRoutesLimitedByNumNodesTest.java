package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverForCountingRoutesLimitedByNumNodesTest {

    @Test
    public void testSimpleCase() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(
                new Graph("aa1,ab1,ba1,bb1"));

        Answer answer = logic.computeNumRoutesLimitedByVisitingNodes(
                new Node("a"),
                new Node("b"),
                2);
        assertEquals(3, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereNoVisitedNodes() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(
                new Graph("aa1,ab1,ba1,bb1"));

        Answer answer = logic.computeNumRoutesLimitedByVisitingNodes(
                new Node("a"),
                new Node("b"),
                0);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereDestinationNodeIsMissing() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(
                new Graph("aa1,ab1,ba1,bb1"));

        Answer answer = logic.computeNumRoutesLimitedByVisitingNodes(
                new Node("a"),
                new Node("c"),
                10);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereNoGraph() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(
                new Graph(""));

        Answer answer = logic.computeNumRoutesLimitedByVisitingNodes(
                new Node("a"),
                new Node("b"),
                3);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }

    @Test
    public void testCaseWhereThereIsJustOne() {
        SolverForCountingRoutes logic = new SolverForCountingRoutes(
                new Graph("aa1"));

        Answer answer = logic.computeNumRoutesLimitedByVisitingNodes(
                new Node("a"),
                new Node("a"),
                5);
        assertEquals(5, answer.getNumericResult(), "Got this: " + answer.getRoutesChosenAsString());
    }
}
