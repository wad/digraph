package org.wadhome.digraph;

import org.wadhome.digraph.logic.Solver;
import org.wadhome.digraph.logic.Graph;
import org.wadhome.digraph.setup.Answer;
import org.wadhome.digraph.setup.ArgumentValues;
import org.wadhome.digraph.setup.Request;
import org.wadhome.digraph.setup.UserInteractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.wadhome.digraph.setup.Output.show;

public class Application {

    private static final int INDEX_OF_ARGUMENT_WITH_INPUT_FILENAME = 0;
    private static final int INDEX_OF_ARGUMENT_WITH_REQUEST_MENU_CHOICE = 1;
    private static final String HELP_MESSAGE_FOR_NON_INTERACTIVE_MODE = """
            If you only supply the input file on the command line,
            then the program will run in interactive mode, where you can do
            multiple different operations on the data, without having to reload it each time.
            If you want to do a single computation and then exit, then your command-line parameters
            should be:
            1. The path and filename of the input file
            2. Your choice from the menu below
            3. The values for the necessary parameters, for your chosen computation, in the order indicated in the menu.""";

    public static void main(String... args) {

        show("Directed Graph Experiment");

        if (args.length == 0) {
            show(HELP_MESSAGE_FOR_NON_INTERACTIVE_MODE);
            Request.showMenu();
            return;
        }

        String fileNameWithPath = args[INDEX_OF_ARGUMENT_WITH_INPUT_FILENAME];

        String fileContent;
        try {
            fileContent = Files.readString(Path.of(fileNameWithPath));
        } catch (IOException ioe) {
            show("Problem reading input file '" + fileNameWithPath + "': " + ioe.getMessage());
            return;
        }

        Graph graph = new Graph(fileContent);

        // If the user calls this with just the input file as a parameter, let's give them an interactive experience.
        boolean isInteractiveMode = args.length == 1;
        if (isInteractiveMode) {
            UserInteractor.interactiveMode(graph);
            return;
        }

        // The user has supplied other stuff on the command line, so we assume that they are doing a single
        // computation, and just want the answer, without using interactive mode.

        String menuChoice = args[INDEX_OF_ARGUMENT_WITH_REQUEST_MENU_CHOICE];
        Request request = Request.determineByMenuChoice(menuChoice);
        if (request == null) {
            show("Invalid request menu choice received: '" + menuChoice + "'.");
            show(HELP_MESSAGE_FOR_NON_INTERACTIVE_MODE);
            return;
        }

        switch (request) {
            case ShowMenu -> {
                show(HELP_MESSAGE_FOR_NON_INTERACTIVE_MODE);
                Request.showMenu();
            }
            case Quit -> {
                return;
            }
            default -> {
                // The first argument skipped is the input filename,
                // and the second argument skipped is the request choice.
                int numArgumentsToSkip = 2;
                List<String> rawArguments = new ArrayList<>(
                        Arrays.asList(args).subList(numArgumentsToSkip, args.length));

                show(request.getOutputMessage());
                Answer answer = Solver.answerRequest(
                        request,
                        new ArgumentValues(
                                request,
                                rawArguments),
                        graph);
                show(answer.toString(), true);
            }
        }
    }
}
