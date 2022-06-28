package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SolverForSummingWeightsTest {

    @Test
    public void testCaseWithNoGraph() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph(""));

        List<Node> nodesInVisitOrder = new ArrayList<>();
        nodesInVisitOrder.add(new Node("A"));
        nodesInVisitOrder.add(new Node("B"));
        nodesInVisitOrder.add(new Node("C"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                nodesInVisitOrder,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testCaseWithNoGraphAndNoNodesToVisit() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph(""));

        List<Node> nodesInVisitOrder = new ArrayList<>();
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                nodesInVisitOrder,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(0, answer.getNumericResult());
    }

    @Test
    public void testCaseWhereThereTheStartingNodeDoesNotExist() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("ab1,bc2"));

        List<Node> nodesInVisitOrder = new ArrayList<>();
        nodesInVisitOrder.add(new Node("z"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                nodesInVisitOrder,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testCaseWhereThereIsNoRoute() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("ab1,bc2"));

        List<Node> nodesInVisitOrder = new ArrayList<>();
        nodesInVisitOrder.add(new Node("a"));
        nodesInVisitOrder.add(new Node("z"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                nodesInVisitOrder,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertFalse(answer.getWasAnswerFound());
    }

    @Test
    public void testWithPreferringMinimumWeight() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("ab1,ab9,bc9,bc1"));

        List<Node> nodesInVisitOrder = new ArrayList<>();
        nodesInVisitOrder.add(new Node("a"));
        nodesInVisitOrder.add(new Node("b"));
        nodesInVisitOrder.add(new Node("c"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                nodesInVisitOrder,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(2, answer.getNumericResult());
    }

    @Test
    public void testWithPreferringMaximumWeight() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("ab1,ab9,bc9,bc1"));

        List<Node> nodesInVisitOrder = new ArrayList<>();
        nodesInVisitOrder.add(new Node("a"));
        nodesInVisitOrder.add(new Node("b"));
        nodesInVisitOrder.add(new Node("c"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                nodesInVisitOrder,
                false);
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(18, answer.getNumericResult());
    }

    @Test
    public void testWithTightLoop() {
        SolverForSummingWeights logic = new SolverForSummingWeights(
                new Graph("aa5,aa8"));

        List<Node> nodesInVisitOrder = new ArrayList<>();
        nodesInVisitOrder.add(new Node("a"));
        nodesInVisitOrder.add(new Node("a"));
        nodesInVisitOrder.add(new Node("a"));
        nodesInVisitOrder.add(new Node("a"));
        Answer answer = logic.computeTotalWeightOfSpecificRoute(
                nodesInVisitOrder,
                true);
        assertTrue(answer.getIsAnswerExpected());
        assertTrue(answer.getWasAnswerFound());
        assertEquals(15, answer.getNumericResult());
    }
}