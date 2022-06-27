package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputationLogicForCountingPathsTest {

    @Test
    public void testSimpleCase() {
        ComputationLogicForCountingPaths logic = new ComputationLogicForCountingPaths(
                new Graph("aa1,ab1,ba1,bb1"));

        Answer answer = logic.computeNumPathsLimitedByVisitingNodes(
                new Node("a"),
                new Node("b"),
                2);
        assertEquals(3, answer.getNumericResult(), "Got this: " + answer.getComment());
    }

    @Test
    public void testCaseWhereNoVisitedNodes() {
        ComputationLogicForCountingPaths logic = new ComputationLogicForCountingPaths(
                new Graph("aa1,ab1,ba1,bb1"));

        Answer answer = logic.computeNumPathsLimitedByVisitingNodes(
                new Node("a"),
                new Node("b"),
                0);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getComment());
    }

    @Test
    public void testCaseWhereDestinationNodeIsMissing() {
        ComputationLogicForCountingPaths logic = new ComputationLogicForCountingPaths(
                new Graph("aa1,ab1,ba1,bb1"));

        Answer answer = logic.computeNumPathsLimitedByVisitingNodes(
                new Node("a"),
                new Node("c"),
                10);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getComment());
    }

    @Test
    public void testCaseWhereNoGraph() {
        ComputationLogicForCountingPaths logic = new ComputationLogicForCountingPaths(
                new Graph(""));

        Answer answer = logic.computeNumPathsLimitedByVisitingNodes(
                new Node("a"),
                new Node("b"),
                3);
        assertEquals(0, answer.getNumericResult(), "Got this: " + answer.getComment());
    }

    @Test
    public void testCaseWhereThereIsJustOne() {
        ComputationLogicForCountingPaths logic = new ComputationLogicForCountingPaths(
                new Graph("aa1"));

        Answer answer = logic.computeNumPathsLimitedByVisitingNodes(
                new Node("a"),
                new Node("a"),
                5);
        assertEquals(5, answer.getNumericResult(), "Got this: " + answer.getComment());
    }
}
