package org.wadhome.digraph.setup;

public enum Argument {

    // Since the argument name is the same as the enum name, we could just use the
    // enum names instead of keeping a separate version of it as a String,
    // but that's dangerous because names of things in the code should not be directly
    // exposed to users. For example, what if this were localized to be in the French language?
    // Or if someone just renamed an enum value, not expecting that to change the user experience?
    StartNodeName("StartNodeName"),
    EndNodeName("EndNodeName"),
    ListOfNodes("ListOfNodes (like 'ABCD')"),
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
