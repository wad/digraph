package org.wadhome.digraph.setup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.wadhome.digraph.setup.Output.show;

public enum Request {

    ShowMenu(
            "m",
            "Show this menu",
            Collections.emptyList()),

    ComputeTotalWeightOfSpecificRoute(
            "1",
            "Compute total weight of the specified route.",
            List.of(Argument.ListOfNodes)),

    ComputeNumPathsBetweenTwoNodesWithLimitOfVisitedNodes(
            "2a",
            "Compute number of paths between two nodes, up to the specified maximum number of nodes visited.",
            Arrays.asList(
                    Argument.StartNodeName,
                    Argument.EndNodeName,
                    Argument.MaxNumNodesVisited)),

    ComputeNumPathsBetweenTwoNodesWithLimitOfTotalWeight(
            "2b",
            "Compute number of paths between two nodes, up to the specified maximum total weight.",
            Arrays.asList(
                    Argument.StartNodeName,
                    Argument.EndNodeName,
                    Argument.MaxTotalWeight)),

    ComputeTotalWeightOfPathBetweenTwoNodesWithLeastTotalWeight(
            "3",
            "Compute total weight of the path between two nodes that has the least total weight.",
            Arrays.asList(
                    Argument.StartNodeName,
                    Argument.EndNodeName)),

    LoadAndDisplay(
            "4",
            "Display the entire digraph in order.",
            Collections.emptyList()),

    Quit(
            "q",
            "Quit this experiment.",
            Collections.emptyList());

    final String description;
    final String menuIndicator;
    final List<Argument> arguments;

    Request(
            String menuIndicator,
            String description,
            List<Argument> arguments) {
        this.menuIndicator = menuIndicator;
        this.description = description;
        this.arguments = arguments;
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
