package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import static org.junit.jupiter.api.Assertions.*;

public class SolverForFindingOptimumRouteTest {

    @Test
    public void testCaseWithNoGraph() {
        SolverForFindingOptimumRoute logic = new SolverForFindingOptimumRoute(
                new Graph(""));

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("A"),
                new Node("C"));
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testCaseWhereThereTheStartingNodeDoesNotExist() {
        SolverForFindingOptimumRoute logic = new SolverForFindingOptimumRoute(
                new Graph("ab1,bc2"));

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("Z"),
                new Node("C"));
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testCaseWhereThereIsNoRoute() {
        SolverForFindingOptimumRoute logic = new SolverForFindingOptimumRoute(
                new Graph("ab1,cd1"));

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("A"),
                new Node("D"));
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testWithTightLoop() {
        SolverForFindingOptimumRoute logic = new SolverForFindingOptimumRoute(
                new Graph("aa5,aa8"));

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("A"),
                new Node("A"));
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(0, answer.getNumericResult(), answer.getRoutesChosenAsString());
    }

    @Test
    public void testMoreComplicatedSituation() {
        SolverForFindingOptimumRoute logic = new SolverForFindingOptimumRoute(
                new Graph("aa5,aa8,aa5,ab21,ab1,ac90,bc22,bc11,bc12,bc18,cd3000,de7,ef1,df7,dx5,dy5,dz5"));
        // ab: best is 1
        // ac: not doing ac, because it's huge
        // bc: best is 11
        // cd: only is 100
        // de: no, because df is better
        // df: best is 7
        // total: 3019

        Answer answer = logic.computeRouteWithLeastTotalWeight(
                new Node("A"),
                new Node("F"));
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(3019, answer.getNumericResult(), answer.getRoutesChosenAsString());
    }
}
