package org.wadhome.digraph.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.wadhome.digraph.setup.Output.show;

public class ArgumentValues {

    private final Map<Argument, String> valuesByArgument = new HashMap<>();

    // The first one was the input file name, and the second one was the request.
    private static final int START_INDEX_OF_ARGUMENTS_TO_INGEST = 2;

    public ArgumentValues(
            Request request,
            String... argsFromCommandLine) {

        List<Argument> arguments = request.getArguments();
        int numExpectedArguments = arguments.size();

        if (numExpectedArguments != argsFromCommandLine.length - START_INDEX_OF_ARGUMENTS_TO_INGEST) {
            show("Did not get the expected number of arguments for request " + request.getMenuIndicator() + ".");
            Request.showMenu();
            return;
        }

        int argumentIndex = START_INDEX_OF_ARGUMENTS_TO_INGEST;
        for (Argument argument : arguments) {
            valuesByArgument.put(
                    argument,
                    argsFromCommandLine[argumentIndex++]);
        }
    }

    public String getValueAsString(Argument argument) {
        String value = valuesByArgument.get(argument);
        if (value == null) {
            throw new RuntimeException("Missing expected argument value: " + argument.getArgumentName());
        }
        return value;
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
}
