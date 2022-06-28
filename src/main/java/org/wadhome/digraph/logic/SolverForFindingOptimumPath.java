package org.wadhome.digraph.logic;

import org.wadhome.digraph.setup.Answer;

import java.util.Optional;

import static java.util.Collections.singleton;

/**
 * The logic in this class is around computing the optimum path through the graph,
 * from a starting node to an ending node.
 */
public class SolverForFindingOptimumPath extends Solver {

    public SolverForFindingOptimumPath(Graph graph) {
        super(graph);
    }

    public Answer computeRouteWithLeastTotalWeight(
            Node startNode,
            Node endNode) {

        SolverForCountingPaths solver = new SolverForCountingPaths(graph);

        // One way to solve this is to check for solutions for each possible weight,
        // starting at the smallest possible value, and working up to a reasonable maximum weight.
        int minRouteWeightLimit = graph.getSmallestWeightFound();
        int maxRouteWeightLimit = determineMaxRouteWeightLimit();
        for (int maxWeight = minRouteWeightLimit; maxWeight <= maxRouteWeightLimit; maxWeight++) {

            Answer answerCandidate = solver.computeNumPathsLimitedByTotalWeight(
                    startNode,
                    endNode,
                    maxWeight);

            // The first time we find routes, this is the answer.
            // All the routes here will have the same weight, though they could be of different lengths.
            // We'll choose one of the ones with fewer nodes.
            Integer numberOfRoutesFound = answerCandidate.getNumericResult();
            if (numberOfRoutesFound > 0) {
                Optional<Route> routeWithLeastWeightOptional = answerCandidate
                        .getRoutesChosen()
                        .stream()
                        .sorted() // The Route comparator will make sure that routes with fewer nodes come before routes with more nodes.
                        .findFirst();
                if (routeWithLeastWeightOptional.isEmpty()) {
                    throw new RuntimeException("Bug in code: There should be a route here!");
                }
                Route routeWithLeastWeight = routeWithLeastWeightOptional.get();

                // Now that we have what we know is the shortest route,
                // let's calculate the lightest weight we can achieve by following it.
                SolverForSummingWeights solverForSummingWeights = new SolverForSummingWeights(graph);
                Answer finalAnswer = solverForSummingWeights.computeTotalWeightOfSpecificRoute(
                        routeWithLeastWeight,
                        true);
                finalAnswer.setRoutesChosen(singleton(routeWithLeastWeight));
                return finalAnswer;
            }
        }
        return Answer.notFound();
    }

    // Question: Where did these values come from?
    // Answer: No place in particular, I just chose logical-seeming values.
    // They are just to make sure that things don't go off the rails (haha).
    int determineMaxRouteWeightLimit() {
        int greatestWeightFound = graph.getGreatestWeightFound();
        if (greatestWeightFound >= Integer.MAX_VALUE / 10) {
            return Integer.MAX_VALUE;
        }
        return greatestWeightFound * 100;
    }
}
