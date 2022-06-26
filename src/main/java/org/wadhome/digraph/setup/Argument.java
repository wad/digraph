package org.wadhome.digraph.setup;

public enum Argument {

    StartNodeName("StartNodeName"),
    EndNodeName("EndNodeName"),
    MaxNumNodesVisited("MaxNumNodesVisited"),
    MaxTotalWeight("MaxTotalWeight");

    final String argumentName;

    Argument(String argumentName) {
        this.argumentName = argumentName;
    }

    public String getArgumentName() {
        return argumentName;
    }
}