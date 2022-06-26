package org.wadhome.digraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Application {

    static StringBuilder outputCache = new StringBuilder();

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

        String fileNameWithPath = args[0];

        String fileContent;
        try {
            fileContent = Files.readString(Path.of(fileNameWithPath));
        } catch (IOException ioe) {
            show("Problem reading input file '" + fileNameWithPath + "': " + ioe.getMessage());
            return;
        }

        DirectedWeightedGraph directedWeightedGraph = new DirectedWeightedGraph(fileContent);
        DigraphExperiment digraphExperiment = new DigraphExperiment(directedWeightedGraph);
        digraphExperiment.performOperation(OperationRequest.LoadAndDisplay);
    }

    // Just consolidating all output here for convenience if we want to change it.
    public static void show(String message) {
        outputCache.append(message);
        outputCache.append("\n");
        System.out.println(message);
    }
}
