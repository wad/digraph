package org.wadhome.digraph.setup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.wadhome.digraph.setup.Output.show;

public class UserInteractor {

    public static Request getRequest() {
        while (true) {
            show("\nMake your choice (enter 'm' to see the menu, 'q' to quit):");
            String menuChoice = getInput();
            Request requestMade = Request.determineByMenuChoice(menuChoice);

            if (requestMade == null) {
                show("Invalid menu choice, choose one from the menu:");
                Request.showMenu();
            } else {
                return requestMade;
            }
        }
    }

    public static ArgumentValues requestArgumentValues(Request request) {

        List<String> rawArgumentInputs = new ArrayList<>();
        List<Argument> arguments = request.getArguments();
        for (Argument argument : arguments) {
            show("Enter the value for " + argument.getArgumentName() + ":");
            rawArgumentInputs.add(getInput());
        }

        return new ArgumentValues(request, rawArgumentInputs);
    }

    static String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // The interaction looks better with a blank line here.
        show("");

        return input;
    }
}
