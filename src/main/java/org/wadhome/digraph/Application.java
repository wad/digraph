package org.wadhome.digraph;

import org.wadhome.digraph.logic.DigraphExperiment;
import org.wadhome.digraph.logic.DirectedWeightedGraph;
import org.wadhome.digraph.setup.ArgumentValues;
import org.wadhome.digraph.setup.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.wadhome.digraph.setup.Output.show;

public class Application {

    private static final int INDEX_OF_ARGUMENT_WITH_INPUT_FILENAME = 0;
    private static final int INDEX_OF_ARGUMENT_WITH_REQUEST_MENU_CHOICE = 1;

    public static void main(String... args) {

        show("Directed Graph Experiment");

        if (args.length == 0) {
            show("""
                    Supply the destinationNodeWithWeight and name of an input file
                    which contains a comma-delimited list of source node name,
                    destination node name, and edge weight.
                    For example: AB3,BC56,CA5""");
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

        DirectedWeightedGraph directedWeightedGraph = new DirectedWeightedGraph(fileContent);
        DigraphExperiment digraphExperiment = new DigraphExperiment(directedWeightedGraph);

        boolean isInteractiveMode = args.length == 1;
        if (isInteractiveMode) {
            digraphExperiment.enterInteractiveMode();
            return;
        }

        String menuChoice = args[INDEX_OF_ARGUMENT_WITH_REQUEST_MENU_CHOICE];
        Request request = Request.determineByMenuChoice(menuChoice);
        if (request == null) {
            show("Invalid request menu choice received: '" + menuChoice + "'.");
            return;
        }

        ArgumentValues argumentValues = new ArgumentValues(request, args);
        digraphExperiment.answerRequest(request, argumentValues);
    }
}
