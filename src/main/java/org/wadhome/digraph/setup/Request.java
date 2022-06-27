package org.wadhome.digraph.setup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.wadhome.digraph.setup.Output.show;

public enum Request {

    ShowMenu(
            "m",
            "Show this menu",
            Collections.emptyList(),
            null),

    ComputeTotalWeightOfSpecificRoutePreferringLessWeight(
            "1a",
            "Compute total weight of the specified route, preferring less-weight options.",
            List.of(Argument.ListOfNodes),
            "Total weight of the specified route:"),

    ComputeTotalWeightOfSpecificRoutePreferringMoreWeight(
            "1b",
            "Compute total weight of the specified route, preferring more-weight options.",
            List.of(Argument.ListOfNodes),
            "Total weight of the specified route:"),

    ComputeNumPathsBetweenTwoNodesWithLimitOfVisitedNodes(
            "2a",
            "Compute number of paths between two nodes, up to the specified maximum number of nodes visited.",
            Arrays.asList(
                    Argument.StartNodeName,
                    Argument.EndNodeName,
                    Argument.MaxNumNodesVisited),
            "Number of paths, limited by a maximum number of visited nodes, between selected nodes:"),

    ComputeNumPathsBetweenTwoNodesWithLimitOfTotalWeight(
            "2b",
            "Compute number of paths between two nodes, up to the specified maximum total weight.",
            Arrays.asList(
                    Argument.StartNodeName,
                    Argument.EndNodeName,
                    Argument.MaxTotalWeight),
            "Number of paths, limited by a maximum total weight, between selected nodes:"),

    ComputeTotalWeightOfPathBetweenTwoNodesWithLeastTotalWeight(
            "3",
            "Compute total weight of the path between two nodes that has the least total weight.",
            Arrays.asList(
                    Argument.StartNodeName,
                    Argument.EndNodeName),
            "Total weight of the route with the least total weight between selected nodes:"),

    LoadAndDisplay(
            "4",
            "Display the entire directed graph, with weights.",
            Collections.emptyList(),
            "Displaying the entire directed graph, in a consistent order, by source node:"),

    Quit(
            "q",
            "Quit this experiment.",
            Collections.emptyList(),
            null);

    final String description;
    final String menuIndicator;
    final List<Argument> arguments;
    final String outputMessage;

    Request(
            String menuIndicator,
            String description,
            List<Argument> arguments,
            String outputMessage) {
        this.menuIndicator = menuIndicator;
        this.description = description;
        this.arguments = arguments;
        this.outputMessage = outputMessage;
    }

    public String getDescription() {
        return description;
    }

    public String getMenuIndicator() {
        return menuIndicator;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public static Request determineByMenuChoice(String menuChoice) {
        for (Request request : values()) {
            if (request.getMenuIndicator().equalsIgnoreCase(menuChoice)) {
                return request;
            }
        }
        return null;
    }

    public static void showMenu() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n█████████████████\n███ Main Menu ███\n█████████████████\n");
        for (Request request : values()) {
            builder.append(request.getMenuIndicator())
                    .append(": ")
                    .append(request.getDescription())
                    .append("\n");
            List<Argument> parameterNames = request.getArguments();
            if (!parameterNames.isEmpty()) {
                builder.append("\tParameters needed:\n");
                for (Argument parameterName : parameterNames) {
                    builder.append("\t\t")
                            .append(parameterName.getArgumentName())
                            .append("\n");
                }
            }
        }
        show(builder.toString());
    }
}
