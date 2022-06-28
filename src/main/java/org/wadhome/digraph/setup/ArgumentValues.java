package org.wadhome.digraph.setup;

import org.wadhome.digraph.logic.Node;
import org.wadhome.digraph.logic.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.wadhome.digraph.setup.Output.show;

public class ArgumentValues {

    private final Map<Argument, String> valuesByArgument = new HashMap<>();

    public ArgumentValues(
            Request request,
            List<String> argumentValues) {
        List<Argument> arguments = request.getArguments();
        int numExpectedArguments = arguments.size();
        if (numExpectedArguments != argumentValues.size()) {
            show("Did not get the expected number of arguments for request " + request.getMenuIndicator() + ".");
            Request.showMenu();
            return;
        }
        int argumentIndex = 0;
        for (Argument argument : arguments) {
            valuesByArgument.put(
                    argument,
                    argumentValues.get(argumentIndex++));
        }
    }

    String getValueAsString(Argument argument) {
        String value = valuesByArgument.get(argument);
        if (value == null) {
            throw new RuntimeException("Missing expected argument value: " + argument.getArgumentName());
        }
        return value;
    }

    public Node getValueAsNode(Argument argument) {
        return new Node(String.valueOf(getValueAsString(argument)));
    }

    public int getValueAsInt(Argument argument) {
        String value = getValueAsString(argument);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            throw new RuntimeException(
                    "Expected argument " + argument.getArgumentName() + " to have an integer value,"
                            + " but got '" + value + "' instead.");
        }
    }

    public Route getValueAsRoute(Argument argument) {
        String value = getValueAsString(argument);
        Route route = new Route();
        for (int i = 0; i < value.length(); i++) {
            route.addNode(new Node(String.valueOf(value.charAt(i))));
        }
        return route;
    }
}
