package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import static org.junit.jupiter.api.Assertions.*;

public class SolverForSummingWeightsTest {

    @Test
    public void testCaseWithNoGraph() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph(""));

        Route route = new Route();
        route.addNode(new Node("A"));
        route.addNode(new Node("B"));
        route.addNode(new Node("C"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                route,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testCaseWithNoGraphAndNoNodesToVisit() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph(""));

        Route route = new Route();
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                route,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(0, answer.getNumericResult());
    }

    @Test
    public void testCaseWhereThereTheStartingNodeDoesNotExist() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("ab1,bc2"));

        Route route = new Route();
        route.addNode(new Node("z"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                route,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testCaseWhereThereIsNoRoute() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("ab1,bc2"));

        Route route = new Route();
        route.addNode(new Node("a"));
        route.addNode(new Node("z"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                route,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testWithPreferringMinimumWeight() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("ab1,ab9,bc9,bc1"));

        Route route = new Route();
        route.addNode(new Node("a"));
        route.addNode(new Node("b"));
        route.addNode(new Node("c"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                route,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(2, answer.getNumericResult());
    }

    @Test
    public void testWithPreferringMaximumWeight() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("ab1,ab9,bc9,bc1"));

        Route route = new Route();
        route.addNode(new Node("a"));
        route.addNode(new Node("b"));
        route.addNode(new Node("c"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                route,
                false);
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(18, answer.getNumericResult());
    }

    @Test
    public void testWithTightLoop() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("aa5,aa8"));

        Route route = new Route();
        route.addNode(new Node("a"));
        route.addNode(new Node("a"));
        route.addNode(new Node("a"));
        route.addNode(new Node("a"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                route,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(15, answer.getNumericResult());
    }

    @Test
    public void testEasyRoute() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("aa5,aa8"));

        Route route = new Route();
        route.addNode(new Node("a"));
        route.addNode(new Node("a"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                route,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(5, answer.getNumericResult());
    }
}
