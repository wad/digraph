package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import static org.junit.jupiter.api.Assertions.*;

public class SolverForFindingOptimumRouteTest {

    @Test
    public void testCaseWithNoGraph() {
        SolverForFindingOptimumPath logic = new SolverForFindingOptimumPath(
                new Graph(""));

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("A"),
                new Node("C"));
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testCaseWhereThereTheStartingNodeDoesNotExist() {
        SolverForFindingOptimumPath logic = new SolverForFindingOptimumPath(
                new Graph("ab1,bc2"));

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("Z"),
                new Node("C"));
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testCaseWhereThereIsNoRoute() {
        SolverForFindingOptimumPath logic = new SolverForFindingOptimumPath(
                new Graph("ab1,cd1"));

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("A"),
                new Node("D"));
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testWithTightLoop() {
        SolverForFindingOptimumPath logic = new SolverForFindingOptimumPath(
                new Graph("aa5,aa8"));

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("A"),
                new Node("A"));
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(5, answer.getNumericResult(), answer.getRoutesChosenAsString());
    }

    // todo: Write some more tests here
}
